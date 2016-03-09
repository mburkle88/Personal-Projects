/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nonogram;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/**
 *
 * @author Rachel
 */
public class View extends JFrame {

	private	Nonogram controller;
	
	//Panels
	private Container contentPane;
	private JPanel menuPane;
	
	private JPanel explainPane;
	private PanelPuzzleGrid puzzlePane;
	private PanelClueRow clueRowPane; 
	private PanelClueCol clueColPane;
	private PanelPreview previewPane;
	private JPanel PCRG; //Preview,Col,rows,gird
	private JPanel PCRG_Top;
	private JPanel PCRG_Other;
	private JPanel temp;
	
	
	//Explain Variables
	private JTextArea explain;
	private JToolBar toolbar;
	
    /**
     * Creates new form View
     */
    public View(Nonogram control) {
    	this.controller=control;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Playing Nonograms");
		
		//Panels
		previewPane=new PanelPreview(controller.getBoardSize(), controller.getPuzzleGridData());
		
		
		puzzlePane=new PanelPuzzleGrid(this, controller.getBoardSize(), controller.getPuzzleGridData());
		puzzlePane.setPreferredSize(new Dimension(500,500));
		
		menuPane = new JPanel();
		menuPane.setLayout(new BorderLayout());
		createToolBar();
		menuPane.add(toolbar,BorderLayout.CENTER);
		
		clueRowPane=new PanelClueRow(controller.getRowCluesData());
		clueColPane=new PanelClueCol(controller.getColsCluesData());
				

		
		
		PCRG_Top = new JPanel();
		PCRG_Top.setLayout(new BorderLayout());
		//previewPane.setPreferredSize(Dimension(clueRowPane.getWidth()+200, clueColPane.getHeight()));
//		PCRG_Top.add(previewPane, BorderLayout.LINE_START);
//		PCRG_Top.add(clueColPane, BorderLayout.CENTER);
		PCRG_Top.add(puzzlePane,BorderLayout.CENTER);
		//PCRG.add(clueRowPane, BorderLayout.LINE_START);
		//clueColPane.setPreferredSize(Dimension(400,600));
		PCRG_Top.add(clueColPane, BorderLayout.PAGE_START);
		
		temp = new JPanel();
		PCRG = new JPanel();
		PCRG.setLayout(new BorderLayout());
		//previewPane.setPreferredSize(Dimension(clueColPane.getHeight(),clueColPane.getHeight()));
		previewPane.setPreferredSize(Dimension(clueRowPane.getHeight(),clueColPane.getHeight()));
		PCRG.add(previewPane,BorderLayout.PAGE_START);
		//PCRG.add(temp,BorderLayout.PAGE_START);
		//clueRowPane.setPreferredSize(Dimension(600,400));
		PCRG.add(clueRowPane,BorderLayout.CENTER);
		
		PCRG_Other = new JPanel();
		PCRG_Other.setLayout(new BorderLayout());
		PCRG_Other.add(PCRG,BorderLayout.LINE_START);
		PCRG_Other.add(PCRG_Top,BorderLayout.CENTER);
//		PCRG.add(puzzlePane,BorderLayout.CENTER);
//		//PCRG.add(clueRowPane, BorderLayout.LINE_START);
//		PCRG.add(clueColPane, BorderLayout.PAGE_START);
		//PCRG.add(PCRG_Top, BorderLayout.PAGE_START);
		
		
		
		//this.add(clueRowPane, BorderLayout.LINE_START);
		this.add(menuPane,BorderLayout.PAGE_START);
		this.add(PCRG_Other, BorderLayout.CENTER);
	
        
    //ShowExplain
		createTextArea();
        this.add(explainPane, BorderLayout.LINE_END);
        showOrHideExplain();


		//
		this.setVisible(true);
		this.pack();
    }
    
    


///////////////////////////////////////////////////////////////////////////////////////
    
	private Dimension Dimension(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}




	//Creating ToolBar
    private void createToolBar(){
    	toolbar=new JToolBar();
    	toolbar.setFloatable(false);
    	int i = 0;
		toolbar.add(new MyAction("Select Puzzle","SELECT_PUZZLE"));
		((JComponent)(toolbar.getComponentAtIndex(i))).setToolTipText("Select Puzzle");
    	i= i+1;
    	
		toolbar.add(new MyAction("See Hint","SEE_HINT"));
		((JComponent)(toolbar.getComponentAtIndex(i))).setToolTipText("See the next move");
    	i= i+1;
    	
    	toolbar.add(new MyAction("Clear Board","CLEAR_BOARD"));
		((JComponent)(toolbar.getComponentAtIndex(i))).setToolTipText("Clears the puzzle grid");
    	i= i+1;
    	
    	toolbar.add(new MyAction("Show Explain","SHOW_EXPLAIN"));
		((JComponent)(toolbar.getComponentAtIndex(i))).setToolTipText("See the next move");
    	i= i+1;
    	
    }
    
    
    private class MyAction extends AbstractAction{
		private static final long serialVersionUID = 1;
		public MyAction(String text, String command){
			super(text);
			putValue(ACTION_COMMAND_KEY, command);
		}
		@Override
		public void actionPerformed(ActionEvent a){
			String command=a.getActionCommand();
			
			if (command.compareTo("SELECT_PUZZLE")==0)
				try {
					controller.newGamePuzzle();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if (command.compareTo("SEE_HINT")==0) controller.runSolver();
				//Have solver agent go once
			else if (command.compareTo("CLEAR_BOARD")==0) controller.clearGrid();
				//Control -> to data -> grid.setGridAllUnknownState()
			//if (command.compareTo("RANDOM_GAME")==0) control.randomGame(getGrade());
			//if (command.compareTo("SOLVE_GAME")==0) control.userSolveGame();
			else if (command.compareTo("SHOW_EXPLAIN")==0) showOrHideExplain();
		}
	}
    
    
///////////////////////////////////////////////////////////////////////////////////////
    
    //Create text Area
    private void createTextArea() {
		//Hook up to the Solver later
		explainPane=new JPanel();
		explainPane.setLayout(new FlowLayout());
	    String text = "A JTextArea object represents a multiline area for displaying text. "
	        + "You can change the number of lines that can be displayed at a time, "
	        + "as well as the number of columns. You can wrap lines and words too. "
	        + "You can also put your JTextArea in a JScrollPane to make it scrollable.";
	    //text = this.control.getExplanation();
	    text = "HELLO";

	    explain = new JTextArea(text, 20, 20);
	    
	    explain.setLineWrap(true);
	    explain.setWrapStyleWord(true);
	    explain.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(explain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    explainPane.add(scrollPane);
	   
	}
    
    public void addToExplainTextArea(String text){
    	explain.append(text);
    }
    
    
	//Hide or show Explanation
 	public void showOrHideExplain(){
		
		if (explainPane.isVisible()){
			explainPane.setVisible(false);
		}
		else
			{explainPane.setVisible(true);
			}
		this.pack();
	}

	public void updateGridGUI()
	{
		puzzlePane.updateGUIPuzzle();
	}


	public PanelPreview getPreviewPane() {
		return previewPane;
	}
	
    
}
