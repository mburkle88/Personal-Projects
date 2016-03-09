/**
*	ApptTitle.java
* A special JLabel showing only the title of an appointment.
**/

package com.kdbp.calendar.gui.view;


import com.kdbp.calendar.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApptTitle extends JLabel {
	private Appt appt;

	public ApptTitle( Appt appt )
	{
		super( appt.toString());
		this.appt = appt;
		setHorizontalAlignment(this.LEFT);
		this.setBackground(Color.white);
	}
	
	public Appt getAppt() {
		return appt;
	}
	
	public String toString()
	{
		return this.getText();
	}
}
