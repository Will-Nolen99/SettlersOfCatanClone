import java.util.Random;

import processing.core.PApplet;

public class RollUI {

	private PApplet canvas;
	
	private Random d1, d2;
	
	private int val1, val2;
	
	private boolean rolled;
	
	private boolean hovered;
	
	
	public RollUI(PApplet c) {
		
		this.canvas = c;
		
		this.d1 = new Random();
		this.d2 = new Random();
		
		this.val1 = 0;
		this.val2 = 0;
		
		
		rolled = false;
		this.hovered = false;
		
		
		
	}
	
	
	
	
	public void draw() {
		
		this.canvas.push();
		
		
		
		this.canvas.stroke(0);
		this.canvas.strokeWeight(5);
		
		
		if(!this.rolled) {
		
			if(this.hovered) {
				this.canvas.fill(225);
			}else {
				this.canvas.noFill();
			}
			
			
			
			this.canvas.rect(5,  300, 100, 50);
			
			this.canvas.strokeWeight(5);
			
			this.canvas.fill(0);
			this.canvas.textSize(35);
			this.canvas.text("Roll!", 15, 340);
			
			
			this.val1 = this.d1.nextInt(5) + 1;
			this.val2 = this.d2.nextInt(5) + 1;
		
		}
		
		
		//dice
		this.canvas.noFill();
		
		this.canvas.rect(5, 375, 45, 50);
		this.canvas.rect(60, 375, 45, 50);
			
		if(this.val1 == 1) {
			this.drawPip(25, 400);
		}else if(this.val1 == 2) {
			this.drawPip(15, 385);
			this.drawPip(40, 415);
		}else if(this.val1 == 3) {
			this.drawPip(15, 385);
			this.drawPip(40, 415);
			this.drawPip(25, 400);
		}else if(this.val1 == 4) {
			
			this.drawPip(15, 385);
			this.drawPip(40, 415);
			this.drawPip(40, 385);
			this.drawPip(15,  415);
			
		}else if(this.val1 == 5) {
			this.drawPip(15, 385);
			this.drawPip(40, 415);
			this.drawPip(40, 385);
			this.drawPip(15,  415);
			this.drawPip(25, 400);
		}else {
			
			this.drawPip(15, 385);
			this.drawPip(40, 415);
			this.drawPip(40, 385);
			this.drawPip(15,  415);
			this.drawPip(15, 400);
			this.drawPip(40, 400);
			
			
		}
		
		
		
		if(this.val2 == 1) {
			this.drawPip(80, 400);
		}else if(this.val2 == 2) {
			this.drawPip(70, 385);
			this.drawPip(95, 415);
		}else if(this.val2 == 3) {
			this.drawPip(70, 385);
			this.drawPip(95, 415);
			this.drawPip(80, 400);
		}else if(this.val2 == 4) {
			
			this.drawPip(70, 385);
			this.drawPip(95, 415);
			this.drawPip(95, 385);
			this.drawPip(70,  415);
			
		}else if(this.val2 == 5) {
			this.drawPip(70, 385);
			this.drawPip(95, 415);
			this.drawPip(95, 385);
			this.drawPip(70,  415);
			this.drawPip(80, 400);
		}else {
			this.drawPip(70, 385);
			this.drawPip(95, 415);
			this.drawPip(95, 385);
			this.drawPip(70,  415);
			this.drawPip(70, 400);
			this.drawPip(95, 400);
			
			
		}
		

		this.canvas.pop();
		
		
	}
	
	
	public void drawPip(int x, int y) {
		
		this.canvas.push();
		
		this.canvas.stroke(0);
		this.canvas.strokeWeight(5);
		this.canvas.fill(0);
		
		this.canvas.circle(x, y, 8);
		
		this.canvas.pop();
		
		
	}
	
	public void update() {
		int x = this.canvas.mouseX;
		int y = this.canvas.mouseY;
		
		// see if button is hovered;
		
		this.hovered = x > 5 && x < 105 && y > 300 && y < 350;
		
		if(this.canvas.mousePressed && this.hovered) {
			this.rolled = true;
		}
			

	}
	
	
	public int getRoll() {
		return this.val1 + this.val2;
	}
	
	public boolean isRolled() {
		return this.rolled;
	}
	
	
	
	
	
	
	
	
	
}
