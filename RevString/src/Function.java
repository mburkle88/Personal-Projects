import java.util.*; 
import java.io.*;

/*
 * Mollie Burkle
 * Take in a word and return it's reversal.
 */
class Function {  
  String FirstReverse(String str) { 
  
	char[] revArr new char[str.length()];
    int charIndex = str.length();
    
    for (int i=0; i<=str.length()-1; i++) {
      revArr[charIndex]=str.charAt(i);
      charIndex--
        
    }
    String revStr = new String (revArr)
    return revStr;
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    Scanner  s = new Scanner(System.in);
    Function c = new Function();
    System.out.print(c.FirstReverse(s.nextLine())); 
  }   
  
}