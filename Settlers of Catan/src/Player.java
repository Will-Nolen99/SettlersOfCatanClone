import java.io.Serializable;


public class Player implements Serializable {


	private static final long serialVersionUID = -4286864464073293446L;
	String name;
	String picturePath;
	private int[] color = {127, 127, 127};
	int number;
	
	public Player(String name) {
		this.name = name;
	}
	
	
	
	public void setProfilePicture(String fname) {
		this.picturePath = fname;
	}
	
	public int[] getColor() {
		return this.color;
	}
	
	public void setColor(int[] color) {
		this.color = color;
	}
	
	
	@Override
	public String toString() {
		String s = "Name: " + this.name + " Player #" + this.number + ", Color: " + this.color[0] + ", " + this.color[1] + ", " + this.color[2] + " Image: " + this.picturePath;
		return s;
	}
	
	public void setNum(int n) {
		this.number = n;
	}
	
	
	public int  getNum() {
		return this.number;
	}
}
