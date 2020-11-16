import processing.core.PApplet;
import processing.core.PVector;

public class Button {
	
	private PVector coords;
	private String shape;
	private int width;
	private String name;
	
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	private int height;
	private int outline;
	private PApplet canvas;
	
	private boolean hovered;
	
	public Button(PVector coords, String shape, int width, PApplet canvas, String name) {
		
		this.coords = coords;
		this.shape = shape;
		this.width = width;
		this.outline = 0;
		this.canvas = canvas;
		
		
		this.hovered = false;
		this.name = name;		
		
		this.height = width;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setColor(int r, int g, int b) {
		this.red = r;
		this.blue = b;
		this.green = g;
	}
	
	
	public boolean isHovered() {
		return this.hovered;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void draw() {
		
		this.canvas.push();
		
		this.canvas.stroke(this.outline);
		this.canvas.strokeWeight(2);
		
		
		int red = this.red;
		int green = this.green;
		int blue = this.blue;
		
		if(this.hovered) {
			red -= 20;
			green -= 20;
			blue -= 20;
		}
		
		
		this.canvas.fill(red, green, blue);
	
		
		if(this.shape.equals("square")) {
			this.canvas.square(this.coords.x, this.coords.y, this.width);
			
		}else if(this.shape.equals("rectangle")) {
			this.canvas.rect(this.coords.x, this.coords.y, this.width, this.height);
		}

		this.canvas.pop();
		
	}
	
	
	public void update() {
		
		int x = this.canvas.mouseX;
		int y = this.canvas.mouseY;
		
		if(this.shape.equals("square") || this.shape.equals("rectangle")) {
			
			if(x < this.coords.x + this.width && x > this.coords.x & y < this.coords.y + this.height && y > this.coords.y) {
				this.hovered = true;
			}else {
				this.hovered = false;
			}
			
			
			
		}
		
		
	}
	
	
}
