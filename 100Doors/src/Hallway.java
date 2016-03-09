
public class Hallway {

	boolean[] hallway;
	public Hallway() {
		hallway = new boolean[100];
	}
	// Setup new hallway containing 100 closed doors.
	public  void prepDoors() {
		for (int i=0; i<100; i++) {
			hallway[i] = false;
		}
	}
	// Switch the status of the door (closed-open; open-closed).
	public void flop(int door) {
		if (hallway[door] == false) {
			hallway[door] = true;
		}
		else {
			hallway[door] = false;
		}
	}
	// Return the current status of the given door.
	public String getStatus(int door) {
		if (hallway[door] == false) {
			return "closed";
		}
		else {
			return "open";
		}
	}
	
	
}
