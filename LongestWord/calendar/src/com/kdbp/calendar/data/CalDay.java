/*
 * CalDay.java
 *
 * Created on November 24, 2001, 2:57 PM
 */

package com.kdbp.calendar.data;

import java.util.*;

/**
 * Stores all of the appointments of a single calendar day. It also
 * has some useful calendar-related abilities, such as the ability
 * to create a new calendar day that is incremented by a day.
 * @author  Paul Miles
 * @version 1.0
 */
public class CalDay {

	/** Collection of all of the appointments for the calendar day */
	/* Note that a LinkedList retains order */
	LinkedList appts;
	
	/** Stores whether or not this is a valid calendar day */
	boolean valid;
	
	/** Stores the calendar day */
	int day;
	
	/** Stores the calendar month */
	int month;
	
	/** Stores the calendar year */
	int year;
	
	/**
	* Default constructor
	* Constructs an invalid CalDay object
	*/
	public CalDay() {
		valid = false;
	}
	
	/**
	* Constructor
	* Creates a new CalDay which is ready for appointments to be
	* added to it. Note that months are numbered 0 through 11. This does
	* not check to see if a date is valid.
	*/
	public CalDay(GregorianCalendar cal) {
	
		int day = cal.get(cal.DAY_OF_MONTH);
		int month = cal.get(cal.MONTH);
		int year = cal.get(cal.YEAR);
	
		setDay(day);
		setMonth(month);
		setYear(year);
	
		setAppts(new LinkedList());
	
		valid = true;
	}
	
	/**
	 * Adds an appointment to the calendar day object. The appointments are
	 * kept in order by start time. Note that this does not check to see if 
	 * the appointment actually occurs on this day. This is so the recurring
	 * appointments can be added. The appointment can also be added twice.
	 */
	public void addAppt(Appt appt) {
		if (valid) {
			for (int i = 0; i < getAppts().size(); i++) {
				//Put the appointment in the correct order - finish this
				if (((Appt)getAppts().get(i)).getStartHour() >
										appt.getStartHour()) {
					
					getAppts().add(i, appt);
					return;
				}
			}
		    //The appointment hasn't been added yet, so add it
		    getAppts().add(appt);
		}
	}
	
	/**
	 * @return True if this is an initalized CalDay object
	 */
	public boolean isValid() {
	    return valid;
	}
	
	/**
	 * This returns an iterator of Appt objects for the calendar day. The 
	 * appointments are added after the CalDay is constructed. The appointments
	 * are guaranteed to be in order by time, with appointments with no specific 
	 * time set placed at the beginning.
	 */
	public Iterator iterator() {
	    if (isValid()) {
	        return getAppts().iterator();
	    }
	    else {
	        return null;
	    }
	}
	
	/** Sets appts */
	private void setAppts(LinkedList appts) {
	    this.appts = appts;
	}
	
	/** Sets day */
	private void setDay(int day) {
	    this.day = day;
	}
	
	/** Sets month */
	private void setMonth(int month) {
	    this.month = month;
	}
	
	/** Sets year */
	private void setYear(int year) {
	    this.year = year;
	}
	
	/** Gets appts */
	public LinkedList getAppts() {
	    return appts;
	}
	
	/** Gets day */
	public int getDay() {
	    return day;
	}
	
	/** 
	 * Gets the month represented by this calDay. Note that January-December
	 * are represented by 0-11 
	 */
	public int getMonth() {
	    return month;
	}
	
	/** Gets year */
	public int getYear() {
	    return year;
	}
	
	/**
	 * Returns a string representation of the date represented in
	 * the format of MM/DD/YYYY. If someone where to ask what day of the
	 * year this CalDay was for, and they called this method, that would
	 * be the answer. No further adjustment would be necessary. Useful for
	 * debugging.
	 */
	public String toString() {
	    return (getMonth()+1) + "/" + getDay() + "/" + getYear();
	}
}