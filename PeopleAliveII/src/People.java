import java.util.*;

public class People {

	int birthYear;
	int deathYear;
	String name;
	Random rdm = new Random();
	
	public People(String name) {
		this.name = name;
		birthYear = rdm.nextInt(2000-1900)+1900;
		deathYear = rdm.nextInt(2000-birthYear)+birthYear;
	}
	
	public int getBirth(String name) {
		return birthYear;
	}
	
	public int getDeath(String name) {
		return deathYear;
	}
	
	public boolean isAlive(int year) {
		if ((year >=birthYear) & (year <= deathYear)) {
			return true;
		}
		return false;
	}
}
