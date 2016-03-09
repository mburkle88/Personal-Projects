import java.util.*;

/*
 * Mollie Burkle
 * 
 * Take in a string from the command line and determine if it is a palindrome. 
 * Ignore any empty space.
 */
public class main {

	public main(String[] args) {
		Scanner scan;
		String str;
		scan = new Scanner(System.in);
		
		System.out.println("Enter a string." );
		str = scan.nextLine();
		scan.close();
		String newStr = str.replaceAll(" ","");
		System.out.println(newStr);
		boolean call = check(newStr);
		//System.out.println(call);  // Test string parsing
		if (call == false) {
			System.out.println("NOT a palindrome!");
		}
		else {
			System.out.println("A palindrome!");
		}
	}
	// Determine if a palindrome by comparing first and last characters, breaking if a 
	// discrepancy is found.
	public boolean check(String str) {
	int i = 0;
			for (int j=str.length()-1; j>i; j--) {
				System.out.println(str.charAt(i));
				System.out.println(str.charAt(j));
				if (str.charAt(i) != (str.charAt(j))) {
					return false;
				}
				i++;
			}
		return true;
	}
}
