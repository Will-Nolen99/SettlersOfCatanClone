//import java.util.Scanner;

import java.io.IOException; 
//import java.net.UnknownHostException;
import java.util.ArrayList;
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
	
	private ArrayList<Player> others;
	
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
        	
        
        String name = "PlaceHolder";
        
        this.player = new Player(name);
        this.player.setNum(-1);
        this.mode = "setup";
        
        this.infoScreen = new PlayerInfoScreen(this, this.player);
        this.waitingScreen = new WaitingScreen(this);
        this.main = new MainUi(this);
        
        this.placementUi = new PlacementUi(this);
        
        
        
        
        
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
    			this.mode = "starting pieces settlement";
    			board = client.getBoard();
    			this.main.setMainPlayer(this.player);
    			this.others = client.getPlayers();
    			this.main.addPlayers(this.others);
    			
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
    				boolean placed = this.placementUi.draw("settlement", this.player);
    				this.board = this.placementUi.getBoard();
    				
    				if(placed) {
    					this.mode = "starting pieces road";
    				}
    				
    			}else {
    				
    				
    				//this.otherTurnUi.draw(turn);
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
    				boolean placed = this.placementUi.draw("road", this.player);
    				this.board = this.placementUi.getBoard();
    				
    				this.mode = "starting pieces road";
    				
    				if (mouseX > 1900) {
    					this.mode = "starting pieces settlement";
    				}
    				
    				
    			}else {
    				
    				
    				//this.otherTurnUi.draw(turn);
    			}
    			
    			
    		}
    		

    		break;
    		
    	}
    	
    }
    
    

    
}