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
		
		this.buildingPoints = new HashSet<BuildingPoint>();
		this.paths = new HashSet<Path>();
		
		this.getTileNum();
		this.makeBoard();
		this.makePlacementPoints();
		this.tileRadius = 30;
	}
	
	
	private void getTileNum() {
		for(int i = 0; i < layers; i++) {
			this.tiles += 6 * i;
		}
	}
	
	
	private void makeBoard() {
		
		//create tile order
		
		this.board = new ArrayList<Hexagon>();
		
		this.board.add(new Hexagon("desert"));
		
		//put minimum amount of resources into deck
		
		for(int i = 0; i < 4; i++) {
			this.board.add(new Hexagon("wheat"));
			this.board.add(new Hexagon("wood"));
			this.board.add(new Hexagon("sheep"));
		}
		
		for(int i = 0; i < 3; i++) {
			this.board.add(new Hexagon("brick"));
			this.board.add(new Hexagon("ore"));
		}
		
		
		//if board is large, add random tiles to the deck
		
		Random choice = new Random();
		
		while(this.board.size() < this.tiles) {
			int pick = choice.nextInt();
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
			
			this.board.add(new Hexagon(type));
			
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
		
		Random rand = new Random();
		
		for(int layer = 0; layer < this.layers; layer++) {
			
			int yCoord = 0;
			int xCoord = (int) PApplet.round((layer * PApplet.sqrt(3) * radius));
			int verticalOffset = (int) PApplet.round((-6 * radius) / 4);
			int horizontalOffset = (int) PApplet.round(-radius * PApplet.sqrt(3) / 2);
			int shift = 0;
			
			for(int hex = 0; hex < layer * 6; hex++) {
				
				PVector point = new PVector();
				point.x = xCoord;
				point.y = yCoord;
				
				centerPoints.add(point);
				this.board.get(hexIndex).setCoords(point);
				this.board.get(hexIndex).setNum(rand.nextInt(11) + 2);
				
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
	
	
	private void makePlacementPoints() {
		
		
		int radius = this.tileRadius;
		
		for(int i = 0; i < this.centerPoints.size(); i++) {
			PVector point = this.centerPoints.get(i);
			
			for(int angle = 30, angle2 = 90; angle < 390; angle += 60, angle += 60) {
				
				float theta = PApplet.radians(angle);
				float theta2 = PApplet.radians(angle2);
				
				int x = PApplet.floor(point.x + radius * PApplet.cos(theta));
				int y = PApplet.floor(point.y + radius * PApplet.sin(theta));
				
				int x2 = PApplet.floor(point.x + radius * PApplet.cos(theta2));
				int y2 = PApplet.floor(point.y + radius * PApplet.sin(theta2));
				
				
				PVector p1 = new PVector();
				PVector p2 = new PVector();
				
				p1.x = x;
				p1.y = y;
				
				p2.x = x2;
				p2.y = y2;
				
				BuildingPoint bp = new BuildingPoint(p1);
				
				this.buildingPoints.add(bp);
				
				Path p = new Path(p1, p2);
				
				this.paths.add(p);
				
				
				
			}
			
			
		}
		

		
		
		
	}
	
	
	
}
