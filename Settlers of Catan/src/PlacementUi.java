
import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

public class PlacementUi {

	private PApplet canvas;
	
	
	private Board board;
	
	private String pieceType;
	
	
	public PlacementUi(PApplet p) {
		this.canvas = p;
	}
	
	public void setBoard(Board b) {
		this.board = b;
	}
	
	public void setType(String s) {
		this.pieceType = s;
	}
	
	
	public void draw(String piece) {
		
		
		if(piece.equals("settlement")) {
			
			Set<BuildingPoint> points = this.board.getBuildingPoints();
			
			for(BuildingPoint point: points) {
				
				if(point.getBuilding().equals("none")) {
					
					PVector coord = point.getPoint();
					
					this.canvas.push();
					
					this.canvas.translate(this.canvas.width/2, this.canvas.height/2);
					
					this.canvas.stroke(0);
					this.canvas.strokeWeight(3);
					this.canvas.noFill();
					
					this.canvas.circle(coord.x, coord.y, 30);
					
					this.canvas.pop();
					
					
				}
				
			}
			
		}
		
		
		
		
	}
	
	
	public String update() {
		
		
		return "";
	}
	
	
}
