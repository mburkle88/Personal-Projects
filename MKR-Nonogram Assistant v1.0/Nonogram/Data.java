/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nonogram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Rachel
 */
public class Data {
	
	String puzzleTitle;
	int boardsize;
	GridArray grid;
	CluesArray rows;
	CluesArray cols;
	
	public Data(String puzzle) throws FileNotFoundException{
		//Read Puzzle
		FileReader fr = new FileReader(puzzle);
		BufferedReader textReader = new BufferedReader(fr);
		
		String aLine;
		int lineNum = 1;
		
		try {
			while ((aLine = textReader.readLine()) != null){
				if(lineNum == 1){
					puzzleTitle = aLine;
					System.out.println(puzzleTitle);
				}
				//Get boardsize
				else if (lineNum ==2){
					boardsize = Integer.parseInt(aLine);
					//System.out.println(boardsize);
					//System.out.println();
					setupGridAndClues(boardsize);
				}
				//Setup Row Clues
				else if (lineNum >= 4 && lineNum <= 4 + (boardsize-1)){
					try {
						rows.setClueLine(aLine, lineNum-4);
					}
					catch (InvalidCluesException e)
					{
						System.out.println(e.getMessage());
					}
					//System.out.println(rows.getClueValue(lineNum-4, 0));
					
				}
				//Setup Col Clues
				else if (lineNum >= (4 + boardsize+1) && lineNum <= ((4 + boardsize+1) + boardsize-1)){
					try {
						cols.setClueLine(aLine, lineNum-(4 + boardsize+1));
					}
					catch (InvalidCluesException e) {
						System.out.println(e.getMessage());
					}
					//System.out.println(cols.getClueValue(lineNum-(4 + boardsize+1), 0));
					
				}
				
				lineNum++;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
//		
		//Debugging
//		rows.printClues();
//		System.out.println();
//		cols.printClues();
//		System.out.println();
//		grid.printGrid();
	}

	
	//Setup the board data
	public void setupGridAndClues(int boardSize) {
		grid = new GridArray(boardSize, this);
		rows = new CluesArray(boardSize);
		cols = new CluesArray(boardSize);
	}
	
	
	
	public boolean checkPuzzle() {
	int countFilled = 0;
	int rowPosition = 0;
	int cluePosition = 0;
	int[] currentRowClues;
	//int rowsCorrect = 0; not necessary find a bad row return false
	//
	

//	for (int row = 0; row < boardsize; row++) {
//		int[] currentRow = rows.getLineClues(row);
//		int index = 0;
//		for (int i = row; i < boardsize; i++) {
//			if (grid.getCellValue(i, row) == 1) {
//				count++;
//			}
//			else if (count == currentRow[index]) {
//					rowsCorrect++;
//					index++;
//					count = 0;
//				
//			}
//		}
//	}
//	
//	if (rowsCorrect == boardsize) {
//		return true;
//	}
//	else {
//		return false;
//		}
//	}
		return true;
	}
	
////Getters
	public String getPuzzleTitle() {
		return puzzleTitle;
	}


	public int getBoardsize() {
		return boardsize;
	}
	
	public GridArray getGrid() {
		return grid;
	}

	public CluesArray getRows() {
		return rows;
	}

	public CluesArray getCols() {
		return cols;
	}
		
}
