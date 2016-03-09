
public class Run {

	/*
	Mollie Burkle
	
	Given a list of people with their birth and end years 
	(all between 1900 and 2000), find the year with the most 
	number of people alive.
	*/
	public static void main(String[] args) {
		People[] ppl = new People[10];
		String name;

		
		for (int i = 0; i < ppl.length; i++) {
			name = "p" + i;
			ppl[i] = new People(name);
			/* TESTING : Print Person data */
			System.out.println(ppl[i].name);
			System.out.println(ppl[i].birthYear);
			System.out.println(ppl[i].deathYear);
			
		}
		
		// Calculate the year with the most living people
		int bestYear = 1900;
		int masterCount = 0;
		int yearCount;
		for (int i=1900; i<=2000; i++) {
			yearCount = 0;
			//System.out.println("Year: " + i);
			//System.out.println("MasterCount " + masterCount);
			for (int j=0; j<ppl.length; j++) {
				if (ppl[j].isAlive(i)) {
			
					yearCount++;
				}
			}
			if (yearCount > masterCount) {
				masterCount = yearCount;
				bestYear = i;
				System.out.println("Current bestYear: " + bestYear + "yearCount: " + yearCount);
			}
		}
		
		System.out.println("The year with the most living is: " + bestYear);

	}

}
