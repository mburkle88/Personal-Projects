import java.util.*;

public class Random_Board {
int nextValue;

public Random_Board() {
}
	// Generate and ArrayList, fill it with random integers, and return.
	public ArrayList<Integer> getBoard(int N, int Z) {
		ArrayList<Integer> board = new ArrayList<Integer>(N);
		Random randBoardGenerator = new Random();
		
		for (int i = 0; i < 6+1; i++) {
			nextValue= randBoardGenerator.nextInt() ;
			board.add(nextValue);
		}
		return board; 
	}
	
}

