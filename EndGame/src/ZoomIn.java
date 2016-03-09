import java.util.*;


public class ZoomIn extends Player {
	boolean initialboard = false;
	int evenSum = 0;
	int oddSum = 0;
	
	public ZoomIn() {
	}
	

	@Override
	public int getMove(ArrayList<Integer> board) {
		// if player 2, return the index of the largest list end 
		if (board.size()%2!=0) {
			if (board.get(0) >= (board.get(board.size()-1))) {
				return 0;
			}
			else {
				return board.size()-1;
			}
		}

		// if player 1 and the first play of the game, evaluate if the sum of the even
		// index values are larger or smaller than the sum of the odds
		else if (board.size()>= 5) {
			for (int i = 0; i < board.size(); i+=2) {
				evenSum += board.get(i);
			}						
			for (int i=1; i< board.size(); i+=2)
				oddSum += board.get(i);
		}
		
		
		if (evenSum > oddSum) {
			return 0;
		}
		else {
			return board.size()-1;
		}
		
	}
}

