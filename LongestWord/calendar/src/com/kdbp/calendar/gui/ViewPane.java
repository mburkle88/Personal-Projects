/**	
*	ViewPane.java
*
*	@author Ben Colburn
*
*	It should be noted that this class was inspired by an
*	example from the Java tutorial on JTables at www.java.sun.com
**/

package com.kdbp.calendar.gui;

import com.kdbp.calendar.data.*;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellRenderer;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
*	This class is repsonsible for displaying a list of days in
*	a calendar format. It utilizes the data view architecture built
*	into JTable to accomplish this. It contains two inner classes,
*	CalDayRenderer and MonthViewDataModel, to display and store
*	the required information.
**/
public class ViewPane extends JPanel {
	
	/**
	*	this is a reference to the window that owns this panel
	**/
	private CalFrame parent;
	
	/**
	*	this is where the appropriate data is stored for display
	*	inside of the JTable
	**/
    private MonthViewDataModel myModel;
    
    /**
    *	This is the table used to display the data in conjunction with
    *	the MonthViewDataModel class
    **/
    private JTable table;
    
    /**
    *	The JTable is inserted into this to provide scrolling capabilities.
    **/
	JScrollPane scrollPane;

	/**
	*	The only constructor. It sets the reference to the parent window.
	*	@param owner This is the parent window that owns an instance of this
	*	class
	**/
    public ViewPane (CalFrame owner) {
        super();	
        parent = owner;	
    }
    
    /**
    *	This is used to set/reset the data that needs to be displayed.
    *	@param indata This is the data that needs to be stored and displayed.
    **/
    public void setCurrentDays(java.util.List indata) {
    	
    	myModel = new MonthViewDataModel(indata);
        table = new JTable(myModel);
        table.addMouseListener( (MouseListener)parent);
        table.setRowHeight (100);
        
        table.setPreferredScrollableViewportSize(new Dimension(700, 500) );

        //Create the scroll pane and add the table to it. 
        scrollPane = new JScrollPane(table);

        //Set up renderer and editor for the Favorite Color column.
        setUpCalDayRenderer(table);

        //Add the scroll pane to this window.
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
    *	This returns the data from the selected data cell.
    **/
    public CalDay getSelectedDay() {
       int rowIndex = table.getSelectedRow();
       int colIndex = table.getSelectedColumn();
       
       return((CalDay)(myModel.getValueAt(rowIndex, colIndex)));
    }
    
	/**
	*	This class is used in conjunction with JTable to override the
	*	default behavior of the JTable to display the data.
	**/
    class CalDayRenderer extends JLabel
                        implements TableCellRenderer {
        
        /**
        *	values used in rendering the data
        **/
        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;

		/**
		*	The only constructor. Set isBordered to true if the cell is
		*	to display a border around itself.
		**/
        public CalDayRenderer(boolean isBordered) {
            super();
            this.setVerticalAlignment(this.NORTH);
            this.setBackground(Color.white);
            this.isBordered = isBordered;
            setOpaque(true); //MUST do this for background to show up.
        }

		/**
		*	Returns the Component, already initialized with the appropriate
		*	data, that is to actually display the data for the specified cell.
		*	This is where the cell's display data is actually set.
		*
		*	@param table The table asking for the Component
		*	@param calday The data that needs to be displayed.
		*	@param isSelected Is set to true if the cell is selected.
		*	@param hasFocus Is set to true if the parent window has the focus
		*	@param row The row of the data to be displayed.
		*	@param column The column of the data to be displayed.
		*	@return Returns an initialized Component used by JTable to display
		*	the specified data.
		**/
        public Component getTableCellRendererComponent(
                                JTable table, Object calday, 
                                boolean isSelected, boolean hasFocus,
                                int row, int column) {
                        
            Iterator itr = ((CalDay)calday).iterator();
            
            String buffer;
            
            //here I'm using the fact that JLabels can be formatted using HTML
            buffer = "<html><p><font size=\"+1\">";            
            buffer += Integer.toString(((CalDay)calday).getDay());            
            buffer += "</font>";
            
          	Appt appointment;
            
            int minute;
            int hour;
            String minString;
			String meridianString;
            
            //go through the day and get the data to display
            while(itr.hasNext()){
            	
            	buffer += "<p>";
            	
            	appointment = (Appt)itr.next();
				
				if(appointment.hasTimeSet()){
					
					//figure AM/PM notation
					hour = appointment.getStartHour();
					if(hour>12){
						meridianString = "PM";
					}
					else{
						meridianString = "AM";	
					}
					
					//convert from 24 to 12 hour time
					if(hour == 0){
						hour = 12;	
					}
					else{
						hour = hour%12;
					}
					
					//add preceding zero to minutes less than 10
					minute = appointment.getStartMinute();
					if(minute < 10){
						minString = new String("0" + Integer.toString(minute));
					}
					else{
						minString = Integer.toString(minute);
					}
					
					//create the string containing a data summary
					buffer += hour + ":" + minString + meridianString + " "
							 + appointment.getTitle();
				}
				else{
					buffer += appointment.getTitle();	
				}
            }
            
            //set the text in the JLabel to the newly created String
            this.setText(buffer);
            
            return this;
        }
	}

	/**
	*	Sets the default renderer for the CalDay class for a JTable
	*	@param table This is the table to be set up with a new renderer
	*	for the CalDay class
	**/
    private void setUpCalDayRenderer(JTable table) {
        table.setDefaultRenderer(CalDay.class, new CalDayRenderer(true));
    }

	/**
	*	This class is used to store the data to be displayed within a
	*	JTable. It is compliant with the model/view architecture implicit
	*	in the JTable implementation.
	**/
    class MonthViewDataModel extends AbstractTableModel {
        /**
        *	The days of the week.
        **/
        final String[] columnNames = {"Sunday", 
                                      "Monday",
                                      "Tuesday",
                                      "Wednesday",
                                      "Thursday",
                                      "Friday",
                                      "Saturday"};

		/**
		*	The stored data.
		**/
        Object[][] data;
        
        /**
        *	The only constructor.
        *	@param indata The data to be stored.
        **/
        public MonthViewDataModel(java.util.List indata) {
        	super();
			
			//7 days in a week, hence 7 columns
            int cols = 7;
            //figure the number of weeks (rows) needed.
			int rows = indata.size() / 7;
			
			//make new 2d array
			this.data = new CalDay[rows][cols];
			
			//insert the data into the new array
			for(int curRow = 0; curRow<rows; curRow++) {
				for(int curCol=0; curCol<7; curCol++) {
					
					this.data[curRow][curCol] = (CalDay)indata.remove(0);
				}
			}
		}

		/**
		*	Used by JTable to get the number of columns needed to display the data.
		*	@return Returns the appropriate number of columns.
		**/
        public int getColumnCount() {
            return columnNames.length;
        }
        
        /**
        *	Used by JTable to get the number of rows needed to display the data.
        *	@return	Returns the appropriate number of rows.
        **/
        public int getRowCount() {
            return data.length;
        }

		/**
		*	Used to get the name of the column.
		*	@param col The column in question
		*	@return Returns a String with the appropriate day of the week.
		**/
        public String getColumnName(int col) {
            return columnNames[col];
        }

		/**
		*	Used to get the data at a specified location in the table.
		*	@param row The row location of the requested data.
		*	@param col The column location of the requested data.
		*	@return Returns the data at (row, col) in the table.
		**/
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) { 
                return false;
            } else {
                return true;
            }
        }

		/**
		*	Sets new data for a specified location in the table.
		*	@param value The new data to be stored in the table.
		*	@param row The row location for the new data.
		*	@param col The column location for the new data.
		**/
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);

        }
    }
}
