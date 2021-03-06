//import java.util.Scanner;

import java.io.IOException;  
//import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
//import java.util.Scanner;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

public class Game extends PApplet {

	private Player player;
	private String mode;
	
	private PlayerInfoScreen infoScreen;
	private WaitingScreen waitingScreen;
	
	private OtherTurnUi otherTurnUi;
	
	private MainUi main;
	
	private Client client;
	
	private Board board;
	
	private ArrayList<Player> players;
	
	private PlacementUi placementUi;
	
	private RollUI roll;
	
	
	
    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("Game");
    }

    // method for setting the size of the window
    public void settings(){
        size(1920, 1080);
    }

    // identical use to setup in Processing IDE except for size()
    public void setup(){
    	
    	//setup window
        background(0);
        stroke(255);
        strokeWeight(10);
        textSize(75);
        text("Enter name in console", width/3, height/2);
        
        //create initial player class
        
//        Scanner scnr = new Scanner(System.in);
//        
//        System.out.print("Enter name: ");
//        String name = scnr.nextLine();
//        
//        scnr.close();
        
        
        Random r = new Random();

        	
        
        String name = "" + r.nextInt(100000);
        
        this.player = new Player(name);
        this.player.setNum(-1);
        this.mode = "setup";
        
        this.infoScreen = new PlayerInfoScreen(this, this.player);
        this.waitingScreen = new WaitingScreen(this);
        this.main = new MainUi(this, name);
        
        this.placementUi = new PlacementUi(this);
        
        
        this.otherTurnUi = new OtherTurnUi();
        
        this.roll = new RollUI(this);
        
 
    }
    
    
    
    
    

    // identical use to draw in Processing IDE
    public void draw(){
    	
    	

        
    	switch(this.mode) {
    	
    	case "setup":
    		this.infoScreen.draw();
    		if(this.infoScreen.getStatus().equals("finished")) {
    			this.mode = "waiting for players";
    			
    			System.out.println(this.player.toString());
    			this.waitingScreen.setColor(this.player.getColor());
    			
    	        try {
    	        	System.out.println("Creating connection to server");
    				this.client = new Client();
    				this.client.setPlayer(this.player);
    				this.client.start();
    				
    				
    			} catch (IOException e) {
    				System.out.println("Connection could not be established");
    				e.printStackTrace();
    			}
    			
    			
    			
    		}
    		break;
    	
    	case "waiting for players":
    		
    		this.waitingScreen.draw();
    		
    		if(client.getMode().equals("starting pieces")) {
    			this.mode = "starting placement";
    			
    			this.players = client.getPlayers();
    			board = client.getBoard();

    			this.main.addPlayers(this.players);
    			
    		}
    		
    		break;
    		
    		
    		
    	case "starting placement":
    		background(255);
    		stroke(0);
    		board.draw(this);
    		this.main.draw();
    		
    		
    		String playerTurn = client.getPlayerTurn();
    		
    		String placeMode = this.client.getMode();
    		this.board = this.client.getBoard(); 	


    	
    		
    		if(placeMode.equals("placing")) {
    			this.mode = "starting pieces settlement";
    			
    		}else if(placeMode.equals("game")) {
    			this.mode = "game";
    		}else {
    			this.otherTurnUi.draw(this, playerTurn);
    		}
    		
    		break;
    		
    	case "starting pieces settlement":
    		background(255);
    		stroke(0);
    		board.draw(this);

    		
    		

    		
    		this.main.draw();
    		
    		String turn = client.getPlayerTurn();
    		
    		if(!turn.equals("none")) {
    			
    			if(turn.equals(this.player.getName())) {
    				
    				this.placementUi.setBoard(this.board);
    				boolean placed = this.placementUi.draw("settlement", this.player, "initial");
    				this.board = this.placementUi.getBoard();
    				

    				
    				if(placed) {
    					this.mode = "starting pieces road";
    					this.client.setBoard(this.board);
    					this.client.setPlayer(this.player);
    					
    					
    					
    					String name = this.player.getName();
    					
    					for(int i = 0; i < this.players.size(); i++) {
    						
    						if(this.players.get(i).getName().equals(name)) {
    					
    							this.players.remove(i);
    							this.players.add(i, this.player);
    					
    						}
    					}
    					
    					

    					
    				}
    				
    			}
    			
    			
    		}
    		

    		break;
    		
    		
    	case "starting pieces road":
    		
    		background(255);
    		stroke(0);
    		board.draw(this);
    		this.main.draw();
    		
    		String turn1 = client.getPlayerTurn();
    		
    		if(!turn1.equals("none")) {
    			
    			if(turn1.equals(this.player.getName())) {
    				
    				
    				this.placementUi.setBoard(this.board);
    				boolean placed = this.placementUi.draw("road", this.player, "intitial");
    				this.board = this.placementUi.getBoard();
    				
    				
    				if (placed) {
    					
    					this.client.setBoard(this.board);
    					this.mode = "starting placement";
    					this.client.setPlayers(this.players);
    					this.client.setMode("done");
    				}
    				
    				
    			}
    			
    			
    		}
    		

    		break;
    		
    		
    	case "other players turn":
    		
    		background(255);
    		stroke(0);
    		board.draw(this);
    		this.main.draw();

    		this.mode = "starting placement";
    		break;

    		
    		
    		
    	case "game":
    		
    		background(255);
    		stroke(0);
    		
    		board.draw(this);
    		this.main.draw();
    		
    		
    		String currentTurn = client.getPlayerTurn();
    		
    		if(currentTurn.equals(this.player.getName())) {
    			
    			
    			this.mode = "Rolling";
    			
    		}else {
    			
    			this.mode = "other turn";
    			
    		}
    		
    		
    		break;
    	
    	case "Rolling":
    		
    		background(255);
    		stroke(0);
    		
    		board.draw(this);
    		this.main.draw();
    		text("Your turn", width/2, 100);
    		
    		this.roll.draw();
			this.roll.update();
			
			if(this.roll.isRolled()) {
				
				
				//int rollValue = this.roll.getRoll();
				int rollValue = 6;
				
				
				//give players cards based on roll
				
				
				
				for(Player player: this.players) {
					
					System.out.println("Adding cards for " + player.getName());
					
					int[] playerColor = player.getColor();
					
					for(BuildingPoint bp: this.board.getBuildingPoints()) {
						
						Map<String, ArrayList<Integer>> resourceMap = bp.getMap();
						
						System.out.println("MAP: " + resourceMap);
						
						int[] otherColor = bp.getColor();
						
						if(playerColor[0] == otherColor[0] && playerColor[1] == otherColor[1] && playerColor[2] == otherColor[2]) {
							for(Map.Entry<String, ArrayList<Integer>> pair : resourceMap.entrySet()) {
								
								ArrayList<Integer> nums = pair.getValue();
								String type = pair.getKey();
								
								for(int num: nums) {
									if(num == rollValue) {
										Map<String, Integer> cards = player.getCards();
										
										cards.put(type, cards.get(type) + 1);
										
										if(bp.getBuilding().equals("city")) {
											cards.put(type, cards.get(type) + 1);
										}
										
										
										
										
									}
								}
								
								
							}
						}
						
						
						
						
					}
				}
					
				
				for(Player p: this.players) {
					if(p.getName().equals(this.player.getName())) {
						this.player = p;
					}
				}
				
				
				
				this.mode = "test";
			}
			
			
			break;
    		
    		
    	case "other turn":
    		
    		background(255);
    		stroke(0);
    		
    		board.draw(this);
    		this.main.draw();
    		
			push();
			
			this.fill(0);
			text(client.getPlayerTurn() + "'s turn", width/2, 100);
			
			pop();
    		
    		if(client.getPlayerTurn().equals(this.player.getName())) {
    			this.mode = "game";
    		}
    		
    		break;
    		
    		
    	case "test":
    		background(255);
    		stroke(0);
    		
    		board.draw(this);
    		this.main.draw();
    		
    		break;
    		
    	}
    	
    	

    		
    		
    		
    	
    	
    	
    }
    
    

    
}