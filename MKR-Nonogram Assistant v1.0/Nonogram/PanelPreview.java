package Nonogram;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelPreview extends JPanel{

	
	private static Color UNKNOWN_COLOR = Color.LIGHT_GRAY;
	private static Color FILL_COLOR = Color.BLACK;
	
	private GridArray dataGrid;  //GridArray from Data
	private int gridBoardSize;
	
	
	public PanelPreview(int boardsize, GridArray gridData){
		this.dataGrid = gridData;
		this.gridBoardSize=boardsize;
		this.setLayout(new GridLayout(gridBoardSize, gridBoardSize,0,0));
		//this.setBackground(Color.BLACK);
		//this.setBorder(new EmptyBorder(10, 100, 100, 10) );
	}
	
	
	
	public void updatePreview(){
    	this.removeAll();
    	createGirdCells();    	
    }
    
    
    public void createGirdCells(){
    	
    	for(int y=0;y<gridBoardSize; y++){
			for(int x =0; x< gridBoardSize; x++){
				JPanel cell =new JPanel();
				//cell.setSize(10, 20);
				if (dataGrid.getCellValue(y,x) == 1){
					cell.setBackground(FILL_COLOR);
					}
				else {
					cell.setBackground(UNKNOWN_COLOR);}
				this.add(cell);
			}
		}
    }
}
