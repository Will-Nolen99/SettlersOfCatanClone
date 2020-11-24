import java.util.ArrayList;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;

public class MainUi {

	private PApplet canvas;
	
	
	private ArrayList<Player> players;
	private String name;
	private int color;
	PImage cardIcon;

	
	
	public MainUi(PApplet p, String name) {
		
		this.canvas = p;
		
		this.cardIcon = this.canvas.loadImage("catanFiles/images/cardIcon.png");
		this.cardIcon.resize(50, 50);
		
		this.name = name;
		
	}
	
	
	public void addPlayers(ArrayList<Player> p ) {
		this.players = p;
		
		for(Player player: this.players) {
			player.loadPic(this.canvas);
			
			
			if(player.getName().equals(this.name)) {
				
				
				int[] c = player.getColor();
				
				this.color = this.canvas.color(c[0], c[1], c[2]);
			}
			
		}
		
	}
	
	public void setMainPlayer(String name) {
		this.name = name;
		
	}
	
	
	
	public void drawUserFrame(Player me) {
		
		/*
		 * 
		 * Draw top left information about user
		 * 
		 * 
		 */

		this.canvas.push();
		
		
		//main box
		this.canvas.strokeWeight(5);
		canvas.stroke(0);
		canvas.fill(this.color);
		this.canvas.rect(0, 0, 500, 200);
		
		//profile picture and outline
		this.canvas.rect(25, 25, 150, 150);
		this.canvas.image(me.getPic(), 25, 25);
		
		//player name
		this.canvas.fill(0);
		this.canvas.textSize(25);
		this.canvas.text(me.getName(), 200, 50);
		this.canvas.text("VP: " + me.getPoints(), 200, 100);
		
		Map<String, Integer> pieces = me.getPieces();
		
		//city piece
		this.canvas.strokeWeight(3);
		this.canvas.noFill();
		
		this.canvas.beginShape();
		
		this.canvas.vertex(200, 125);
		this.canvas.vertex(200, 155);
		this.canvas.vertex(240, 155);
		this.canvas.vertex(240, 135);
		this.canvas.vertex(220, 135);
		this.canvas.vertex(220, 125);
		this.canvas.vertex(210, 115);
		this.canvas.vertex(200, 125);
		this.canvas.endShape();
		
		//settlement piece
		
		this.canvas.translate(90, -40);
		
		this.canvas.beginShape();
		
		
		this.canvas.vertex(200, 180);
		this.canvas.vertex(200, 195);
		this.canvas.vertex(220, 195);
		this.canvas.vertex(220, 180);
		this.canvas.vertex(210, 170);
		this.canvas.vertex(200, 180);
		this.canvas.endShape();
		
		//road piece
		
		this.canvas.translate(70, -40);
		this.canvas.beginShape();
		this.canvas.vertex(200, 225);
		this.canvas.vertex(200, 235);
		this.canvas.vertex(240, 235);
		this.canvas.vertex(240, 225);
		this.canvas.vertex(200, 225);
		this.canvas.endShape();
		
		this.canvas.pop();
		
		this.canvas.push();
		this.canvas.fill(0);
		
		
		this.canvas.textSize(23);
		
		this.canvas.text(": " + pieces.get("city"), 250, 155);
		this.canvas.translate(70, -40);
		this.canvas.text(": " + pieces.get("settlement"), 250, 195);
		this.canvas.translate(90, -40);
		this.canvas.text(": " + pieces.get("road"), 250, 235);
		this.canvas.pop();
		
	}
	
	public void drawPlayerFrame(int num, Player player){
		
		int[] c = player.getColor();
		
		int color = this.canvas.color(c[0], c[1], c[2]);
		
		
		this.canvas.push();
		
		this.canvas.translate(this.canvas.width - 250, 210 * num);
		
		//frame
		this.canvas.stroke(0);
		this.canvas.strokeWeight(5);
		
		this.canvas.fill(color);
		this.canvas.rect(0, 0, 250, 210);
		
		this.canvas.push();
		this.canvas.translate(25, 25);
		
		this.canvas.rect(0, 0, 150, 150);
		this.canvas.image(player.getPic(), 0, 0);
		
		this.canvas.pop();
		
		this.canvas.push();
		
		this.canvas.translate(25,  200);
		this.canvas.fill(0);
		this.canvas.textSize(25);
		this.canvas.text(player.getName(),0, 0);
		
		this.canvas.pop();
		
		this.canvas.push();
		this.canvas.translate(190, 25);
		this.canvas.image(this.cardIcon, 0, 0);
		this.canvas.translate(0, 50);
		this.canvas.fill(0);
		this.canvas.textSize(25);
		
		this.canvas.text(player.getTotalCards(), 0,50);
		
		this.canvas.push();
		this.canvas.translate(0, 75);
		
		
		this.canvas.text("VP", 0, 0);
		this.canvas.translate(0, 25);
		this.canvas.text(player.getPoints(), 0, 0);
	
		this.canvas.pop();
		this.canvas.pop();
		
		

		this.canvas.pop();
		
		
		
		
	}
	
	public void drawCards(Player me) {
		
		
		/*
		 *    card color pallette

		 * ore       534B62    83, 75, 98
		 * brick     E65F5C    230, 95, 92
		 * sheep     8CD867    140, 216, 103
		 * wood      3C7C56    60, 124, 86
		 * wheat     FFFD82    255, 253, 130

		 *  
		 */
		
		Map<String, Integer> cards = me.getCards();
		
		
		this.canvas.push();
		
		this.canvas.strokeWeight(5);
		this.canvas.stroke(0);
		this.canvas.fill(0);
		
		this.canvas.translate(5, 190);
		this.canvas.textSize(25);
		
		//wood
		this.canvas.fill(60, 124, 86); 
		this.canvas.rect(0, 0, 90, 100, 10);
		this.canvas.fill(0);
		this.canvas.text(cards.get("wood"), 35, 90);
		this.canvas.text("wood", 15, 40);
		
		
		//wheat
		this.canvas.fill(255, 253, 130); 
		this.canvas.rect(100, 0, 90, 100, 10);
		this.canvas.fill(0);
		this.canvas.text(cards.get("wheat"), 135, 90);
		this.canvas.text("wheat", 110, 40);
		
		//sheep
		this.canvas.fill(140, 216, 103); 
		this.canvas.rect(200, 0, 90, 100, 10);
		this.canvas.fill(0);
		this.canvas.text(cards.get("sheep"), 235, 90);
		this.canvas.text("sheep", 210, 40);
		
		//brick
		this.canvas.fill(230, 95, 92); 
		this.canvas.rect(300, 0, 90, 100, 10);
		this.canvas.fill(0);
		this.canvas.text(cards.get("brick"), 335, 90);
		this.canvas.text("brick", 315, 40);
		
		//ore
		this.canvas.fill(83, 75, 98); 
		this.canvas.rect(400, 0, 90, 100, 10);
		this.canvas.fill(0);
		this.canvas.text(cards.get("ore"), 435, 90);
		this.canvas.text("ore", 425, 40);
		
		this.canvas.pop();
		
		
	}
	
	
	public void draw() {
		
		

		for(int i = 0, count = 0; i < this.players.size(); i++) {
			
			Player player = this.players.get(i);
			
			if(player.getName().equals(this.name)) {
				
				drawCards(player);
				drawUserFrame(player);
				
			}else {
			
				drawPlayerFrame(count, this.players.get(i));
				
				count++;
			}
		}
		
		
		
		
		
		
	}
	
	
}
