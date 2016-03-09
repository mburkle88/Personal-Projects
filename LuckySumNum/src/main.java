import java.io.*;
import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan;
		int inNum;
		calculateLucky calNum;
		
		System.out.println("Enter a number. ");
		scan = new Scanner(System.in);
		inNum = scan.nextInt();
		calNum  = new calculateLucky(inNum);
		String numDig = new Integer(inNum).toString();
		if (numDig > 1) {
			calNum.compute()
		}
	}

}
