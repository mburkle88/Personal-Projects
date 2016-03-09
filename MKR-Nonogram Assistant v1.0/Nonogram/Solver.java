/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nonogram;

/**
 *
 * @author Mollie
 * 
 * 
 * DEFINITIONS
 * EMPTY (-1):	an unknown cell
 * BLANK (0):	a white cell
 * FILLED (1):	a black cell
 * BLOCK:	a cluster of filled cells/boxes
 */
public class Solver {

	Nonogram controller;
	GridArray puzzleGrid;
	CluesArray rowCluesArray;
	CluesArray colCluesArray;
	int size;
	int[] rowClues;
	boolean solved = false;
	int[] basicExplanations;
	ExplainMove explain;
	
	public Solver(Nonogram controller, Data data){
		this.controller = controller;
		this.puzzleGrid = data.grid.getGrid();
		this.rowCluesArray = data.getRows();
		this.colCluesArray = data.getCols();
		this.size = data.getBoardsize();
		int[] basicExplanations = new int[3];
	}
	
	
	public String runSolver() {
		String[] basicExplanations;
		
		while(!solved) {
		
        // find a row with clues more than half the size of the board
        // find next move in the given row
        for(int i = size - 1; i >= 0; i--) {
        	rowClues = rowCluesArray.getLineClues(i);
        	if(rowClues[size] == size) {	// if whole line will be filled black
        		for(int x = 0; i <= size; x++) {
        			if(rowCluesArray.getClueValue(i, x) == -1) {
        				puzzleGrid.setCellFillState(x, i);	// make first blank cell found black
        				explain.getExplanation(1);
        			}
        		}
        	}
        	// test for overlap
            if(getClueSum(rowClues) > (size/2)) {    
                LineSolver(rowCluesArray.getLineClues(i), rowClues);
                        break;
                }
            }
        }
        return "null";
        }
	
	// find and return "overlapped' index
    private int LineSolver(int[] row, int[] clues) {
    	
    	// make two copies of the original row, 
    	int[] rowCopyOne = new int[size];
    	int[] rowCopyTwo = new int[size];
    	int cluesCounter = clues[0];
    	
    	for(int i = 0; i <= size; i++) {
    		rowCopyOne[i] = row[i];
    		rowCopyTwo[i] = row[i];
    	}
    	if(clues.length == 1) {		// if there is only ONE block
    		for(int i = 0; i < row.length; i++) {
    			if(cluesCounter == 0) {
    				break;
    			}
	    		if(rowCopyOne[i] == -1) {
	    			rowCopyOne[i] = 1;
	    			cluesCounter -= 1;
	    		}
	    	}
	    				
	    }
    	cluesCounter = 0;
		for(int i = row.length - 1; i <= 0; i++) {
			if(cluesCounter == 0) {
				break;
			}
    		if(rowCopyOne[i] == -1) {
    			rowCopyOne[i] = 1;
    			cluesCounter -= 1;
    		}
    	}
    	
    	return (Integer) null;
    }
	
	public int getClueSum(int[] clues) {
		int sum = 0;
		for(int i = 0; i <= clues.length; i++) {
			sum += clues[i];
		}
		return sum;
		}
	}
    

