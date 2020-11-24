import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{
	
	private Socket socket;
	
	ObjectInputStream in;
	ObjectOutputStream out;
	
	private String mode;
	
	private String playerTurn;
	
	Player player;
	
	private Board board;
	
	private int numPlayers;
	
	boolean currentTurn;
	
	ArrayList<Player> players;
	
	public Client() throws IOException{
		this.socket = new Socket("127.0.0.1", 9090);
		
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.mode = "waiting";
		System.out.println("Connection created");
		System.out.println();
		
		this.currentTurn = false;
		this.playerTurn = "None";
		
	}
	
	
	public void close() throws IOException {
		this.socket.close();
	}
	
	
	
	public String getMode() {
		return this.mode;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	
	public boolean getTurn() {
		return this.currentTurn;
	}
	
	
	public void setBoard(Board b) {
		this.board = b;
	}
	
	public void sendBoard() throws IOException{
		System.out.println("Sending board to server");
		this.out.writeObject(this.board);
		this.out.flush();
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void sendPlayer() throws IOException {
		this.out.writeObject(this.player);
		this.out.flush();
	}
	
	public String getPlayerTurn() {
		return this.playerTurn;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		boolean running = true;
		
		try {
			
			
			while(running) {
			
			
				System.out.println("Checking for message from server");
				
					int messageType = in.readInt();
					
					System.out.println("Message type recieved, Type: " + messageType);
					System.out.println();
					
					switch(messageType) {
					
						case 1:
							//Server is asking for player information
							System.out.println("Sending player information");
							out.writeObject(this.player);
							
							//Server will now respond with updated player information
							
							this.player = (Player) in.readObject();
							
							System.out.println("New information recieved");
							System.out.println(this.player);
							
							break;
							
						case 2:
							System.out.println("Recieving other player information");
							
							this.numPlayers = in.readInt();
							int otherPlayers = this.numPlayers - 1;
							System.out.println("Other players in game: " + otherPlayers);
							
							this.players = new ArrayList<Player>();
								
							this.players = (ArrayList<Player>)in.readObject();

							System.out.println("All player information recieved");
							System.out.println();
							
							break;
							
						case 3:
							
							//server is sending information about the board;
							this.board = (Board) in.readObject();
							System.out.println("Board recieved");
							this.mode = "starting pieces";
							break;
							
						case 4:
							System.out.println("Player placement turn recieved");
							this.playerTurn = (String) this.in.readObject();
							
							System.out.println(this.playerTurn + "'s placement turn");
							
							
							if(this.player.getName().equals(this.playerTurn)) {
								this.mode = "placing";
								
								while(this.mode.equals("placing")) {
									Thread.sleep(10);
									continue;
								}
								
								
								System.out.println("Sending board");
								this.sendBoard();
								
							}else {
								this.mode = "waiting";
							}

							break;
							
							
							
						case 5:
							System.out.println("End turn recieved");
							this.currentTurn = false;
							break;
							
							
						case 6:
							System.out.println("Getting board info");
							
							this.board = (Board) in.readObject();
							break;
							
					}
					
				
					
				
				
				Thread.sleep(1000);
				
			}
			
			
		} catch (IOException | InterruptedException e) {
			System.out.println("Read from connection failed");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}



	
	
	
}
