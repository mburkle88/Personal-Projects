package Nonogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelClueRow extends JPanel{
	
	private int boardsize;
	private CluesArray clues;
	
	public PanelClueRow(CluesArray rowClues) {
		this.boardsize = rowClues.getClues().length;
		this.clues = rowClues;
		this.setLayout(new GridLayout(boardsize, 1,5,5));
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
    		for(int x =0; x< clues.getClues()[y].length; x++){
    			
    			cluesForLine= cluesForLine + clues.getClues()[y][x]+ ", ";
    			
    			}
    		cluesForLine = cluesForLine.substring(0, cluesForLine.length()-2);
    		
    		JPanel cell =new JPanel();
    		cell.setLayout(new BorderLayout());
    		cell.setBorder(new EmptyBorder(0, 10, 0, 10) );
    		JLabel label =new JLabel(cluesForLine);
    		label=increaseFontSize(label);
    		cell.add(label, BorderLayout.LINE_END);
    		this.add(cell);
    		cluesForLine="";
    			
    		}
    	}
    
    private JLabel increaseFontSize(JLabel label){
    	Font f = new Font("Dialog", Font.BOLD, 32);
    	label.setFont(f);
    	return label;
    } 
}
