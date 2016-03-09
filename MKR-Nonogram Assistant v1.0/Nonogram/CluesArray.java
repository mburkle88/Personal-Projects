package Nonogram;

public class CluesArray {

	private int[][] clues;
	private int boardSize;
	
	public CluesArray(int sizeOfBoard){
		
		clues = new int[sizeOfBoard][];
		boardSize = sizeOfBoard;
	}

	public int[][] getClues() {
		return clues;
	}

	
	public void setClueLine(String cluesForLine, int line) throws InvalidCluesException{
		
		//Process String Line
		String delimiter = ",";
		String[] tokens = cluesForLine.split(delimiter);
		clues[line]= new int[tokens.length];
		
		for (int i = 0; i < tokens.length; i++){
			clues[line][i] = Integer.parseInt(tokens[i]);
			if (!validClues(line)) {
				String message = "INVALID CLUES: ".concat(String.valueOf(line));
				clues[line] = new int[1];
				clues[line][0] = -1;
				throw new InvalidCluesException(message);
			}
				
			// Create new exception for too many/too few clues
			// Make sure that clue "length" is equal or less than boardSize
			}
	}
	
	public int getClueValue(int col, int row){
		return clues[col][row];
	}
	
	public int[] getLineClues(int lineNum){
		return clues[lineNum];
	}
	
	//For Debugging
	public void printClues(){
		String line = "";
		
		for (int i = 0; i < clues.length; i++){
			for (int x = 0; x <clues[i].length; x++){
				line = line + clues[i][x]+",";
			}
			System.out.println(line);
			line = "";
		}
	}
	
	private boolean validClues(int row) {
		int sum = clues[row].length - 1;
		for (int i = 0; i < clues[row].length; i++)
			sum += clues[row][i];
		if (sum > boardSize)
			return false;
		else
			return true;
	}
	
}
