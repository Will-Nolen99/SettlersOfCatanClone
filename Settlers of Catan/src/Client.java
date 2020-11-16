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
	
	Player player;
	
	private Board board;
	
	private int numPlayers;
	
	ArrayList<Player> otherPlayers;
	
	public Client() throws IOException{
		this.socket = new Socket("127.0.0.1", 9090);
		
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.mode = "waiting";
		System.out.println("Connection created");
		System.out.println();
		
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
							
							this.otherPlayers = new ArrayList<Player>();
							
							for(int i = 0; i < otherPlayers; i++) {
								
								Player p = (Player) in.readObject();
								
								this.otherPlayers.add(p);
								
								int pNum = p.getNum();
								
								System.out.println("Player " + pNum + " Information recieved");
								System.out.println(p);
								
							}
							
							System.out.println("All player information recieved");
							System.out.println();
							
							break;
							
						case 3:
							
							//server is sending information about the board;
							this.board = (Board) in.readObject();
							System.out.println("Board recieved");
							this.mode = "starting pieces";
					
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
