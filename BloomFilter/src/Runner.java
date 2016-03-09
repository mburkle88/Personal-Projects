import java.math.*;

public class Runner {

	public static void main(String[] args) {
		ProbFinder probFinder = new ProbFinder();
		
		int thisTeam, otherTeam;
		double probability;
		double result;
		int gamesNum;
		
		probability = 0.25;
		gamesNum = 10;
		thisTeam = 3;
		otherTeam = 5;
		
		probFinder.run(probability, gamesNum);
		
		result = (probFinder.getSpecificProb(0, 6));
		
		System.out.println(result);
	}
}
