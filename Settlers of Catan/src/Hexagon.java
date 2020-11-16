import processing.core.PVector;

public class Hexagon {
	
	
	private PVector center;
	private String type;
	private int number;
	
	public Hexagon(String type) {
		this.type = type;
	}
	
	public void setCoords(PVector center) {
		this.center = center;
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
	
}
