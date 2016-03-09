// ApptDetailsPane.java
// A simple Web-browsing component that
// extends JEditorPane and maintains a history of visited URLs.
package com.kdbp.calendar.gui.view;


// Java core packages
import java.util.*;
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;

import com.kdbp.calendar.data.*;
import com.kdbp.calendar.gui.*; 


/**
*	A class that shows the details of for a specific set of 
*	appointments.
**/
public class ApptDetailsPane extends JPanel implements MouseListener {

	private CalFrame owner;
	private LinkedList appts;
	private DefaultListModel model;
	private JList apptViews;
	private JScrollPane scroller = new JScrollPane();
	
	
	/**
	*	The only constructor.
	*	@param day The CalDay object with the data to display.
	*	@param parent The owner of the ApptDetailsPane
	**/
	public ApptDetailsPane(CalDay day, CalFrame parent) {
		// disable editing to enable hyperlinks
		super( ); 
		owner = parent;
		if(day != null) {
			initComponents(day);
		}
	}
	
	/**
	*	Initializes the components and readies the window for display.
	*	@param day The day to display.
	**/
	private void initComponents(CalDay day) {
		
		setLayout(new BorderLayout());
		model = new DefaultListModel();
		appts = day.getAppts();
		
		ListIterator itr = appts.listIterator();
		
		while( itr.hasNext() ) {
			
			Appt cur = (Appt)itr.next();
		 	model.addElement( new ApptTitle(cur) );    	 
		}
		
		apptViews = new JList(model);
		
		apptViews.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		apptViews.setFixedCellWidth(725);
		
		apptViews.addMouseListener(this); 
		scroller= new JScrollPane(apptViews);     
		add(scroller, BorderLayout.CENTER);
		// setSize(new Dimension(640, 80));
		setVisible(true);      
	}
	
	/**
	*	Reinitializes the components.
	*	@param day The day to display.
	**/
	private void reInitComponents(CalDay day)
	{
		model.clear();
		appts = day.getAppts();
	  
		ListIterator itr = appts.listIterator();
		
		while( itr.hasNext() ) {
			
			Appt cur = (Appt)itr.next();
		 	model.addElement( new ApptTitle(cur) );    	 
		}
		//apptViews = new JList( model );
		
		//apptViews.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//apptViews.setFixedCellWidth(725);
		
		//apptViews.addMouseListener(this); 
		//scroller= new JScrollPane(apptViews);     
		//add(scroller, BorderLayout.CENTER);
		// setSize(new Dimension(640, 80));
		//setVisible(true);      
	}      
	
	/**
	*	Handles mouse click event.
	**/
	public void mouseClicked(MouseEvent e) {
		
		if (e.getClickCount() == 2) {
			Appt clicked = ( (ApptTitle)apptViews.getSelectedValue() ).getAppt();
			new ApptDialog( owner, "Edit Appointment", true, clicked).show();
		}
	}

	public void setSelected(CalDay day) {
		reInitComponents(day);
	}
	
	public Appt getSelected() {
		  return ( (ApptTitle)apptViews.getSelectedValue() ).getAppt();
	}
	
	/**
	*	NOT USED
	**/
	public void mouseEntered(MouseEvent e) { }
	
	/**
	*	NOT USED
	**/
	public void mouseExited(MouseEvent e) { } 
	
	/**
	*	NOT USED
	**/
	public void mousePressed(MouseEvent e) { } 
	
	/**
	*	NOT USED
	**/
	public void mouseReleased(MouseEvent e) { } 

}
