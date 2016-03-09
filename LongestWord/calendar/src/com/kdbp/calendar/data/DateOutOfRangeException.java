/*
 * DateOutOfRangeException.java
 *
 * Created on November 27, 2001, 8:25 PM
 */

package com.kdbp.calendar.data;

/**
 *
 * @author  Paul Miles
 * @version 1.0
 */
public class DateOutOfRangeException extends java.lang.Exception {

    /**
     * Creates new <code>DateOutOfRangeException</code> without detail message.
     */
    public DateOutOfRangeException() {
    }


    /**
     * Constructs an <code>DateOutOfRangeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DateOutOfRangeException(String msg) {
        super(msg);
    }
}


