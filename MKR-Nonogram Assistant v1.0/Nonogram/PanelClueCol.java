package Nonogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelClueCol extends JPanel{

	private int boardsize;
	private CluesArray clues;
	
	public PanelClueCol(CluesArray rowClues) {
		this.boardsize = rowClues.getClues().length;
		this.clues = rowClues;
		this.setLayout(new GridLayout(1,boardsize,5,5));
		this.setBackground(Color.BLACK);
		updateGUIPuzzle();
	}
	
    public void updateGUIPuzzle(){
    	this.removeAll();
    	createGirdCells();    	
    }
    
    public void createGirdCells(){
    	String cluesForLine="";
    	
    	for(int y=0;y<boardsize; y++){
    		JPanel col =new JPanel();
    		col.setLayout(new BorderLayout());
    		JPanel cell =new JPanel();
    		cell.setLayout(new GridLayout(clues.getClues()[y].length,1));
    		cell.setBorder(new EmptyBorder(0, 15, 0, 0 ) );
    		for(int x =0; x< clues.getClues()[y].length; x++){
    			int temp = clues.getClues()[y][x];
    			cluesForLine= String.valueOf(temp);
    			JLabel label =new JLabel(cluesForLine);
        		label=increaseFontSize(label);
        		cell.add(label);
    		
    			}
    		col.add(cell, BorderLayout.PAGE_END);
    		this.add(col);
    		}
    	}
    
    private JLabel increaseFontSize(JLabel label){
    	Font f = new Font("Dialog", Font.BOLD, 32);
    	label.setFont(f);
    	return label;
    } 
}
