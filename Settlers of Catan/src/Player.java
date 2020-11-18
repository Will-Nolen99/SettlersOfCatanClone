import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Player implements Serializable {


	private static final long serialVersionUID = -4286864464073293446L;
	private String name;
	private String picturePath;
	private int points;
	private int[] color = {127, 127, 127};
	private int number;
	
	private Map<String, Integer> pieces;
	
	public Player(String name) {
		this.name = name;
		this.points = 0;
		
		this.pieces = new HashMap<String, Integer>();
		
		this.pieces.put("city", 4);
		this.pieces.put("settlement", 5);
		this.pieces.put("road", 15);
	}
	
	
	
	public void setProfilePicture(String fname) {
		this.picturePath = fname;
	}
	
	public Map<String, Integer> getPieces(){
		return this.pieces;
	}
	
	public void incrementPoints() {
		this.points++;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int[] getColor() {
		return this.color;
	}
	
	public void setColor(int[] color) {
		this.color = color;
	}
	
	public String getPicturePath() {
		return this.picturePath;
	}
	
	public String getName() {
		return this.name;
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
