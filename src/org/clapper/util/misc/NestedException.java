/*---------------------------------------------------------------------------*\
  $Id$
\*---------------------------------------------------------------------------*/

package org.clapper.util.misc;

import java.io.*;

/**
 * <p><tt>NestedException</tt> defines a special <tt>Exception</tt> class
 * that permits exceptions to wrap other exceptions. While
 * <tt>NestedException</tt> can be used directly, it is most useful as a
 * base class for other exception classes.</p>
 *
 * @version <tt>$Revision$</tt>
 */
public class NestedException extends Exception
{
    /*----------------------------------------------------------------------*\
                             Private Variables
    \*----------------------------------------------------------------------*/

    private Throwable containedException = null;

    /*----------------------------------------------------------------------*\
                                Constructor
    \*----------------------------------------------------------------------*/

    /**
     * Default constructor, for an exception with no nested exception and
     * no message.
     */
    public NestedException()
    {
	super();
    }

    /**
     * Constructs an exception containing another exception, but no message
     * of its own.
     *
     * @param exception  the exception to contain
     */
    public NestedException (Throwable exception)
    {
	super();
	this.containedException = exception;
    }

    /**
     * Constructs an exception containing an error message, but no
     * nested exception.
     *
     * @param message  the message to associate with this exception
     */
    public NestedException (String message)
    {
        super (message);
    }

    /**
     * Constructs an exception containing another exception and a message.
     *
     * @param message    the message to associate with this exception
     * @param exception  the exception to contain
     */
    public NestedException (String message, Throwable exception)
    {
	super (message);
	this.containedException = exception;
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Returns the error message string for this exception. If the
     * exception was instantiated with a message of its own, then that
     * message is returned. Otherwise, this method returns the class name,
     * along with the class name of the first nested exception, if any.
     * Unlike the parent <tt>Exception</tt> class, this method will never
     * return null.
     *
     * @return  the error message string for this exception
     */
    public String getMessage()
    {
	StringBuffer buf = new StringBuffer();
	String       msg = super.getMessage();

	if (msg != null)
	    buf.append (msg);

	else
        {
            buf.append (this.getClass().getName());
            if (containedException != null)
            {
                buf.append (" (contains an ");
                buf.append (containedException.getClass().getName());
                buf.append (")");
            }
        }

        return buf.toString();
    }

    /**
     * Creates a localized version of this exception's error message.
     * Currently, this method is a synonym for <tt>getMessage()</tt>,
     * though it may be extended in the future.
     *
     * @return  The localized version of the this exception's message
     */
    public String getLocalizedMessage()
    {
	return getMessage();
    }

    /**
     * Gets the exception that's nested within this <tt>NestedException</tt>,
     * if any.
     *
     * @return the nested exception, or null
     */
    public Throwable getNestedException()
    {
        return containedException;
    }

    /**
     * Returns a short description of this exception. If this object was
     * created with an error message string, then the result is the
     * concatenation of three strings:
     *
     * <ul>
     *   <li> The name of the actual class of this object 
     *   <li> ": " (a colon and a space) 
     *   <li> The result of the {@link #getMessage} method for this object 
     * </ul>
     *
     * If this object was created with no error message string, then the
     * name of the actual class of this object is returned.
     *
     * @return  a string representation of this object
     */
    public String toString()
    {
	String s = getClass().getName();
	String message = getLocalizedMessage();
	return (message != null) ? (s + ": " + message) : s;
    }

    /**
     * Prints this exception and its backtrace to the standard error
     * stream. If this exception contains another, nested exception, its
     * stack trace is also printed.
     */
    public void printStackTrace()
    { 
        printStackTrace (System.err);
    }

    /**
     * Prints this exception and its backtrace to the specified
     * <tt>PrintStream</tt>. If this exception contains another, nested
     * exception, its stack trace is also printed.
     *
     * @param out <tt>PrintStream</tt> to use for output
     */
    public void printStackTrace (PrintStream out)
    { 
        printStackTrace (new PrintWriter (out));
    }

    /**
     * Prints this exception and its backtrace to the specified
     * <tt>PrintWriter</tt>. If this exception contains another, nested
     * exception, its stack trace is also printed.
     *
     * @param out <tt>PrintStream</tt> to use for output
     */
    public void printStackTrace (PrintWriter out)
    {
	synchronized (out)
        {
            super.printStackTrace (out);
            if (this.containedException != null)
            {
                Throwable  ex = this.containedException;

                while (ex != null)
                {
                    out.println ("*** Nested exception");
                    ex.printStackTrace (out);

                    if (ex instanceof NestedException)
                        ex = ((NestedException) ex).getNestedException();
                    else
                        ex = null;
                }
            }

            out.flush();
        }
    }
}
