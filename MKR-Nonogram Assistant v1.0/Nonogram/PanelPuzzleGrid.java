package Nonogram;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelPuzzleGrid extends JPanel{
	
	
	
	private static Color UNKNOWN_COLOR = Color.WHITE;
	private static Color BLANK_COLOR = Color.YELLOW;
	private static Color FILL_COLOR = Color.BLACK;
	
	
	private GridArray dataGrid;  //GridArray from Data
	private int gridBoardSize;
	private View viewer;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1410133260717329893L;
	
	
	public PanelPuzzleGrid(View view, int boardsize, GridArray gridData){
		this.dataGrid = gridData;
		this.gridBoardSize=boardsize;
		this.viewer = view;
		this.setLayout(new GridLayout(gridBoardSize, gridBoardSize,5,5));
		this.setBackground(Color.BLACK);
		createGirdCells();		

	}
    public void clickLeft(MouseEvent e, GUICell cell) {
    	//System.out.println("("+cell.getRow()+", "+cell.getCol()+")");
    	if(cell.getBackground() == (UNKNOWN_COLOR)){
    		cell.setBackground(FILL_COLOR);
    		dataGrid.setCellFillState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    		
    	}
    	else if (cell.getBackground() == (FILL_COLOR)){
    		cell.setBackground(BLANK_COLOR);
    		dataGrid.setCellBlankState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    	}
    	else if (cell.getBackground() == (BLANK_COLOR)){
    		cell.setBackground(UNKNOWN_COLOR);
    		dataGrid.setCellUnknownState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    		}
    }

    public void clickRight(MouseEvent e, GUICell cell) {
    	//System.out.println("("+cell.getRow()+", "+cell.getCol()+")");
    	if(cell.getBackground() == (UNKNOWN_COLOR)){
    		cell.setBackground(BLANK_COLOR);
    		dataGrid.setCellBlankState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    	}
    	else if (cell.getBackground() == (BLANK_COLOR)){
    		cell.setBackground(FILL_COLOR);
    		dataGrid.setCellFillState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    	}
    	else if (cell.getBackground() == (FILL_COLOR)){
    		cell.setBackground(UNKNOWN_COLOR);
    		dataGrid.setCellUnknownState(cell.getCol(), cell.getRow());
    		updatePreviewPanel();
    		System.out.println();
    		dataGrid.printGrid();
    		}
    }
	
    
    public void updateGUIPuzzle(){
    	this.removeAll();
    	createGirdCells();    	
    }
    
    
    public void createGirdCells(){
    	
    	for(int y=0;y<gridBoardSize; y++){
			for(int x =0; x< gridBoardSize; x++){
				final GUICell cell =new GUICell(x,y);
				this.add(cell);
				cell.setSize(10, 10);
				if (dataGrid.getCellValue(y,x) == 1){
					cell.setBackground(FILL_COLOR);}
				else if (dataGrid.getCellValue(y,x) == 0){
					cell.setBackground(BLANK_COLOR);}
				else {
					cell.setBackground(UNKNOWN_COLOR);}
				
				cell.addMouseListener(new MouseListener(){
					
						
					@Override
					public void mouseClicked(MouseEvent e) {
					    
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if (e.getModifiers() == MouseEvent.BUTTON3_MASK) { 
							clickRight(e, cell);
							}
						else{clickLeft(e, cell);}
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
		
				});				
			}
		}
    	updatePreviewPanel();
    }
    
    private void updatePreviewPanel(){
    	viewer.getPreviewPane().updatePreview();
    	viewer.pack();
    }
    
    
}
