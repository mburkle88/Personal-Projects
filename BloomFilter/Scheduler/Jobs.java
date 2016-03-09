import java.util.*;

public class Jobs {
	Random randint;
	
	public Jobs() {
		randint = new Random();
	}
	
	public int getTaskSize() {
		return randint.nextInt(300-100) + 100;
	}
	
}
