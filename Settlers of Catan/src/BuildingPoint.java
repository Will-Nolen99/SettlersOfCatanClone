
import java.io.Serializable;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PVector;

public class BuildingPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4061249677100782557L;
	private PVector point;
	private int[] color;
	
	private String building;
	
	public BuildingPoint(PVector p) {
		
		this.point = p;
		
		this.color = new int[3];
		this.color[0] = 0;
		this.color[1] = 0;
		this.color[2] = 0;
		
		this.building = "none";
		
	}
	
	
	public PVector getPoint() {
		return this.point;
	}
	
	public int[] getColor() {
		return this.color;
	}
	
	public String getBuilding() {
		return this.building;
	}

	
	public int distance(BuildingPoint bp) {
		
		PVector p1 = this.point;
		PVector p2 = bp.getPoint();
		
		
		
		
		return (int) PApplet.dist(p1.x, p1.y, p2.x, p2.y);
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + Arrays.hashCode(color);
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BuildingPoint other = (BuildingPoint) obj;
		if (building == null) {
			if (other.building != null) {
				return false;
			}
		} else if (!building.equals(other.building)) {
			return false;
		}
		if (!Arrays.equals(color, other.color)) {
			return false;
		}
		if (point == null) {
			if (other.point != null) {
				return false;
			}
		} else if (!point.equals(other.point)) {
			return false;
		}
		return true;
	}
	

	
}
