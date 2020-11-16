import java.io.Serializable;
import java.util.Arrays;

import processing.core.PVector;

public class Path implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489003526199585222L;
	private PVector p1, p2;
	private int[] color;
	
	
	public Path(PVector p1, PVector p2) {
		
		this.p1 = p1;
		this.p2 = p2;
		this.color = new int[3];
		
		this.color[0] = 0;
		this.color[1] = 0;
		this.color[2] = 0;
		
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(color);
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
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
		if (p1 == null) {
			if (other.p1 != null) {
				return false;
			}
		} else if (!p1.equals(other.p1)) {
			return false;
		}
		if (p2 == null) {
			if (other.p2 != null) {
				return false;
			}
		} else if (!p2.equals(other.p2)) {
			return false;
		}
		return true;
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





	public int[] getColor() {
		return this.color;
	}
	
	public void setColor(int[] c) {
		this.color = c;
	}
	
	
	
	
}
