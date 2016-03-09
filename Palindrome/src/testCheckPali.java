import junit.framework.*;

public class testCheckPali {

	main checkPali;
	String str = "ollo";
	
	public void testSemetry () {
		assert(checkPali.check(str));
		assert (checkPali.check("Race car"));
		assert (checkPali.check("not a palindrome"));
	}

}
