import java.util.*;
public class Runner {

	public static void main(String[] args) {
		Random randint = new Random();
		
		// run 10 experiments
		for (int i = 0; i < 9; i++) {
			Scheduler scheduler = new Scheduler(randint.nextInt(300-100)+100);
			scheduler.run();
		}

	}

}
