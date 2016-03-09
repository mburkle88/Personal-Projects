/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nonogram;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Rachel
 */
public class Nonogram {
    
    Data data;
    View view;
    Solver solver; 
    String puzzle;
    PuzzleManager pM;
    
    public Nonogram() throws FileNotFoundException {
    	pM = new PuzzleManager();
        //puzzle = "res/Block.txt";
        puzzle = ("res/" + pM.getFirstPuzzle());
        data = new Data(puzzle);
        
        view = new View(this);
        solver = new Solver(this, data); 
   	    	
    }
    
    public void newGamePuzzle() throws FileNotFoundException{
        
    	pM = new PuzzleManager();
    	String selectedPuzzle = pM.getFileName();
    	if (selectedPuzzle == "NOPE"){
    		//Do noting
    	}
    	else{
        
        puzzle = ("res/" + selectedPuzzle);
        System.out.println(puzzle);
        data = new Data(puzzle);
        
        view.dispose();
        view = new View(this);
        } 
    }
    
 
    
    public int getBoardSize(){
    	return data.boardsize;
    }
    
    
    
    public GridArray getPuzzleGridData(){
    	return data.getGrid();
    }
    
    public CluesArray getRowCluesData(){
    	return data.getRows();
    }
   
    
    public CluesArray getColsCluesData(){
    	return data.getCols();
    }
    
    public void updateGridGUI(){
    	view.updateGridGUI();
    }
    
    public int[] getRowLineClues(int line){
    	return getRowCluesData().getLineClues(line);
    	
    }
    
    public int[] getColLineClues(int line){
    	return getColsCluesData().getLineClues(line);
    }
        
    public void runSolver(){
    	System.out.println("Run Solver");
    	updateGridGUI();
    }

	public void clearGrid(){
		data.getGrid().setGridAllUnknownState();
		view.updateGridGUI();
	}
    
    public void updateExplaination(String text){
    	view.addToExplainTextArea(text);
    }

	/**
     * @param args the command line arguments
	 * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
    	Nonogram nono = new Nonogram();
        
    }
    
}
