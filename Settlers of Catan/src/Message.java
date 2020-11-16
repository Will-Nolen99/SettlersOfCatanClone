import java.io.Serializable;

public class Message implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -680725394326900251L;
	private String contents;
	
	public Message(String m) {
		
		this.contents = m;
		
	}
	
	
	public String getMessage() {
		return this.contents;
	}
	
	
}
