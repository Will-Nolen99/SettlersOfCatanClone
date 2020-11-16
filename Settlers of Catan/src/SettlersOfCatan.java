import java.io.IOException;
import java.util.Scanner;

public class SettlersOfCatan {
	
	final static String SERVER = "server";
	final static String GAME = "game";
	
	public static void main(String[] args) throws IOException {
		
		Scanner scnr = new Scanner(System.in);
		String input = "";
		
		do {
		
			System.out.print("Enter 'game' to play Catan or enter 'server' to create a lobby: ");
			input = scnr.nextLine();
		
		}while(!input.equals(SERVER) && !input.equals(GAME));
		
		
		if(input.equals(SERVER)) {
			Server.main(args);
		}else {
			Game.main(args);
		}
		
		scnr.close();
		
		
	}

}
