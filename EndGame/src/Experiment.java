import java.util.*;

public class Experiment {

	float gameOutcome;
	float results;
	int n = 6;
	int z = 100;
	
	Game currentGame = new Game();
	ArrayList<Integer> gameBoard = new ArrayList<Integer>();
	Random_Board boardGenerator = new Random_Board();
	
	// For every game, generate a new board, play, and add results to outcome sum.
	public float run(Player p1, Player p2, int num_games) {
		for (int i=1; i<= num_games; i++) {
			gameBoard = boardGenerator.getBoard(n, z);
			gameOutcome += currentGame.playGame(p1, p2, gameBoard);
			//System.out.println("Outcome: " + gameOutcome);	//TEST
		}
		return gameOutcome;
		
	}
}
