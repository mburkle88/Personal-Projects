/**
*	CalFrame.java
*	
*	The main frame that contains everything you see in the calendar.
*	Controls the calendar logic and flow of control throughout the program.
*	Either handles mouse events directly, or passes them to a particular component.
*	Contains a CalToolBar, DataHandler, ViewPane, and ApptDetailsPane. 
*	
*	@author Dan Belina
**/

package com.kdbp.calendar.gui;

import com.kdbp.calendar.data.*;
import com.kdbp.calendar.gui.view.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class CalFrame extends javax.swing.JFrame 
						implements ActionListener, MouseListener {

	/** instance of CalToolBar **/
	protected CalToolBar calToolBar;
	
	/** instance of DataHandler **/
	protected DataHandler dataHandler;
	
	/** instance of ViewPane **/
	protected ViewPane viewPane;
	
	/** instance of ApptDetailsPane **/
	protected ApptDetailsPane apptPane;
	
	/** used to determine current system date **/
	protected Calendar rightnow;
	
	/** the month the user is currently viewing **/
	protected int thisMonth;
	
	/** the year the user is currently viewing **/
	protected int thisYear;
	
	/** todays date **/
	protected int thisDay;
	
	
	/** Used when the user is changing month/year**/
	private int endYear = 0;
	private int beginYear = 0;

	/** Whether an appointment had a specified time **/
	public boolean TIME;
	
	/**
	*	Creates initial frame, instantiates its member variables,
	*	queries the DataHandler for information about the current month
	*	and gives this to the ViewPane.  Adds the gui related components
	*	to itself
	*	
	*	@param title The title of the CalFrame
	*	
	**/
	public CalFrame(String title) {
		super(title);
		try{

			TIME = false; //init to false
		
			//get todays date
			rightnow = Calendar.getInstance();
		
			//current month/year/date is today
			thisMonth = rightnow.get(Calendar.MONTH);
			thisYear = rightnow.get(Calendar.YEAR);
			thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
			
			//initialize the components of the calendar
			dataHandler = new DataHandler();
			viewPane = new ViewPane(this);
			calToolBar = new CalToolBar(this);
	
			//update the current view to the current month and year
			changeView(thisMonth, thisYear);
			
			
			//get a list of appointments for one day, for the ApptDetailsPane to use initially
			GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
			GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
			tomorrow.add(Calendar.DAY_OF_MONTH,1);
			java.util.List L = dataHandler.getApptRange(today,tomorrow);
			
			//init the details pain
			apptPane = new ApptDetailsPane((CalDay)L.get(0),this);
			
			//init all the components
			initComponents();
		}
		catch(IOException ioe) {
			System.err.println("IO Exception: " + ioe);
		}
		catch(Exception e) { 
			System.err.println("Unknown Exception: " + e);
			e.printStackTrace();
		}
	}
		

    /**
	*	used for action that is performed in the frame
	*	@param evt the event which occured
	**/
	public void actionPerformed(java.awt.event.ActionEvent evt)
	{
		//get the source of the event	
		Object o = evt.getSource();

		//if the add appointment button was clicked   
		if( o == CalToolBar.newApptButton ) {

			//spawn new dialog box, using the CalFrame as the parent, give it a title,
			//and make it modal.  Then show it.
			new ApptDialog(this,"Add New Appointment",true).show();
		}


		//if the month forward button was clicked
		if( o == CalToolBar.monthForwardButton ) {
			if(thisMonth == 11) {
				thisMonth = 0;
				thisYear++;
				
			}
			else {
				thisMonth++;
				
			}
		}

		//if the month backward button was clicked
		if( o == CalToolBar.monthBackwardButton ) {
			if(thisMonth == 0) {
					thisMonth = 11;
					thisYear--;
			}
			else {
				thisMonth--;
			}
		}

		//if the year forward buttong was clicked
		if( o == CalToolBar.yearForwardButton ) {
			thisYear++;
		}

		//if the year backward button was clicked
		if( o == CalToolBar.yearBackwardButton ) {
			thisYear--;
		}

		//Get the ViewPane a new set of data for the month and year
		//after a change
		changeView(thisMonth,thisYear);

		//repaint so the ViewPane can show the new info
		repaint();
		viewPane.repaint();
	}
   
	/**
	*	When a mouse clicked event occurs
	*	@param e the particular mouse event
	**/
	public void mouseClicked(MouseEvent e) {
		//get the source of the event
		Object o = e.getSource();
		
		//if it was a click on JTable
		if (o instanceof JTable ) {
			//the selected day is that day
			CalDay highlighted = viewPane.getSelectedDay();
			apptPane.setSelected(highlighted);   	 
		}
	}
	   
	//The next four methods are needed here because CalFrame
	//implements the MouseListener interface
	
	/**
	*	When a mouse entered event occurs
	*		@param e the particular mouse event
	**/
	public void mouseEntered(MouseEvent e) { }
	
	
	/**
	*		When a mouse exited event occurs
	*		
	*		@param e the particular mouse event
	**/
	public void mouseExited(MouseEvent e) { }
	
	/**
	*		When a mouse pressed event occurs
	*		
	*		@param e the particular mouse event
	**/
	public void mousePressed(MouseEvent e) { }
	
	
	/**
	*		When a mouse released event occurs
	*		
	*		@param e the particular mouse event
	**/
	public void mouseReleased(MouseEvent e) { }
	
	
	/**
	Private method that inits the components of the frame
	**/
	private void initComponents() {
		
		//add a window listener
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		//get the content pane
		Container c = getContentPane();
		
		//add the toolbar, viewpane, and appointment details pane to the frame
		c.add(calToolBar, BorderLayout.NORTH);
		c.add(viewPane, BorderLayout.CENTER);
		c.add(apptPane,BorderLayout.SOUTH);
		
		//set the initial location
		setLocation(50, 50);
		
		//pack it
		pack();
	}
	
	
	/**
	*	When the frame is exited
	*	@param evt the particular event which causes the frame to exit
	**/
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}


	/**
	*	Shows the frame on the display
	**/
	public void run() {
		show();
	}


	/**
	*	Gets a range of data from the DataHandler for a specified month
	*	and year, and gives it to the ViewPanel.
	*	This range includes the month and year specified, but also
	*	enough days before the 1st of the month and after the last to make
	*	the first and last weeks have seven days
	*	@param month the month
	*	@param year the year
	**/
	public void changeView(int month, int year) {
		
	
		//what day of week the 1st is	
		int firstDayOfMonth = CalendarUtil.CalcFirstOfMonth(year, month);
		
		
		//Using that day, determine # days needed before that day to make a 7 day week
		int numDaysNeededBefore = firstDayOfMonth - Calendar.SUNDAY;
		
		//first day on the calendar ViewPanel defaults to 1
		int firstDateNeeded = 1;
		
		//we need to figure out what day of week last day is on....initialize it to
		//be the first day
		int lastDayOfMonth = firstDayOfMonth;
		
		//starting at 0, loop a number of times = to the days in the month
		for(int i = 0; i < CalendarUtil.NumDaysInMonth(year,month); i++) {
	
			//if the last day is on a saturday, "wrap around" to sunday
			if(lastDayOfMonth == Calendar.SATURDAY)
				lastDayOfMonth = Calendar.SUNDAY;
			else
				lastDayOfMonth++;  //otherwise, go to the next day (i.e. MONDAY -> TUESDAY)
		}
		
		//once we have what day of week the last is on, we can find how many days
		//are needed after that day to make a 7 day week
		int numDaysNeededAfter = Calendar.SATURDAY - lastDayOfMonth;
		
		//some vars used in determining the # of the date which is first/last on the calendar
		int prev, next;
		prev = next = 0;
	
		//if we need extra days to make the first week a 7 day week (i.e. the 1st isn't on a Sunday)
		if(numDaysNeededBefore > 0) {
			if(month == 0)
				prev = 11;
			else
				prev = -1;
			
			firstDateNeeded = CalendarUtil.NumDaysInMonth(year, month+prev) - numDaysNeededBefore;
		}
		
		//if we need extra days to make the last week a 7 day week (i.e. the last isn't a Saturday)
		if(numDaysNeededAfter > 0) {
			if(month == 11) {
			next = -11;
			}
			else {
				next = 1;
			}
		}
	
		//get the range of dates we need and give it to the ViewPanel
		try {
			
			/**
				If the current month is 11 (December), the next month will be in the next year
			*/
			if(thisMonth == 11)
				endYear = 1;
	
			/**
				If the current month is 0 (January), the prev month is in the prior year
			*/
			if(thisMonth == 0)
				beginYear = 1;
	
			/**
			*	Here's how this next call works:
			*	
			*	We know the current month and year the user is looking at, but lets also display
			*	the days before the 1st of that month in the prior month, and the days after
			*	the last of the month in the next month. This way we have all 7 day weeks,
			*	and the user can see appointments in proximity to the month he is viewing.
			*	
			*	The current month could possibly be January or December, so the
			*	"endYear" and "beginYear" variables are used here.  They are zero unless
			*	at the end or beginning of a year respectively, so we can subtract or add
			*	one from the year to get the year we need for our range if we are indeed at
			*	the end/beginning of a year
			**/
			viewPane.setCurrentDays(dataHandler.getApptRange
					(new GregorianCalendar(year-beginYear,month+prev,firstDateNeeded),
					new GregorianCalendar(year+endYear,month+next,numDaysNeededAfter+1)));
			
			//reset the variables to zero
			endYear = beginYear = 0;
		}
		
		//if an invalid date is specified for the DataHandler
		//Should never happen but...
		catch(DateOutOfRangeException e) {
	
			System.out.println("Date exception"); 
			System.err.println(e);
		}
	}

	/**
	*	When a new Appt is added
	*	
	*	@param appt the appointment being added
	**/
	public void addAppt(Appt appt) {
		dataHandler.saveAppt(appt);
	}
	
	/**
	*	When an Appt is removed
	*	
	*	@param appt the appoinment being removed
	**/
	public void deleteAppt(Appt appt) {
		dataHandler.deleteAppt(appt);
	}
	
	/**
	*	When an appointment is updated
	*
	*	@param appt the appointment being updated
	**/
	public void UpdateAppt(Appt appt) {
		deleteAppt(appt);
		addAppt(appt);
	}
}














