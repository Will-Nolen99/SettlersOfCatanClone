import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Decks {

	
	private Map<String, Integer> decks;
	private Stack<String> devCards;
	
	
	private final int  NUM_CARDS = 19;
	private final int  NUM_SOLDIERS = 14;
	private final int  NUM_ACTION_CARDS = 2;
	private final int NUM_VP = 5;
	
	
	
	
	
	public Decks(int players) {
		
		
		
		this.decks = new HashMap<String, Integer>();
		this.devCards = new Stack<String>();

		this.createNormalDecks();
		this.createDevDeck();

		
	}
	
	
	
	private void createNormalDecks() {
		
		this.decks.put("wood", this.NUM_CARDS);
		this.decks.put("wheat", this.NUM_CARDS);
		this.decks.put("sheep", this.NUM_CARDS);
		this.decks.put("ore", this.NUM_CARDS);
		this.decks.put("brick",this.NUM_CARDS);
	}
	
	private void createDevDeck() {
		
		for(int i = 0; i < this.NUM_SOLDIERS; i++) {
			this.devCards.push("soldier");
		}
		
		for(int i = 0; i < this.NUM_ACTION_CARDS; i++) {
			this.devCards.push("road building");
			this.devCards.push("year of plenty");
			this.devCards.push("monopoly");
		}
		
		for(int i = 0; i < this.NUM_VP; i++) {
			this.devCards.push("victory point");
		}
		
		Collections.shuffle(this.devCards);
	}
	
	
	/**
	 * 
	 * Used to request cards from deck
	 */
	public int request(String type, int amount) {
		
		int given = amount;
		int size = this.decks.get(type);
		
		if(size < amount) {
			given = size;
		}
		
		size -= given;
		
		this.decks.put(type, size);

		return given;
	}
	
	public String drawDev() {
		
		if(this.devCards.size() <= 0) {
			this.createDevDeck();
		}
		
		return this.devCards.pop();
	}
	
}
