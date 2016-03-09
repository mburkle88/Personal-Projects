
public class Table {
		int T_Length;
		double[][] table;
		
		
	public double[][] buildTable(double thisProbability, int gamesNum) {
		
		//int T_Length = ((int) Math.ceil(gamesNum/2));
		int T_Length = gamesNum;
		
		table = new double[T_Length][T_Length];
		
		// This team
		for (int i=0; i<T_Length-1; i++) {
			table[0][i] = 1.0;
		}
		// Other team 
		for (int i=0; i<T_Length-1; i++) {
			table[i][0] = 0.0;
		}
		
		for (int i = 1; i <T_Length; i++) {
			for (int j = 1; j < T_Length; j++) {
				table[i][j] = ((1-thisProbability)*table[i][j-1]) + ((thisProbability*table[i-1][j]));
			}
		}
				
		return table;
}
	
	public double getTableValue(int a, int b) {

		return table[a][b];
	}

}
