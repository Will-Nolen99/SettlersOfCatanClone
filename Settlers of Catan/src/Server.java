import java.io.*; 
//import java.text.*; 
import java.util.*; 
import java.net.*; 


public class Server {


	
	
	public static void main(String[] args) throws IOException {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Enter number of players: ");
		String n = keyboard.nextLine();
		int numPlayers = Integer.parseInt(n);
		
		int layers = 1;
		
//		System.out.println("Enter number of board layers: ");
//		n = keyboard.nextLine();
//		layers = Integer.parseInt(n);
//		
//		
//		
//		//number of tiles in hexagonal
//		
		int tiles = 1;
//		
//		for(int i = 0; i < layers; i++) {
//			tiles += 6 * i;
//		}
//		
		
		
		//Create server and assign port
		
		final int PORT = 9090;
		ServerSocket server = new ServerSocket(PORT);
		
		System.out.println("Server started on port " + PORT);
		
		
		
		
		// get connections
		
		ArrayList<Connection> connections = new ArrayList<Connection>();
		
		try {
			int connectedPlayers = 0;
			while(connectedPlayers < numPlayers) {
				
				System.out.println("Searching for players: " + connectedPlayers + "/" + numPlayers);
				
				
				//create socket and accept connection
				Socket connection = null;
				connection = server.accept();
				
				System.out.println("Connection established on socket: " + connection);
				System.out.println("Assigning new thread");
				System.out.println();
				
				
				ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
				
				
				Connection client = new Connection(connection, in, out);
				
				System.out.println("Connection created");
				System.out.println("Starting connection");
				
				
				String clientName = "Player " + (connectedPlayers + 1);
				client.setName(clientName);
				
				client.start();
				
				connections.add(client);
				connectedPlayers++;
				
				
			}
			
			System.out.println();
			System.out.println("All players connected");
			System.out.println("Getting player information");
			System.out.println();
			
			
			ArrayList<Player> players = new ArrayList<Player>();
			
			for(int i = 0; i < connections.size(); i++) {
				
				
				Connection current = connections.get(i);
				
				System.out.println("Sending message to player " + (i + 1));
				
				current.sendMessageType(1);
				
				Player p = (Player) current.readObject();
				
				System.out.println("Player " + (i + 1) + " information recieved");
				System.out.println(p.toString());
				System.out.println();
				
				System.out.println("Assigning player number");
				p.setNum(i + 1);
				
				System.out.println("Resending player information");
				
				current.sendPlayerInformation(p);
				System.out.println("Sent");
				
				
				
				players.add(p);
				
				
				System.out.println();
			}
			
			System.out.println("Synchronizing information with other players");
			
			for(int i = 0; i < connections.size(); i++) {
				
				Connection current = connections.get(i);
				
				current.sendMessageType(2);
				
				current.sendNumber(numPlayers);
			    
				
				System.out.println("Synchronizing with player " + (i + 1));
				
				for(int j = 0; j < connections.size(); j++) {
					
					if(j != i) {
						
						System.out.println("Sending information about player " + (j + 1));
						
						Player p = players.get(j);
						current.sendPlayerInformation(p);
						
					}
					
				}
				System.out.println();
			}
			
			
			
			/*
			 * game and board setup starts here
			 */
			
			
			//create board and decks
			
			System.out.println("Creating decks and board");
			
			Decks decks = new Decks(numPlayers);
			Board board = new Board(tiles);
			
			//synchronize board with each player
			
			System.out.println("Sending initial board with players");
			for(int i = 0; i < connections.size(); i++) {
				Connection connection = connections.get(i);
				connection.sendMessageType(3);
				connection.sendBoard(board);
			}
			
			System.out.println("Board synced");
			
			
			
			
			
			
			
		}catch(Exception e) {
			System.out.println("Error with connections");
			System.out.println(e);
		}
		
		

		
		
		keyboard.close();	
		
		server.close();
		

	}
	

}



class Connection extends Thread {
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String mode;
	
	
	
	public Connection(Socket s, ObjectInputStream in, ObjectOutputStream out) throws IOException {
		
		
		System.out.println("Creating connection class");
		
		this.socket = s;
		this.in = in;
		this.out = out;
		this.mode = "waiting player info";
		
		System.out.println("Connection created on socket " + this.socket);
		
	}
	
	
	
	
	@Override
	public void run() {
		
		while(true) {
			
			
			try {
				
				switch(this.mode) {
				
					case "waiting player info":
	
					break;
				
				}
					
				
			}catch(Exception e) {
				System.out.println("Error in client conection");
				System.out.println(e);
			}

			
			
			
		}
		
		
	}
	
	public void sendMessageType(int code) throws IOException {
		System.out.println("Sending type: " + code);
		this.out.writeInt(code);
		this.out.flush();
		System.out.println("Sent");
		
	}
	
	public void sendBoard(Board b) throws IOException {
		this.out.writeObject(b);
	}
	
	public String reciveMessage() throws ClassNotFoundException, IOException {
		String m = (String) this.in.readObject();
		return m;
	}
	
	public Player getPlayerInfo() throws ClassNotFoundException, IOException {
		Player p = (Player) this.in.readObject();
		return p;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public Object readObject() throws ClassNotFoundException, IOException {
		return this.in.readObject();
	}
	
	public void sendPlayerInformation(Player p) throws IOException {
		this.out.writeObject(p);
		this.out.flush();
	}
	
	public void sendNumber(int n) throws IOException {
		this.out.writeInt(n);
	}
	
	
}
