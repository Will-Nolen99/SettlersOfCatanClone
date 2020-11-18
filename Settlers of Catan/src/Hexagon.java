import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

public class Hexagon implements Serializable {
	
	
	
	/*
	 *    Tile color pallette
	 * 
	 * ocean     3E92CC    62, 146, 204
	 * ore       534B62    83, 75, 98
	 * brick     E65F5C    230, 95, 92
	 * sheep     8CD867    140, 216, 103
	 * wood      3C7C56    60, 124, 86
	 * wheat     FFFD82    255, 253, 130
	 * desert    F8DDA4    248, 221, 164
	 *  
	 */
	
	
	
	private static final long serialVersionUID = -8442021869013284474L;

	private PVector center;
	private String type;
	private int number;
	private Set<BuildingPoint> points = new HashSet<BuildingPoint>();
	private Set<Path> paths = new HashSet<Path>();
	private int color;
	
	private int radius; // temp for now
	
	public Hexagon(String type) {
		this.type = type;	
		this.radius = 60;
	}
	
	public void setCoords(PVector center) {
		this.center = center;
		System.out.println("Hex center at " + this.center.x  + ", " + this.center.y);
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getType() {
		return this.type;
	}
	
	public PVector getCenter() {
		return this.center;
	}
	
	public void setNum(int n) {
		this.number = n;
	}
	
	
	public void draw(PApplet canvas){
		
		canvas.push();
		canvas.strokeWeight(1);
		
		switch (this.type){
		case "ocean":
			canvas.fill(62, 146, 204);
			break;
			
		case "ore":
			canvas.fill(83, 75, 98);
			break;
			
		case "brick":
			canvas.fill(230, 95, 92);
			break;
			
		case "sheep":
			canvas.fill(140, 216, 103);
			break;
			
		case "wood":
			canvas.fill(60, 124, 86);
			break;
			
		case "wheat":
			canvas.fill(255, 253, 130);
			break;
			
		case "desert":
			canvas.fill(248, 221, 164);
			break;
			
		default:
			canvas.fill(0);
			break;

		}
		
		
		canvas.beginShape();
		
		
		for(int angle = 30; angle <= 390; angle += 60) {
			
			float theta = PApplet.radians(angle);
			
			int x = PApplet.floor(this.center.x + radius * PApplet.cos(theta));
			int y = PApplet.floor(this.center.y + radius * PApplet.sin(theta));
			
			canvas.vertex(x, y);
			
		}
		
		
		canvas.endShape();
		
		
		if(!this.type.equals("desert") && !this.type.equals("ocean")) {
			canvas.push();
			
			if(this.number == 6 || this.number == 8) {
				canvas.fill(255, 0, 0);
			}else {
				canvas.fill(0);
			}

			canvas.textSize(20);
			
			int xOffset = 8;
			canvas.text(this.number, this.center.x - xOffset, this.center.y);
			
			canvas.pop();
		}
		
		
		
		canvas.pop();
		
	}

	@Override
	public String toString() {
		return "Hexagon [center=" + center + ", type=" + type + ", number=" + number + ", color=" + color + "]";
	}
	
	
}
