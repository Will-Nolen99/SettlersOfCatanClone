
import java.util.Arrays;
import java.util.Map;
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
	
	public Board getBoard() {
		return this.board;
	}
	
	public void setType(String s) {
		this.pieceType = s;
	}
	
	
	public boolean draw(String piece, Player player, String mode) {
		
		boolean placed = false;
		
		if(piece.equals("settlement")) {
			
			Set<BuildingPoint> points = this.board.getBuildingPoints();
			
			for(BuildingPoint point: points) {
				
				if(point.getBuilding().equals("none")) {
					
					PVector coord = point.getPoint();
					
					boolean tooClose = false;

					for(BuildingPoint other: points) {
						
						PVector otherCoord = other.getPoint();
						
						if(coord != otherCoord && other.getBuilding().equals("settlement")) {
							
							if(PApplet.dist(coord.x, coord.y, otherCoord.x, otherCoord.y) < 70) {
								tooClose = true;
							}
							
						}
					}
					
					if(!tooClose) {
					
					
						this.canvas.push();
						
						
						this.canvas.stroke(0);
						this.canvas.strokeWeight(3);
						this.canvas.noFill();
						
						int extent = 30;
						
						this.canvas.circle(coord.x, coord.y, extent);
						
						this.canvas.pop();
						
						if(this.canvas.mousePressed) {

							int x = this.canvas.mouseX ;
							int y = this.canvas.mouseY;
							
							
							if(PApplet.dist(x, y, coord.x, coord.y) < extent/2) {
								
								
								
								
								
								int count = 0;
								int[] playerColor = player.getColor();
								
								System.out.println("My color: " + Arrays.toString(playerColor));
								for(BuildingPoint otherPoint: points) {
									
									int[] pointColor = otherPoint.getColor();
									
									System.out.println("point color: " + Arrays.toString(pointColor));
									
									if(pointColor[0] == playerColor[0] && pointColor[1] == playerColor[1] && pointColor[2] == playerColor[2]){
										count++;
										System.out.println(count);
									}
								}
								
								

								
								if(count == 1) {
									Map<String, Integer> cards = player.getCards();
									
									System.out.println(cards);
									
									for(Hexagon hex : this.board.getTiles()) {
										
										Set<BuildingPoint> buildingPoints = hex.getBuildingPoints();
										
										
										for(BuildingPoint bp: buildingPoints) {
											
											System.out.print("BP: " + bp);
											//System.out.println("     POINT: " + point);
											
											PVector myCheckPoint = new PVector(); 
											
											myCheckPoint.x = point.getPoint().x - 1920 / 2;
											myCheckPoint.y = point.getPoint().y - 1080 / 2;
											
											System.out.println("     POINT: " + myCheckPoint);
											
											PVector otherCheckPoint = new PVector();
											
											otherCheckPoint.x = bp.getPoint().x;
											otherCheckPoint.y = bp.getPoint().y;
											
											
											
											if(PApplet.dist(myCheckPoint.x, myCheckPoint.y, otherCheckPoint.x, otherCheckPoint.y) < 10) {
												
												String type = hex.getType();
												
												if(cards.containsKey(type)) {
													int num = cards.get(type);
													cards.put(type, num + 1);
													
													System.out.println("Adding " + type);
												}

											}
											
										}
										

											
											
										
									}
									
									System.out.println(cards);
									
								}
								
								point.setBuilding("settlement");
								placed = true;
								
								point.setcolor(player.getColor());
								
								
								
								player.incrementPoints();
								player.decrementPiece("settlement");
								
								this.canvas.delay(250);
		
							}

						}
						
					}
				}
				
			}
			
		} else if (piece.equals("road")) {
			
			Set<Path> paths = this.board.getPaths();
			
			for(Path path: paths) {
				
				PVector p1 = path.getP1();
				PVector p2 = path.getP2();
				
				PVector midPoint = new PVector();
				
				midPoint.x = (p1.x + p2.x) / 2;
				midPoint.y = (p1.y + p2.y) / 2;
				
				boolean drawRoad = false;
				
				
				if(!path.isBuilt()) {
				
					
					
					for(BuildingPoint point: this.board.getBuildingPoints()) {
						
						int[] pointC = point.getColor();
						int[] playerC = player.getColor();
						
						if(pointC[0] == playerC[0] && pointC[1] == playerC[1] && pointC[2] == playerC[2]){
							
						
							if(point.getPoint().equals(p1) || point.getPoint().equals(p2)) {
								
								drawRoad = true;

								
							}
							
						}
						
	
					}
					
					
					
					
					
					for(Path p: this.board.getPaths()) {
							
						if(p != path) {
								
							if(p1.equals(p.getP1()) || p1.equals(p.getP2()) || p2.equals(p.getP1()) || p2.equals(p.getP2())) {
								
								int[] pointC = p.getColor();
								int[] playerC = player.getColor();
								
								if(pointC[0] == playerC[0] && pointC[1] == playerC[1] && pointC[2] == playerC[2]){
								
									drawRoad = true;
								}
							}
								
								
						}
							
					}
					
					
					
					if(drawRoad) {
						this.canvas.push();
						
						this.canvas.strokeWeight(3);
						this.canvas.stroke(0);
						this.canvas.noFill();
						
						if(PApplet.abs(p1.x - p2.x) < 2 || PApplet.abs(p2.x - p1.x) < 2) {
							
							if(p1.y < p2.y) {
								
								this.canvas.rect(p1.x - 5, p1.y + 15, 10, p2.y - p1.y - 30);
								
							}else {
								
								this.canvas.rect(p1.x - 5, p2.y + 15, 10, p1.y - p2.y - 30);
								
							}
							
							
							
						}else{
							
							if(p1.x < p2.x) {
								
								if(p1.y > p2.y) {
									this.canvas.quad(p1.x - 3 + 15, p1.y - 4 - 7, p1.x + 3 + 15, p1.y + 4 - 7, p2.x + 3 - 15, p2.y + 4 + 8, p2.x - 3 - 15, p2.y - 4 + 8);
								}else {
									this.canvas.quad(p1.x - 3 + 15, p1.y + 4 + 7, p1.x + 3 + 15, p1.y - 4 + 7, p2.x + 3 - 15, p2.y - 4 - 8, p2.x - 3 - 15, p2.y + 4 - 8);
								}

							}else {
								
								if(p1.y > p2.y) {
									
									this.canvas.quad(p2.x - 3 + 15, p2.y + 4 + 7, p2.x + 3 + 15, p2.y - 4 + 7, p1.x + 3 - 15, p1.y - 4 - 8, p1.x - 3 - 15, p1.y + 4 - 8);
									
								}else {
									this.canvas.quad(p2.x + 3 + 15, p2.y + 4 - 7, p2.x - 3 + 15, p2.y - 4 - 7, p1.x - 3 - 15, p1.y - 4 + 8, p1.x + 3 - 15, p1.y + 4 + 8);
								}
								
								
							}
							
						}

						
						
						
						this.canvas.pop();
						
						
						if(this.canvas.mousePressed) {
							int x = this.canvas.mouseX;
							int y = this.canvas.mouseY;
							
							
							if(PApplet.dist(x, y, midPoint.x, midPoint.y) < 30) {
								path.setBuilt(true);
								path.setColor(player.getColor());
								
								placed = true;
								player.decrementPiece("road");
								
								System.out.println("Built");
								this.canvas.delay(250);
								
							}
							
							
						}
						
						
					}
					

				}
				

				
			} 
			
			
		}
		return placed;
		
	}
	
	
	public String update() {
		
		
		return "";
	}
	
	
}
