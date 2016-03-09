
public class Main {

	public static void main(String[] args) {
		Hallway doors = new Hallway();
		int doorSeq;
		
		doors.prepDoors();
		
		// Pass 100 doors 100 times; 1st run toggle each door, 2nd run every
		// 2nd door, and so on until only the 100th door is toggled on the 100th run.
		doorSeq = 2;
		for (int i=1; i<100; i++) {
			for (int j=1; j<100;) {
				doors.flop(j);
				j+= doorSeq;
			}
			doorSeq+= 1;
		}
		
		// Print final status of doors in the hallway
		System.out.println("The final state of the doors in the hallway are: ");
		for (int d=0; d<100; d++) {
			System.out.println("Door " + (d+1) + ": " + doors.getStatus(d));
		}
	}

}
