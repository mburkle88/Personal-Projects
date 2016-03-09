/**
*	
*	The dialog box used for adding or editing appointments
*	
*	@author Dan Belina
**/

package com.kdbp.calendar.gui;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.kdbp.calendar.data.Appt;

public class ApptDialog extends javax.swing.JDialog {


	/** used for the current system date */
	protected Calendar rightnow;
	
	/** the owner frame that invokes the instance of ApptDialog */
	protected CalFrame owner;
	
	/** the email and url entered into the dialog box */
	protected String email, url;
	
	/** integer value of the month chosen */
	protected int month;
	
	/** an appointment used when dialog is used for editing an exisiting appointment */
	protected Appt appointment;
	
	/** whether an appointment was opened for editing or not */
	public boolean EDIT;
	
	/** whether an appointment opened had a specified time or not */
	public boolean TIME;
	
	/**
	*	Used when a new blank appointment being added via the "Add" button
	*	
	*	@param parent the owner frame that invokes the dialog box
	*	@param title the title of the dialog box
	*	@param model whether the box is modal or not
	*	
	**/
	public ApptDialog(CalFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
		
		EDIT = TIME = false;
		owner = parent;
		email = url = "";
		
		//init the month and year fields to current month, year, and day
		rightnow = Calendar.getInstance();
		month = Calendar.MONTH;
		jComboBoxMonth.setSelectedIndex(rightnow.get(month));
		jTextFieldYear.setText((new Integer(rightnow.get(Calendar.YEAR))).toString());
		jTextFieldDay.setText((new Integer(rightnow.get(Calendar.DAY_OF_MONTH))).toString());
	}

	/**
	*	Used when an existing appointment is opened for editing...
	*	Need to init the fields in the dialog box 
	*	
	*	@param parent the owner frame that invokes the dialog box
	*	@param title the title of the dialog box
	*	@param model whether the box is modal or not
	*	@param a used to hold the info of the appointment being edited
	*
	**/

	public ApptDialog(CalFrame parent, String title, boolean modal, Appt a) {
		
		super(parent,title, modal);
		initComponents();
		
		jTextFieldTitle.setText(a.getTitle());
		jTextFieldDesc.setText(a.getDescription());
		jTextFieldLocation.setText(a.getLocation());
		
		jComboBoxMonth.setSelectedIndex(a.getStartMonth());
	
		//if the appt was originally specified with a start/stop time...   	  
		if(a.getDuration() > 0) {
		
			TIME = true;
			jComboBoxStartMinutes.setSelectedIndex(a.getStartMinute());
			jComboBoxStartHours.setSelectedIndex(a.getStartHour());
			jComboBoxEndMinutes.setSelectedIndex(a.getDuration() / 60);
			jComboBoxEndHours.setSelectedIndex(a.getDuration() % 60);
			
			jCheckBoxTime.setSelected(true);
		}
	
		TIME = false;
		jTextFieldYear.setText(String.valueOf(a.getStartYear()));
		jTextFieldDay.setText(String.valueOf(a.getStartDay()));
		
		jTextFieldEmail.setText(String.valueOf(a.getEmailAddress()));
		jTextFieldURL.setText(String.valueOf(a.getWebAddress()));
		
		
		appointment = a;
		EDIT = true;
	}

	/**
	*	Private method used to validate the times specified
	*	
	*	@return true/false whether the times are correct or not
	**/
	private boolean validateTimes() {
	
		if(jCheckBoxTime.isSelected()) {
	
			int startHours, startMinutes, endHours, endMinutes;

			startHours = Integer.parseInt((String)jComboBoxStartHours.getSelectedItem());
			startMinutes = Integer.parseInt((String)jComboBoxStartMinutes.getSelectedItem());
			endHours = Integer.parseInt((String)jComboBoxEndHours.getSelectedItem());
			endMinutes = Integer.parseInt((String)jComboBoxStartHours.getSelectedItem());

			if(jRadioButtonPM.isSelected()) {
				startHours = startHours + 12;
				if(startHours == 24) {
					startHours = 0;
				}
			}

			if(jRadioButtonPM2.isSelected()) {
				endHours = endHours + 12;
				if(endHours == 24) {
					endHours = 0;
				}
			}
	
			Calendar startTime = new GregorianCalendar(Integer.parseInt(jTextFieldYear.getText()),
								getMonthInt((String)jComboBoxMonth.getSelectedItem()),
								Integer.parseInt(jTextFieldDay.getText()),
								startHours, startMinutes);
	
			Calendar endTime = new GregorianCalendar(Integer.parseInt(jTextFieldYear.getText()),
								getMonthInt((String)jComboBoxMonth.getSelectedItem()),
								Integer.parseInt(jTextFieldDay.getText()),
								endHours, endMinutes);
	
			if(startTime.after(endTime))
				return false;
			else
				return true;

		}
		
		return true;

	}

	/**
	*	Private method used to validate a URL entered
	*	
	*	@return true/false whether the URL is valid
	*/
	private boolean validateURL()
	{
		if(jTextFieldURL.equals("")) {
			return true;
			
		}
		else {
			try {
				URL u = new URL(jTextFieldURL.getText());
			}
			catch(MalformedURLException e)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	*	Private method used to validate the text fields entered
	*	
	*	@return true/false whether the text fields all contain info
	**/

    private boolean validateTextFields()
	{
		if(jTextFieldTitle.getText().equals("") || jTextFieldDesc.getText().equals("") ||
				jTextFieldLocation.getText().equals("") || jTextFieldDay.getText().equals("") ||
				jTextFieldYear.getText().equals("")) {
			
			return false;
		}
		else {
			return true;
		}
	}


	/**
	*	Private method to get the integer value of a month according to
	*	GregorianCalendar
	*	
	*	
	*	@see java.util.GregorianCalendar
	*	@param s a string specifying a month
	*	@return the integer value of the specified month, -1 if invalid String
	**/
	private int getMonthInt(String s) {
		if(s.equals("January"))
			return GregorianCalendar.JANUARY;
		else if (s.equals("February"))
			return GregorianCalendar.FEBRUARY;
		else if (s.equals("March"))
			return GregorianCalendar.MARCH;
		else if (s.equals("April"))
			return GregorianCalendar.APRIL;
		else if (s.equals("May"))
			return GregorianCalendar.MAY;
		else if (s.equals("June"))
			return GregorianCalendar.JUNE;
		else if (s.equals("July"))
			return GregorianCalendar.JULY;
		else if (s.equals("August"))
			return GregorianCalendar.AUGUST;
		else if (s.equals("September"))
			return GregorianCalendar.SEPTEMBER;
		else if (s.equals("October"))
			return GregorianCalendar.OCTOBER;
		else if (s.equals("November"))
			return GregorianCalendar.NOVEMBER;
		else if (s.equals("December"))
			return GregorianCalendar.DECEMBER;
		
		return -1;
	}


	/** 
	*	Private method used to initialize everything in the dialog box
	*	This method was generated by Forte, which was used to create
	*	the initial layout of the dialog box
	**/
   private void initComponents() {
		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		jButtonAdd = new javax.swing.JButton();
		jButtonCancel = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jButtonClear = new javax.swing.JButton();
		jTextFieldTitle = new javax.swing.JTextField();
		jTextFieldDesc = new javax.swing.JTextField();
		jTextFieldLocation = new javax.swing.JTextField();
		jTextFieldEmail = new javax.swing.JTextField();
		jTextFieldURL = new javax.swing.JTextField();
		jTextFieldDay = new javax.swing.JTextField();
		jComboBoxMonth = new javax.swing.JComboBox();
		jTextFieldYear = new javax.swing.JTextField();
		jComboBoxStartHours = new javax.swing.JComboBox();
		jSeparator1 = new javax.swing.JSeparator();
		jRadioButtonAM = new javax.swing.JRadioButton();
		jRadioButtonPM = new javax.swing.JRadioButton();
		jSeparator2 = new javax.swing.JSeparator();
		jLabel10 = new javax.swing.JLabel();
		jComboBoxStartMinutes = new javax.swing.JComboBox();
		jCheckBoxTime = new javax.swing.JCheckBox();
		jComboBoxEndHours = new javax.swing.JComboBox();
		jComboBoxEndMinutes = new javax.swing.JComboBox();
		jRadioButtonAM2 = new javax.swing.JRadioButton();
		jRadioButtonPM2 = new javax.swing.JRadioButton();
		jLabel11 = new javax.swing.JLabel();
		
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});
		
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		
		jButtonAdd.setText("Add");
		jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonAddMouseClicked(evt);
			}
		});
		
		jPanel1.add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 120, -1));
		
		jButtonCancel.setText("Cancel");
		jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonCancelMouseClicked(evt);
			}
		});
		
		jPanel1.add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 130, -1));
		
		jLabel1.setText("Title");
		jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));
		
		jLabel2.setText("Description");
		jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));
		
		jLabel3.setText("Location");
		jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
		
		jLabel4.setText("Start Time");
		jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, -1));
		
		jLabel5.setText("Day");
		jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 30, -1));
		
		jLabel6.setText("Month");
		jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 40, -1));
		
		jLabel7.setText("Year");
		jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 30, -1));
		
		jLabel8.setText("Email (optional)");
		jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 90, -1));
		
		jLabel9.setText("Website (optional)");
		jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 110, -1));
		
		jButtonClear.setText("Clear");
		jButtonClear.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonClearMouseClicked(evt);
			}
		});
		
		jPanel1.add(jButtonClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 120, -1));
		
		jPanel1.add(jTextFieldTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 270, -1));
		
		jPanel1.add(jTextFieldDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 270, -1));
		
		jPanel1.add(jTextFieldLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 270, -1));
		
		jPanel1.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 270, -1));
		
		jPanel1.add(jTextFieldURL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 270, -1));
		
		jPanel1.add(jTextFieldDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 50, -1));
		
		jComboBoxMonth.setMaximumRowCount(12);
		jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
		jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBoxMonthActionPerformed(evt);
			}
		});
		
		jPanel1.add(jComboBoxMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 130, 20));
		
		jPanel1.add(jTextFieldYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 50, -1));
		
		jComboBoxStartHours.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		jPanel1.add(jComboBoxStartHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 50, 20));
		
		jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 390, 10));
		
		jRadioButtonAM.setText("AM");
		buttonGroup1.add(jRadioButtonAM);
		jPanel1.add(jRadioButtonAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 40, 20));
		
		jRadioButtonPM.setText("PM");
		buttonGroup1.add(jRadioButtonPM);
		jRadioButtonPM.setSelected(true);
		
		jPanel1.add(jRadioButtonPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 40, 20));
		
		jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 390, 10));
		
		jLabel10.setText("Recur every: (optional)");
		jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, -1));
		
		jComboBoxStartMinutes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
		jPanel1.add(jComboBoxStartMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 50, 20));
		
		jCheckBoxTime.setText("Use Time?");
		jPanel1.add(jCheckBoxTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 80, 20));
		
		
		jComboBoxEndHours.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		jPanel1.add(jComboBoxEndHours, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 50, 20));
		
		jComboBoxEndMinutes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
		jPanel1.add(jComboBoxEndMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 50, 20));
		
		jRadioButtonAM2.setText("AM");
		buttonGroup2.add(jRadioButtonAM2);
		jPanel1.add(jRadioButtonAM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 40, 20));
		
		jRadioButtonPM2.setText("PM");
		buttonGroup2.add(jRadioButtonPM2);
		jRadioButtonPM2.setSelected(true);
		jPanel1.add(jRadioButtonPM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 40, 20));
		
		jLabel11.setText("Stop Time");
		jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, -1));
		
		getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
		
		pack();
	}


	/**
	*	Private method used when the "Add button is clicked on the dialog box.
	*	Either adds the appointment or spawns an error message if invalid info entered
	**/
	private void jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {
	
		//if the all required fields are entered and are valid...process the input and return focus to CalFrame
		if(validateTextFields()) {
			//&& validateTimes() && validateURL())
		
		Calendar when = new GregorianCalendar(Integer.parseInt(jTextFieldYear.getText()),
												getMonthInt((String)jComboBoxMonth.getSelectedItem()),
												Integer.parseInt(jTextFieldDay.getText()));

			//if the appointment will have a start and end time...
			if(jCheckBoxTime.isSelected()) {
				
				int StartHours = Integer.parseInt((String)jComboBoxStartHours.getSelectedItem());
				int StartMinutes = Integer.parseInt((String)jComboBoxStartMinutes.getSelectedItem());
				int EndHours = Integer.parseInt((String)jComboBoxEndHours.getSelectedItem());
				int EndMinutes = Integer.parseInt((String)jComboBoxEndMinutes.getSelectedItem());
				
				
				if(jRadioButtonPM.isSelected()) {
					StartHours = StartHours + 12;
					if(StartHours == 24) {
						StartHours = 0;
					}
				}
				
				
				if(jRadioButtonPM2.isSelected()) {
					EndHours = EndHours + 12;
					if(EndHours == 24) {
						EndHours = 0;
					}
				}
				
				
				int duration = ((EndHours - StartHours) * 60) + (EndMinutes - StartMinutes);
				
				owner.addAppt(new Appt(StartHours, StartMinutes,
					when.get(Calendar.DAY_OF_MONTH),
					when.get(Calendar.MONTH),
					when.get(Calendar.YEAR),
					duration,
					jTextFieldTitle.getText(),
					jTextFieldDesc.getText(),
					jTextFieldLocation.getText(), Appt.NO_REMINDER,
					jTextFieldEmail.getText(), jTextFieldURL.getText())); 
				
			}
			
			//otherwise, no start/stop time specified
			else {
	
				owner.addAppt(new Appt(when.get(Calendar.DAY_OF_MONTH), when.get(Calendar.MONTH),
				when.get(Calendar.YEAR), jTextFieldTitle.getText(), 
				jTextFieldDesc.getText(), jTextFieldLocation.getText(),
				jTextFieldEmail.getText(), jTextFieldURL.getText()));
			}
		
			//if the appointment was opened for editing, delete the old version
			if(EDIT) {
				owner.deleteAppt(appointment);
			}
			setVisible(false);
		}//end if
			
		else { //otherwise show an error and return focus to the dialog box
			JOptionPane.showMessageDialog(this,
									"Not all required fields entered or not entered correctly",
									"Error",
									JOptionPane.ERROR_MESSAGE);
		}//end else
	}

	/**
	*	Private method used when the "Clear" button is clicked
	*	Resets all fields in the dialog box
	**/
	private void jButtonClearMouseClicked(java.awt.event.MouseEvent evt) {
		// Add your handling code here:
		jTextFieldTitle.setText("");
		jTextFieldDesc.setText("");
		jTextFieldLocation.setText("");
		jTextFieldEmail.setText("");
		jTextFieldURL.setText("");
		jTextFieldDay.setText("");
	}

	/**
	*	Private method used when the "Cancel" button is clicked
	*	Closes the dialog box, discarding anything entered
	**/
	private void jButtonCancelMouseClicked(java.awt.event.MouseEvent evt) {
		// Add your handling code here:
		setVisible(false);
		dispose();
	}


	/**
		Private method used when the user chooses a month.
		Sets a member variable to the integer value of the month chosen
	*/
	private void jComboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {
	
		JComboBox source = (JComboBox)evt.getSource();
		String monthString = (String)source.getSelectedItem();
		month = getMonthInt(monthString);
	}

	/**
	*	Private method to close the dialog bix
	**/
	private void closeDialog(java.awt.event.WindowEvent evt) {
		setVisible(false);
		dispose();
	}

	
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JButton jButtonAdd;
	private javax.swing.JButton jButtonCancel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JButton jButtonClear;
	private javax.swing.JTextField jTextFieldTitle;
	private javax.swing.JTextField jTextFieldDesc;
	private javax.swing.JTextField jTextFieldLocation;
	private javax.swing.JTextField jTextFieldEmail;
	private javax.swing.JTextField jTextFieldURL;
	private javax.swing.JTextField jTextFieldDay;
	private javax.swing.JComboBox jComboBoxMonth;
	private javax.swing.JTextField jTextFieldYear;
	private javax.swing.JComboBox jComboBoxStartHours;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JRadioButton jRadioButtonAM;
	private javax.swing.JRadioButton jRadioButtonPM;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JComboBox jComboBoxStartMinutes;
	private javax.swing.JCheckBox jCheckBoxTime;
	private javax.swing.JComboBox jComboBoxEndHours;
	private javax.swing.JComboBox jComboBoxEndMinutes;
	private javax.swing.JRadioButton jRadioButtonAM2;
	private javax.swing.JRadioButton jRadioButtonPM2;
	private javax.swing.JLabel jLabel11;
}

