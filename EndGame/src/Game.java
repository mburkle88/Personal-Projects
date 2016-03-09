import java.util.*;

public class Game {
	
	int p1_choice;
	int p2_choice;
	int p1_total = 0;
	int p2_total = 0;
	
	public Game () {
	}
	public Game(Player p1, Player p2, ArrayList<Integer> board) {
	}
	
	public float playGame(Player p1, Player p2, ArrayList<Integer> board) {
		for (int i=0; i<6; i++) {
			// if current round is odd (1/3/5/etc) it is player one's move
			if (i%2 == 0) {
				p1_choice = p1.getMove(board);
				p1_total += board.get(p1_choice);
				board.remove(p1_choice);
			}
			else {
				p2_choice = p2.getMove(board);
				p2_total += board.get(p2_choice);
				board.remove(p2_choice);
		}
			
	}	
		// Determine game outcome for player 1 and return reflecting float score.
		if (p1_total > p2_total) {
			return (float) 1.0;
		}
		else if (p1_total == p2_total) {
			return (float) 0.5;
		}
		else {
			return (float) 0.0;
		}
	}
	
}
