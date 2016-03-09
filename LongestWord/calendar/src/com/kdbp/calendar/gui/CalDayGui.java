/**
*	CalDayGui.java
*	
*	The dialog box used for adding or editing appointments
**/

package com.kdbp.calender.gui;

import com.kdbp.calendar.data.*;

import java.awt.*;
import javax.swing.*;



public class CalDayGui extends JPanel {
	/** JLabel holds the date text**/
	public JLabel dateText;
	/** A private CalendarDay  object**/
	private CalDay day;
	/** public static java.awt.Color DEFAULT_BG_COLOR = java.awt.Color.white **/
	public static Color DEFAULT_BG_COLOR = Color.white;
	/** public static java.awt.Color HIGHLIGHT_COLOR = new Color(R B, G) **/
	public static Color HIGHLIGHT_COLOR = Color.lightGray;
	public static Color bgColor;
	/*** API ***/

	public CalDayGui()
	{
		super();
		day = new CalDay();
		//String data = day.getData();
		String data = day.toString();
		dateText = new JLabel(data);
		add(dateText);
		highlight(false);
	}
	
	/*public CalDayGui ( CalDay day ) {
		this.day = day;
		dateText = new JLabel( day.getDate( ) );
		
	}*/

	public void highlight( boolean setHighlight ) {
	if (setHighlight)
		setBackground(HIGHLIGHT_COLOR);
	else
		setBackground(DEFAULT_BG_COLOR);	}

}
