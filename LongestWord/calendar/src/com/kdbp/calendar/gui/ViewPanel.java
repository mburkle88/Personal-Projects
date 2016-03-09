package com.kdbp.calendar.gui;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import com.kdbp.calendar.data.*;


/**
*	This class is responsible for containing, managing and 
**/
public class ViewPanel extends JPanel
{
   private LinkedList days; 
   private Color DEFAULT_BG_COLOR = Color.white;
   
   /**
   *	The only constructor. Sets the background color to be white.
   **/
   public ViewPanel ()
   { 
      super();
      setBackground (DEFAULT_BG_COLOR);
   }
   
   /**
   *	Sets the current data.
   *	@param days A linked list, ala java.util.LinkedList, containing
   *	the new CalDay data.
   **/
   public void setCurrentDays (LinkedList days)
   { 
      this.days = days;
   }
   
   /**
   *	NOT IMPLEMENTED
   **/
   public void setHighlightedDate (GregorianCalendar date )
   {

   }
   
   /**
   *	Repaints this JPanel.
   *	@param g A Graphics object used to display the JPanel.
   **/
   public void paintComponent (Graphics g)
   {
   	  super.paintComponent(g);   	  
   }
}
