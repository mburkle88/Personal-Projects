/*
 * XmlParserErrorHandler.java
 *
 * Created on November 24, 2001, 7:35 PM
 */

package com.kdbp.calendar.data;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.w3c.dom.*;

/**
* This error handler is to report warnings and errors with the 
* XML Parser. It is modeled after Java's sample code.
*
* @author  Paul Miles
* @version 1.0
**/

public class XmlParserErrorHandler implements ErrorHandler {

	/**
	* Returns a string describing parse exception details
	**/
	private String getParseExceptionInfo(SAXParseException spe) {
		String systemId = spe.getSystemId();
		if (systemId == null) {
			systemId = "null";
		}
		String info = "URI=" + systemId +
		" Line=" + spe.getLineNumber() +
		": " + spe.getMessage();
		return info;
	}
	
	// The following methods are standard SAX ErrorHandler methods.
	// See SAX documentation for more info.
	public void warning(SAXParseException spe) throws SAXException {
		//out.println("Warning: " + getParseExceptionInfo(spe));
		//Treat errors as warnings
		String message = "Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}

	public void error(SAXParseException spe) throws SAXException {
		String message = "Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}

	public void fatalError(SAXParseException spe) throws SAXException {
		String message = "Fatal Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}
}