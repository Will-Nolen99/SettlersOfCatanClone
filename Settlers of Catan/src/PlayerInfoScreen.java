import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class PlayerInfoScreen {

	private PApplet canvas;
	ArrayList<Button> buttons = new ArrayList<Button>();
	int[] color = {127, 127, 127};
	int picNum = 1;
	final int numPics = 334;
	Player player;
	String status;
	
	public PlayerInfoScreen(PApplet canvas, Player player) {
		this.canvas = canvas;
		this.player = player;
		
		Random r = new Random();
		this.color[0] = r.nextInt(256);
		this.color[1] = r.nextInt(256);
		this.color[2] = r.nextInt(256);
		picNum = r.nextInt(numPics) + 1;
		
		
		
		this.status = "drawing";
		
		createButtons();
		

	}
	
	private void createButtons() {
		
		PVector coords = new PVector();
        

        coords.x = 25;
        coords.y  = 25;
        Button upR = new Button(coords, "square", 50, this.canvas, "upR");
        upR.setColor(198, 10, 30);
        
        coords = new PVector();
        
        coords.x = 125;
        coords.y  = 25;
        Button upG = new Button(coords, "square", 50, this.canvas, "upG");
        upG.setColor(77, 210, 145);
        
        coords = new PVector();
        
        coords.x = 225;
        coords.y  = 25;
        Button upB = new Button(coords, "square", 50, this.canvas, "upB");
        upB.setColor(10, 96, 228);
        
        coords = new PVector();
        
        coords.x = 25;
        coords.y  = 125;
        Button downR = new Button(coords, "square", 50, this.canvas, "downR");
        downR.setColor(198, 10, 30);
        
        coords = new PVector();
        
        coords.x = 125;
        coords.y  = 125;
        Button downG = new Button(coords, "square", 50, this.canvas, "downG");
        downG.setColor(77, 210, 145);
        
        coords = new PVector();
        
        coords.x = 225;
        coords.y  = 125;
        Button downB = new Button(coords, "square", 50, this.canvas, "downB");
        downB.setColor(10, 96, 228);
        
        coords = new PVector();
        
        coords.x = 25;
        coords.y = 250;
        Button submit = new Button(coords, "rectangle", 250, this.canvas, "submit");
        submit.setHeight(50);
        submit.setColor(255, 255, 255);
        
        coords = new PVector();
        
        coords.x = 350;
        coords.y = 250;
        Button leftPic = new Button(coords, "square", 50, this.canvas, "leftPic");
        leftPic.setColor(255, 255, 255);
        
        coords = new PVector();
        
        coords.x = 750;
        coords.y = 250;
        Button rightPic = new Button(coords, "square", 50, this.canvas, "rightPic");
        rightPic.setColor(255, 255, 255);
		
        this.buttons.add(upR);
        this.buttons.add(upG);
        this.buttons.add(upB);
        this.buttons.add(downR);
        this.buttons.add(downG);
        this.buttons.add(downB);
        this.buttons.add(submit);
        this.buttons.add(leftPic);
        this.buttons.add(rightPic);
		
	}
	
	public void draw() {
		
		PApplet canvas = this.canvas;
		canvas.background(this.color[0], this.color[1], this.color[2]);
		String pressed = "";
		
		String path = "catanFiles/images/profile_picture" + picNum + ".png";
		PImage img = canvas.loadImage(path);
		
		
		canvas.push();
		
		canvas.stroke(4);
		canvas.fill(255);
		
		canvas.rect(0, 0, 300, 325);
		canvas.rect(325, 0, 500, 325);
		
		canvas.textSize(40);
		canvas.fill(0);
		canvas.text("Choose color", 25, 230);
		canvas.text("Choose picture", 425, 285);
		
		canvas.pop();
		
		canvas.push();
		
		canvas.fill(0);
		canvas.textSize(20);
		canvas.text(this.color[0], 35, 105);
		canvas.text(this.color[1], 135, 105);
		canvas.text(this.color[2], 235, 105);
		
		
		canvas.pop();
		
		img.resize(200, 200);
		canvas.image(img, 450, 25);

		
		
		
		for(Button button: this.buttons) {
			button.update();
			button.draw();
			
			if(canvas.mousePressed && button.isHovered()) {
				
				pressed = button.getName();
				
				switch(pressed) {
				
					case "upR":
						if(this.color[0] < 255) {
							this.color[0]++;
						}
						break;
					
					case "downR":
						if(this.color[0] > 0) {
							this.color[0]--;
						}
						break;
						
					case "upG":
						if(this.color[1] < 255) {
							this.color[1]++;
						}
						break;
					
					case "downG":
						if(this.color[1] > 0) {
							this.color[1]--;
						}
						break;
						
					case "upB":
						if(this.color[2] < 255) {
							this.color[2]++;
						}
						break;
					
					case "downB":
						if(this.color[2] > 0) {
							this.color[2]--;
						}
						break;
						
					case "leftPic":
						if(picNum == 1) {
							picNum = this.numPics;
						}else {
							picNum--;
						}
						canvas.delay(100);
						break;
						
					case "rightPic":
						if(picNum == this.numPics) {
							picNum = 1;
						}else {
							picNum++;
						}
						canvas.delay(100);
						break;
						
					case "submit":
						
						this.status = "finished";
						this.player.setColor(this.color);
						this.player.setProfilePicture(path);
					
				
				}
				
				
			}
			
			
			//Draw button labels here
			
			canvas.push();
			
			canvas.fill(0);
			canvas.textSize(30);
			canvas.text("Submit", 100, 285);
			
			canvas.text("+", 38, 59);
			canvas.text("+", 138, 59);
			canvas.text("+", 238, 59);
			
			canvas.text("-", 41, 160);
			canvas.text("-", 141, 160);
			canvas.text("-", 241, 160);
			
			canvas.text("<", 362, 282);
			canvas.text(">", 765, 282);
			
			
			
			canvas.pop();
			

			
		}
		
		
	}
	
	public String getStatus() {
		return this.status;
	}
}
