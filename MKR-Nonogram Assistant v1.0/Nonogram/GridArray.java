/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nonogram;

/**
 *
 * @author Rachel
 */
public class GridArray {
    
	private int[][] grid;  //Cols, rows
	private Data data;
	
	public GridArray(int size, Data data){
	
		grid = new int[size][];
		this.data = data;
		for (int i = 0; i < grid.length; i++){
			grid[i] = new int[size];
		}
		setGridAllUnknownState();
	}
	
	public void setGridAllUnknownState(){
		for (int i = 0; i < grid.length; i++){
			for (int x = 0; x <grid.length; x++){
				grid[i][x] = -1;
			}
		}
	}
	
	public int getCellValue(int row, int col){
		return grid[row][col];
	}
	
	public void setCellUnknownState(int row, int col){
		grid[row][col] = -1;
	}
	
	public void setCellBlankState(int row, int col){
		grid[row][col] = 0;
		if (gridWithNoUnknowns()) {
			if (data.checkPuzzle()) {
				System.out.println("Puzzle is Solved!");
				}
			else {
				System.out.println("Still needs some work");
					}
			}
	}
	
	public void setCellFillState(int row, int col){
		grid[row][col] = 1;
		if (gridWithNoUnknowns()) {
			if (data.checkPuzzle()) {
				System.out.println("Puzzle is Solved!");
				}
			else {
				System.out.println("Still needs some work");
					}
			}
		}
	
	public boolean gridWithNoUnknowns(){
		for (int i = 0; i < grid.length; i++){
			for (int x = 0; x <grid.length; x++){
				if (grid[i][x] == -1){
					return false;
				}
			}
		}
		
		return true;
	}
	
	//For Debugging
	public void printGrid(){
		String line = "";
		
		for (int i = 0; i < grid.length; i++){
			for (int x = 0; x <grid.length; x++){
				line = line + grid[i][x]+", ";
			}
			System.out.println(line);
			line = "";
		}
	}

	public GridArray getGrid() {
		return grid;
	}
}
