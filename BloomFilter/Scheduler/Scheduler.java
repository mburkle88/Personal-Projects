
import java.util.*;;

public class Scheduler {
	Jobs taskManager;
	int[] tasks;
	int[] taskDurations;
	int delay;
	long runStartTime;
	long runEndTime;
	int taskNum;
	int count;
	
	public Scheduler(int n) {
		delay = 0;
		taskManager = new Jobs();
		tasks = new int[n];
		taskDurations = new int[n];
		this.taskNum = n;
		count = 0;
	}
	
	public void run() {

		// initialize n number of tasks
		for (int x = 0; x < taskNum-1; x++) {
			tasks[x] = taskManager.getTaskSize();
		}
		Arrays.sort(tasks);

		// process each job
		for (int i = taskNum-1; i > 0; i--) {
			count = taskNum - i;
			int p = tasks[i];
			delay += p;
			taskDurations[i] = delay;
			System.out.println("Experiment: "+count+"    Task Size:    "+tasks[i]+"    Duration:    "+taskDurations[i]);
		}
		runEndTime = System.currentTimeMillis();
	}
}
