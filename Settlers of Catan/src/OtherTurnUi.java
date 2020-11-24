import processing.core.PApplet;

public class OtherTurnUi {

	
	public OtherTurnUi() {
		
	}
	
	
	public void draw(PApplet canvas, String name) {
		
		
		canvas.push();
		canvas.textSize(25);
		canvas.fill(0);
		
		canvas.text(name + "'s turn", canvas.width / 2, 100);
		
		canvas.pop();
		
		
		
	}
	
	
}
