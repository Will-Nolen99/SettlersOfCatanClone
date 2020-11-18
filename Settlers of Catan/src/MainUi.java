import java.util.ArrayList;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;

public class MainUi {

	private PApplet canvas;
	
	
	private ArrayList<Player> players;
	private Player me;
	private int color;
	private PImage img;
	
	
	public MainUi(PApplet p) {
		
		this.canvas = p;
		
	}
	
	
	public void addPlayers(ArrayList<Player> p ) {
		this.players = p;
	}
	
	public void setMainPlayer(Player me) {
		this.me = me;
		int[] c = me.getColor();
		
		
		this.color = this.canvas.color(c[0], c[1], c[2]);
		
		this.img = this.canvas.loadImage(me.getPicturePath());
		this.img.resize(150, 150);
		
		
		
	}
	
	
	public void drawUserFrame() {
		
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
		this.canvas.rect(0, 0, 500, 250);
		
		//profile picture and outline
		this.canvas.rect(25, 25, 150, 150);
		this.canvas.image(this.img, 25, 25);
		
		//player name
		this.canvas.fill(0);
		this.canvas.textSize(25);
		this.canvas.text(me.getName(), 200, 50);
		this.canvas.text("V.P.: " + me.getPoints(), 200, 100);
		
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
		
		this.canvas.beginShape();
		this.canvas.vertex(200, 180);
		this.canvas.vertex(200, 195);
		this.canvas.vertex(220, 195);
		this.canvas.vertex(220, 180);
		this.canvas.vertex(210, 170);
		this.canvas.vertex(200, 180);
		this.canvas.endShape();
		
		//road piece
		
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
		this.canvas.text(": " + pieces.get("settlement"), 250, 195);
		this.canvas.text(": " + pieces.get("road"), 250, 235);
		this.canvas.pop();
		
	}
	
	public void draw() {
		
		
		drawUserFrame();
		
		
	}
	
	
}
