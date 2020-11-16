import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import processing.core.PVector;

public class Hexagon implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8442021869013284474L;

	private PVector center;
	private String type;
	private int number;
	private Set<BuildingPoint> points = new HashSet<BuildingPoint>();
	private Set<Path> paths = new HashSet<Path>();
	
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
