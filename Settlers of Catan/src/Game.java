//import java.util.Scanner;

import java.io.IOException; 
//import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import processing.core.PApplet;

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
    		
    		
    		//delay(2000);
    		
    		String playerTurn = client.getPlayerTurn();
    		
    		String placeMode = this.client.getMode();
    		this.board = this.client.getBoard(); 		
    	
    		
    		if(placeMode.equals("placing")) {
    			this.mode = "starting pieces settlement";
    		}else {
    			this.otherTurnUi.draw(this, playerTurn);
    		}
    		
    		break;
    		
    	case "starting pieces settlement":
    		background(255);
    		stroke(0);
    		board.draw(this);

    		
    		
    		//TODO: change placement UI and Main UI
    		// Pass in players as paramaters rather than instance variables.
    		// get player number in game.
    		
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
//    					this.player.decrementPiece("settlement");
//    					this.player.incrementPoints();
    					
    					for(Player p: this.players) {
    						if(p.getName().equals(this.player.getName())) {
    							p.decrementPiece("settlement");
    							p.incrementPoints();
    							this.player = p;
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
    					this.player.decrementPiece("road");
    					this.mode = "starting placement";
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

    		
    		
    	}
    	
    }
    
    

    
}