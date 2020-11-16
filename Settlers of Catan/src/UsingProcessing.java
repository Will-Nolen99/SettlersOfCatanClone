import processing.core.PApplet;
import processing.core.PVector;

public class UsingProcessing extends PApplet {

    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("UsingProcessing");
    }

    // method for setting the size of the window
    public void settings(){
        size(500, 500);
    }

    // identical use to setup in Processing IDE except for size()
    public void setup(){
        background(255);
        stroke(0);
        noFill();
        strokeWeight(1);
    }

    int radius = 30;
    int hex_size = 30;
    int layers = 4;
    
    // identical use to draw in Prcessing IDE
    public void draw(){
    	
    	
    	draw_hexes();
        
    	
    	noLoop();
    	
    }
    
    
    public void draw_hexes() {
    	
    	translate(width/2, height/2);
    	PVector origin = new PVector();
    	origin.x = 0;
    	origin.y = 0;
    	
    	int count = 0;
    	
    	draw_hex(origin);
    	
    	textSize(20);
    	fill(0);
    	text(count, origin.x, origin.y);
    	noFill();
    	
    	count++;
    	
    	point(origin.x, origin.y);
    	
    	for(int layer = 0; layer < 5; layer++) {
    		
    		int yCoord = 0;
    		int xCoord = (int) round((layer * sqrt(3) * radius));
    		int verticalOffset = round((int) (-6 * radius) / 4);
    		int xOffset = (int) round((-radius * sqrt(3) / 2));
    		int shift = 0;
    		
    		for(int hex = 0; hex < layer * 6; hex++) {
    			
    			
    			PVector point = new PVector();
    			point.x = xCoord;
    			point.y = yCoord;
    			draw_hex(point);
    			
    	    	fill(0);
    	    	text(count, point.x - 15, point.y);
    	    	noFill();
    	    	count++;
    			
    			System.out.println(point);
    			
    			if(shift == layer) {
    				verticalOffset = 0;
    				xOffset = round((-sqrt(3) * radius));
   			    }
    			
    			if(shift == 2 * layer) {
    				verticalOffset = round((6 * radius) / 4);
    				xOffset = round((-radius * sqrt(3) / 2));
    			}
    			
    			if(shift == 3 * layer) {
    				xOffset *= -1;
    			}
    			
    			if(shift == 4 * layer) {
       				verticalOffset = 0;
    				xOffset = round((sqrt(3) * radius));
     			}
    			
    			if(shift == 5 * layer) {
    				verticalOffset = round(-(6 * radius) / 4);
    				xOffset = round((radius * sqrt(3) / 2));
    			}
    			

    			yCoord += verticalOffset;
    			xCoord += xOffset;
    			
    			shift++;
    			

    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    public void draw_hex(PVector center) {
    	
    	
    	
    	
    	beginShape();
    	
    	for(int angle = 30; angle <= 390; angle += 60) {
    		
    		float theta = radians(angle);
    		
    		
    		vertex(floor(center.x + radius * cos(theta)), floor(center.y + radius * sin(theta)));
    		
    	}
    	
    	
    	endShape();
    	
    	
    	
    }
    	
    	

    	
    }
