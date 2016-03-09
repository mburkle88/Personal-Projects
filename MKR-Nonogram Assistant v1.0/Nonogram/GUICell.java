package Nonogram;

import javax.swing.JPanel;

public class GUICell extends JPanel{
	
	private int row;
	private int col;
	
	public GUICell(int row, int col){
		this.row = row;
		this.col = col;
		
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
}

