import java.io.*;
import java.util.*;

/*
 * Mollie Burkle
 * 
 */
public class Run {

	public static void main(String[] args) {
		String inStr = "";
		Scanner scan = new Scanner(System.in);
		boolean validStr = false;
		
		//Prompt user for string.
		while (!validStr) {
			System.out.println("Please enter a valid string.");
			inStr = scan.nextLine();
			if (inStr.length()> 0) {
				validStr=true;
			}
		}
		scan.close();
		
		// Parse input string into words
		String trigger = "[ ]+";
		String[] wordBank  = inStr.split(trigger);
		
		// Find largest word
		int maxCount = 0;
		int maxWord = 0;

		for (int i=0; i<wordBank.length; i++) {
			if (wordBank[i].length()> maxCount) {
				maxCount = wordBank[i].length();
				maxWord = i;
			}
		}
		
		System.out.println("The largest word in this string is " + wordBank[maxWord] 
				+ " with " + maxCount + " characters.");
	}

}
