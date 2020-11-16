import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class WaitingScreen {
	
	private PApplet canvas;
	private String mode;
	int[] color;
	
	ArrayList<PVector> loadingPoints1 = new ArrayList<PVector>();
	ArrayList<PVector> loadingPoints2 = new ArrayList<PVector>();
	ArrayList<PVector> loadingPoints3 = new ArrayList<PVector>();
	
	public WaitingScreen(PApplet canvas) {
		this.canvas = canvas;
		this.mode = "waiting";
		this.setup();
		
		
	}
	
	public void setColor(int[] c) {
		this.color = c;
		System.out.println("Color set");
	}
	
	
	
	private void setup() {
		
		for(int i = 0; i < this.canvas.width; i++) {
			
			PVector point = new PVector();
			point.x = i;
			point.y = this.canvas.displayHeight / 2;
			point.z = PApplet.map(i, 0, this.canvas.width, 0, 4 * PApplet.TWO_PI);
			
			this.loadingPoints1.add(point);
			
			point = new PVector();
			point.x = i;
			point.y = this.canvas.displayHeight / 2;
			point.z = PApplet.map(i, 0, this.canvas.width, 0, 4 * PApplet.TWO_PI);
			
			this.loadingPoints2.add(point);
			
			point = new PVector();
			point.x = i;
			point.y = this.canvas.displayHeight / 2;
			point.z = PApplet.map(i, 0, this.canvas.width, 0, 4 * PApplet.TWO_PI);
			
			this.loadingPoints3.add(point);
			
		}
	}
	
	
	
	public void draw() {
		
		PApplet canvas = this.canvas;
		
		canvas.background(this.color[0], this.color[1], this.color[2]);
		
		canvas.push();
		
		canvas.textSize(50);
		canvas.text("Wating for other players", canvas.width/3, canvas.height/4);
		
		canvas.colorMode(PApplet.HSB);
		canvas.noFill();
		canvas.strokeWeight(10);
		
		canvas.beginShape();
		
		for(int i = 0; i < loadingPoints1.size(); i++) {
			
			PVector p = loadingPoints1.get(i);
			p.y += 5 * PApplet.sin(p.z);
			p.z += 0.25;
			
			canvas.stroke(100 * PApplet.cos(p.z/100), 100, 100);
			canvas.vertex(p.x, p.y);
		}
		canvas.endShape();
		
		canvas.beginShape();
		
		for(int i = 0; i < loadingPoints2.size(); i++) {
			
			PVector p = loadingPoints2.get(i);
			p.y += 5 * PApplet.sin(p.z);
			p.z += 0.1;
			
			canvas.stroke(100 * PApplet.cos(p.z/100), 100, 100);
			canvas.vertex(p.x, p.y);
		}
		
		canvas.endShape();
		
		canvas.beginShape();
		
		for(int i = 0; i < loadingPoints3.size(); i++) {
			
			PVector p = loadingPoints3.get(i);
			p.y += 5 * PApplet.sin(p.z);
			p.z += 0.17;
			
			canvas.stroke(100 * PApplet.cos(p.z/100), 100, 100);
			canvas.vertex(p.x, p.y);
		}
		
		canvas.endShape();
		
		
		canvas.pop();
		
		
		
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public void setMode(String m) {
		this.mode = m;
	}
	
	
	
	
	
}
