
import java.util.*;

public class Greedy extends Player{

	int optionL;
	int optionR;
	
	public Greedy() {
	}
	public Greedy(ArrayList<Integer> board) {
	}
	
	// Return the end of the board with the highest value.
	public int getMove(ArrayList<Integer> board) {
		optionL = board.get(0);
		optionR = board.get(board.size()-1);
		if (optionL >= optionR) {
			return 0;
		}
		else {
			return board.size()-1;
		}
	}
}


