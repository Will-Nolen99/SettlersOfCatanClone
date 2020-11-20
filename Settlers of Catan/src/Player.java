import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;


public class Player implements Serializable {


	private static final long serialVersionUID = -4286864464073293446L;
	private String name;
	private String picturePath;
	private int points;
	private int[] color = {127, 127, 127};
	private int number;
	private int totalCards;
	
	private PImage pic;
	
	private Map<String, Integer> cards;
	
	private Map<String, Integer> pieces;
	
	public Player(String name) {
		this.name = name;
		this.points = 0;
		
		this.pieces = new HashMap<String, Integer>();
		
		this.pieces.put("city", 4);
		this.pieces.put("settlement", 5);
		this.pieces.put("road", 15);
		
		this.cards = new HashMap<String, Integer>();
		this.totalCards = 0;
		
		this.cards.put("wood", 0);
		this.cards.put("wheat", 0);
		this.cards.put("sheep", 0);
		this.cards.put("brick", 0);
		this.cards.put("ore", 0);

	}
	
	
	
	public void setProfilePicture(String fname) {
		this.picturePath = fname;
	}
	
	public void loadPic(PApplet canvas) {
		this.pic = canvas.loadImage(this.picturePath);
		this.pic.resize(150, 150);
	}
	
	public PImage getPic() {
		return this.pic;
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
	
	public int getTotalCards() {
		return this.totalCards;
	}
	
	public Map<String, Integer> getCards(){
		return this.cards;
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
