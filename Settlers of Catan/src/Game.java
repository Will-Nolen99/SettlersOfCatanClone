//import java.util.Scanner;

import java.io.IOException; 
//import java.net.UnknownHostException;

import processing.core.PApplet;

public class Game extends PApplet {

	private Player player;
	String mode;
	
	PlayerInfoScreen infoScreen;
	WaitingScreen waitingScreen;
	
	Client client;
	
	
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
//        System.out.println("Enter name: ");
//        String name = scnr.nextLine();
//        
//        scnr.close();
        	
        this.player = new Player("Placeholder");
        this.player.setNum(-1);
        this.mode = "setup";
        
        this.infoScreen = new PlayerInfoScreen(this, this.player);
        this.waitingScreen = new WaitingScreen(this);
        
        
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
    			this.mode = "starting pieces";
    			Board board = client.getBoard();
    		}
    		
    		break;
    		
    	case "starting pieces":
    		background(0);
    		text("ready", width/2, height/2);
    		
    		break;
    		
    	}
    	
    }
    
    

    
}