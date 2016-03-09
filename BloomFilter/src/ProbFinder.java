
	import java.io.*;
import java.util.*;


	public class ProbFinder {
		Table table;
		int tLength;
		double response;
		double[][] thisTable;
		
		public ProbFinder() {
			table = new Table();
			response = 0;
		}

		//
		public double run(double probability, int gamesNum) {
			//tLength = ((int) Math.ceil(gamesNum/2));
			tLength = gamesNum;
			thisTable = table.buildTable(probability, tLength);
			
		
		//
	for (int i=tLength-1; i>tLength-1-i; i--) {
			//System.out.println("i: "+(i)+" games: "+(table.getTableValue(tLength-i, i))+"    ");
			response += thisTable[tLength-i][ i];
			//System.out.println(table.getTableValue(2, 6));
		}	
		return (1-response);}
/*			double results = thisTable[3][5];
		return results;
		}*/
		
		public double getSpecificProb(int n, int m) {
			double results = thisTable[n][m];
			return results;
		}
	}
