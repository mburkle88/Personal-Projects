import java.io.*;
import java.util.*;

/*
 * Mollie Burkle
 * 
	Evaluate Greedy versus ZoomIn approach to algorithms through runs of Minimax.
	Let user define the number of simulations run. Print out the scores.
 */
public class DriverFnt {

	public static void main (String[] args) {
		Player greedy = new Greedy();
		Player zoomIn = new ZoomIn();
		float results_RndOne;
		float results_RndTwo;

		// Read in specific number of trial games per experiment from user.
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.print("Please enter the number of games you wish to run: " );
		int num_games = scanner.nextInt();
		scanner.close();
		
		// Initialize and run both Greedy and ZoomIn experiments.
		Experiment experiment = new Experiment();
		results_RndOne = experiment.run(greedy, zoomIn, num_games);	
		Experiment experiment2 = new Experiment();
		results_RndTwo = experiment2.run(zoomIn, greedy, num_games);

		
		//System.out.print(results_RndOne);// TEST
		
		// Display the results of both experiment runs.
		System.out.println("Results greedy experiment: ");
		System.out.println("Player one won "+ results_RndOne +" out of " + num_games + " games." );
		System.out.println("Results zoomIn experiment: ");
		System.out.println("Player one won "+ results_RndTwo +" out of " + num_games + " games." );
	}
}
