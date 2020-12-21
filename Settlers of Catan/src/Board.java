import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PVector;

public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 935097427564983659L;
	private int layers, tiles;
	private ArrayList<Hexagon> board;
	private ArrayList<PVector> centerPoints;
	
	private Set<BuildingPoint> buildingPoints;
	
	private Set<Path> paths;
	
	private int tileRadius;
	
	
	public Board(int layers) {
		
		this.layers = layers;
		this.tiles = 1;
		
		this.tiles = 3 * layers * layers - 3 * layers + 1;

		
		this.buildingPoints = new HashSet<BuildingPoint>();
		this.paths = new HashSet<Path>();
		
		this.tileRadius = 60;
		
		this.makeBoard();
		

	}
	
	public Set<BuildingPoint> getBuildingPoints(){
		return this.buildingPoints;
	}
	
	public ArrayList<Hexagon> getTiles(){
		return this.board;
	}
	
	
	private void makeBoard() {
		
		//create tile order
		
		this.board = new ArrayList<Hexagon>();
		
		Hexagon desert = new Hexagon("desert");
		
		desert.robberFlip();
		
		this.board.add(desert);
		
		//put resources into deck
		
		Random choice = new Random();
		
		
		
		//assign that tile a random number with catan distribution
		ArrayList<Integer> nums = new ArrayList<Integer>();
		ArrayList<Hexagon> hexes = new ArrayList<Hexagon>();
		
		for(int i = 0; i < 10000; i++) {
			int pick = choice.nextInt(18);
			
			int num = 0;
			
			if(pick < 1) {
				num = 2;
			}else if(pick < 3) {
				num = 3;
			}else if(pick < 5) {
				num = 4;
			}else if(pick < 7) {
				num = 5;
			}else if(pick < 9) {
				num = 6;
			}else if(pick < 11) {
				num = 8;
			}else if(pick < 13) {
				num = 9;
			}else if(pick < 15){
				num = 10;
			}else if(pick < 16) {
				num = 11;
			}else {
				num = 12;
			}
			
			nums.add(num);
			
			pick = choice.nextInt(18);
			String type = "";
			
			if(pick < 4) {
				type = "wheat";
			}else if(pick < 8) {
				type = "wood";
			}else if(pick < 12) {
				type = "sheep";
			}else if(pick < 15) {
				type = "brick";
			}else {
				type = "ore";
			}
			
			
			Hexagon hex = new Hexagon(type);
			hexes.add(hex);
			
			
			
		}
		
		
		Collections.shuffle(nums);
		Collections.shuffle(hexes);
		
		
		
		
		
		while(this.board.size() < this.tiles) {

			
			Hexagon hex = hexes.get(0);
			hexes.remove(0);
			
			hex.setNum(nums.get(0));
			nums.remove(0);
			
			this.board.add(hex);
			
		}
		
		Collections.shuffle(this.board);
		
		//create hex center points
		this.centerPoints = new ArrayList<PVector>();
		
		int radius = this.tileRadius;

		
		PVector origin = new PVector();
		origin.x = 0;
		origin.y = 0;
		
		this.centerPoints.add(origin);
		
		int hexIndex = 0;
		this.board.get(hexIndex).setCoords(origin);
		hexIndex++;
		
		boolean pointsMade = false;
		
		
		for(int layer = 1; layer < this.layers + 1 ; layer++) {
			
			int yCoord = 0;
			int xCoord = (int) PApplet.round((layer * PApplet.sqrt(3) * radius));
			int verticalOffset = (int) PApplet.round((-6 * radius) / 4);
			int horizontalOffset = (int) PApplet.round(-radius * PApplet.sqrt(3) / 2);
			int shift = 0;
			
//			System.out.println("Layer: " + layer);
//			System.out.println("sqrt 3: " + PApplet.sqrt(3));
//			System.out.println("radius: " + radius);
//			
//			System.out.println(PApplet.round((layer * PApplet.sqrt(3) * radius)));
//			System.out.println("xCoord: " + xCoord + " yCoord: " + yCoord);
			
			
			for(int hex = 0; hex < layer  * 6; hex++) {
				
				PVector point = new PVector();
				point.x = xCoord;
				point.y = yCoord;
				
				centerPoints.add(point);
				
				
				
				Hexagon current = new Hexagon("ocean");
				
				if(layer != layers) {
				
					current = this.board.get(hexIndex);
				}else {
					
					if(!pointsMade) {
						this.makePlacementPoints();
						pointsMade = true;
					}
					
					this.board.add(current);
				}
				
				//System.out.println("Coords set at " + point);
				current.setCoords(point);

				
				if(shift == layer) {
					verticalOffset = 0;
					horizontalOffset = PApplet.round(-PApplet.sqrt(3) * radius);
				}else if(shift == 2 * layer) {
					verticalOffset = PApplet.round((6 * radius) / 4);
					horizontalOffset = PApplet.round((-radius * PApplet.sqrt(3)) / 2);
				}else if(shift == 3 * layer) {
					horizontalOffset *= -1;
				}else if(shift == 4 * layer) {
					verticalOffset = 0;
					horizontalOffset = PApplet.round(PApplet.sqrt(3) * radius);
				}else if(shift == 5 * layer) {
					verticalOffset = PApplet.round((-6 * radius) / 4);
					horizontalOffset = PApplet.round((radius * PApplet.sqrt(3)) / 2);
				}
				
				yCoord += verticalOffset;
				xCoord += horizontalOffset;
				
				
				shift++;
				hexIndex++;
				
			}
			
			
		}
	

		
		
	}
	
	public Set<Path> getPaths() {
		return this.paths;
	}
	
	
	private void makePlacementPoints() {
		
		
		int radius = this.tileRadius;
		
		for(int i = 0; i < this.centerPoints.size() - 1; i++) {
			PVector point = this.centerPoints.get(i);
			
			
			for(int angle = 30, angle2 = 90; angle < 390; angle += 60, angle2 += 60) {
				
				float theta = PApplet.radians(angle);
				float theta2 = PApplet.radians(angle2);
				
				int x = PApplet.floor(point.x + radius * PApplet.cos(theta));
				int y = PApplet.floor(point.y + radius * PApplet.sin(theta));
				
				int x2 = PApplet.floor(point.x + radius * PApplet.cos(theta2));
				int y2 = PApplet.floor(point.y + radius * PApplet.sin(theta2));
				
				
				PVector p1 = new PVector();
				PVector p2 = new PVector();
				
				
				
				p1.x = x + 1920 / 2;
				p1.y = y + 1080 / 2;
				
				p2.x = x2 + 1920 / 2;
				p2.y = y2 + 1080 / 2;
				
				System.out.println(p1);
				
				BuildingPoint bp = new BuildingPoint(p1);
				
				boolean place = true;
				
				if(this.buildingPoints.size() > 0) {
					for(BuildingPoint bpi:this.buildingPoints) {
						
						if(bp.distance(bpi) < 10) {
							
							//System.out.println("Distance from other points: " + bp.distance(bpi));
							place = false;
						}
						

					
					}	
				}
				
				
				if(place) {
					this.buildingPoints.add(bp);
				}
				
				
				
				//Path p = new Path(p1, p2);
				
				//System.out.print(p);
				
				//this.paths.add(p);
				
			}
			
			
			for(BuildingPoint p1: this.buildingPoints) {
				
				for(BuildingPoint p2: this.buildingPoints) {
					
					if(p1 != p2) {
						
						PVector p1c = p1.getPoint();
						PVector p2c = p2.getPoint();
						
						if(PApplet.dist(p1c.x, p1c.y, p2c.x, p2c.y) < 70) {
							
							Path p = new Path(p1c, p2c);
							this.paths.add(p);
							
						}
						
						
						
					}
					
					
					
					
				}
				
				
			}
			
			
			System.out.println();
			
		}

	}
	
	

	
	
	public void draw(PApplet canvas) {


		canvas.push();
		
		canvas.translate(canvas.width/2, canvas.height/2);
		
		for(Hexagon hex: this.board) {
			hex.draw(canvas);
		}
		canvas.pop();
		
		int x = canvas.mouseX;
		int y = canvas.mouseY;
		
		//temp for making measurements on canvas
		
		canvas.push();
		canvas.fill(0);
		canvas.textSize(50);
		canvas.text("X: " + x, 100, 800);
		canvas.text("Y: " + y, 100, 900);
		canvas.pop();
		
		
		
		for(Path path: this.paths) {
			
			canvas.push();
			
			
			
			
			int[] color = path.getColor();
			
			PVector p1 = path.getP1();
			PVector p2 = path.getP2();
			
			if(color[0] == 0 && color[1] == 0 && color[2] == 0) {
				canvas.strokeWeight(1);
			}else {
				canvas.strokeWeight(15);
				canvas.stroke(0);
				canvas.line(p1.x, p1.y, p2.x, p2.y);
				canvas.strokeWeight(12);
				
				canvas.stroke(color[0], color[1], color[2]);
				
				canvas.line(p1.x, p1.y, p2.x, p2.y);
			}
			
			

			
			
			
			
			canvas.pop();
			
			
		}
		
		
		for(BuildingPoint p: this.buildingPoints) {
			
			String building = p.getBuilding();
			int[] color = p.getColor();
			PVector coords = p.getPoint();
			

			
			if(building.equals("settlement")) {
				

				int buildX = (int) (coords.x - 10);
				int buildY = (int) coords.y - 5 ;
				
				
				canvas.push();
				canvas.stroke(0);
				canvas.strokeWeight(3);
				
				canvas.fill(color[0], color[1], color[2]);
				
				canvas.beginShape();
				
				canvas.vertex(buildX, buildY);
				canvas.vertex(buildX, buildY + 15);
				canvas.vertex(buildX + 20, buildY + 15);
				canvas.vertex(buildX + 20, buildY);
				canvas.vertex(buildX + 10, buildY - 10);
				canvas.vertex(buildX, buildY);
				
				
				canvas.endShape();
				canvas.pop();
				
			}
			
			
			
			
		}
		
		
		
	}
	
	
	
	
}
