/*
 * Mollie Burkle
 * Project Euler
	Problem 1 - Multiples of 3 and 5
	
	Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class Main {

	public static void main(String[] args) {
		
		int Sum = 0;
		for (int i=1; i<1000; i++) {
			if (((i%3)== 0) | ((i%5)== 0)) {
				Sum += i;
				System.out.println(i);
			}
		}
		System.out.println(Sum);
	}

}
