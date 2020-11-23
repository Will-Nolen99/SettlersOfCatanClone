import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import processing.core.PVector;

public class Path implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489003526199585222L;
	private int[] color;
	private Set<PVector> points;
	private PVector p1, p2;
	private boolean built;
	
	
	
	public Path(PVector p1, PVector p2) {
		

		this.p1 = new PVector();
		this.p2 = new PVector();
		
		this.p1 = p1;
		this.p2 = p2;
		
		this.color = new int[3];
		
		this.points = new HashSet<PVector>();
		
		this.points.add(p1);
		this.points.add(p2);
		
		this.color[0] = 0;
		this.color[1] = 0;
		this.color[2] = 0;
		
		this.built = false;
		
	}
	
	
	
	/**
	 * @return the built
	 */
	public boolean isBuilt() {
		return built;
	}



	/**
	 * @param built the built to set
	 */
	public void setBuilt(boolean built) {
		this.built = built;
	}



	/**
	 * @return the p1
	 */
	public PVector getP1() {
		return p1;
	}



	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(PVector p1) {
		this.p1 = p1;
	}



	/**
	 * @return the p2
	 */
	public PVector getP2() {
		return p2;
	}



	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(PVector p2) {
		this.p2 = p2;
	}



	/**
	 * @return the points
	 */
	public Set<PVector> getPoints() {
		return points;
	}


	/**
	 * @param points the points to set
	 */
	public void setPoints(Set<PVector> points) {
		this.points = points;
	}


	public int[] getColor() {
		return this.color;
	}
	
	public void setColor(int[] c) {
		this.color = c;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(color);
		result = prime * result + ((points == null) ? 0 : points.hashCode());
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
		Path other = (Path) obj;
		if (!Arrays.equals(color, other.color)) {
			return false;
		}
		if (points == null) {
			if (other.points != null) {
				return false;
			}
		} else if (!points.equals(other.points)) {
			return false;
		}
		return true;
	}
	
	
	
	
}
