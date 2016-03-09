/**
* CalToolBar.java
* A simple JToolbar of JButtons that allows any actionListener to listen
* for clicks on any of the JButtons.
*
* @author  Kieron Hinds
* Date:    11/23/2001
**/

package com.kdbp.calendar.gui;


import java.util.*;
import java.awt.*;
import java.awt.event.*;	
import javax.swing.*;
import javax.swing.event.*;


/**
*	A simple JToolbar of JButtons that allows any actionListener to listen
*	for clicks on any of the JButtons.
*
*	@author Kieron Hinds
*	@version 1.0
**/
public class CalToolBar extends JToolBar {
	/** A Jbutton for selecting new Appointments **/
	public static JButton newApptButton;
	
	/** A Jbutton for deleting new Appointments **/
	public static JButton deleteApptButton;
	
	/** A Jbutton for causing the previous month to viewed **/
	public static JButton monthBackwardButton;
	
	/** A Jbutton for causing the next month to viewed **/
	public static JButton monthForwardButton;
	
	/** A Jbutton for causing the previous year to viewed **/
	public static JButton yearBackwardButton;
	
	/** A Jbutton for causing the next year to viewed **/
	public static JButton yearForwardButton;
	
	/** stores the JButtons for iterator access **/
	private Vector buttons;


	/**
	*	Constructor
	*	Initializes every member JButton and adds it to the JToolBar
	**/
	public CalToolBar(ActionListener component ) {

		super();
		buttons = new Vector(6);
		
		newApptButton = new JButton("New Appointment");
		buttons.addElement(newApptButton);
		add(newApptButton);
		
		deleteApptButton = new JButton("Delete Appointment");
		add(deleteApptButton);
		buttons.addElement(deleteApptButton);
		
		monthBackwardButton = new JButton("<=Month");
		add(monthBackwardButton);
		buttons.addElement(monthBackwardButton);
		
		monthForwardButton = new JButton("Month=>");
		add(monthForwardButton);
		buttons.addElement(monthForwardButton);
		
		yearBackwardButton = new JButton("<=Year");
		add(yearBackwardButton);
		buttons.addElement(yearBackwardButton);
		
		yearForwardButton = new JButton("Year=>");
		add(yearForwardButton);
		buttons.addElement(yearForwardButton);
		
		setListener(component);
		}

	/**
	*	Allows an ActionListener object to be registered as a listener with 
	*	every JButton.    
	**/ 
	public void setListener(ActionListener listener) {
		for(int i = 0; i < buttons.size(); i++) {
			((JButton)buttons.elementAt(i)).addActionListener(listener);
		}
	}
}

