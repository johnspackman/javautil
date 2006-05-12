/*---------------------------------------------------------------------------*\
  $Id$
  ---------------------------------------------------------------------------
  This software is released under a Berkeley-style license:

  Copyright (c) 2004-2006 Brian M. Clapper. All rights reserved.

  Redistribution and use in source and binary forms are permitted provided
  that: (1) source distributions retain this entire copyright notice and
  comment; and (2) modifications made to the software are prominently
  mentioned, and a copy of the original software (or a pointer to its
  location) are included. The name of the author may not be used to endorse
  or promote products derived from this software without specific prior
  written permission.

  THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
  WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.

  Effectively, this means you can do what you want with the software except
  remove this notice or take advantage of the author's name. If you modify
  the software and redistribute your modified version, you must indicate that
  your version is a modification of the original, and you must provide either
  a pointer to or a copy of the original.
\*---------------------------------------------------------------------------*/

package org.clapper.util.misc;

/**
 * Thrown by version-sensitive classes to indicate a fatal version mismatch.
 *
 * @version <tt>$Revision$</tt>
 *
 * @author Copyright &copy; 2004-2006 Brian M. Clapper
 */
public class VersionMismatchException extends NestedException
{
    /*----------------------------------------------------------------------*\
                         Private Static Variables
    \*----------------------------------------------------------------------*/

    /**
     * See JDK 1.5 version of java.io.Serializable
     */
    private static final long serialVersionUID = 1L;

    /*----------------------------------------------------------------------*\
                           Private Instance Data
    \*----------------------------------------------------------------------*/

    private String expectedVersion = null;
    private String foundVersion    = null;

    /*----------------------------------------------------------------------*\
                               Constructors
    \*----------------------------------------------------------------------*/

    /**
     * Constructs an exception containing an error message, but no
     * nested exception.
     *
     * @param message         the message to associate with this exception
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     */
    public VersionMismatchException (String message,
                                     String expectedVersion,
                                     String foundVersion)
    {
        super (message);
        this.expectedVersion = expectedVersion;
        this.foundVersion = foundVersion;
    }

    /**
     * Constructs an exception containing another exception and a message.
     *
     * @param message         the message to associate with this exception
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     * @param exception       the exception to contain
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     */
    public VersionMismatchException (String    message,
                                     Throwable exception,
                                     String    expectedVersion,
                                     String    foundVersion)
    {
	super (message, exception);
        this.expectedVersion = expectedVersion;
        this.foundVersion = foundVersion;
    }

    /**
     * Constructs an exception containing a resource bundle name, a message
     * key, and a default message (in case the resource bundle can't be
     * found). Using this constructor is equivalent to calling the
     * {@link #VersionMismatchException(String,String,String,Object[],String,String)}
     * constructor with a null pointer for the <tt>Object[]</tt> parameter.
     * Calls to {@link NestedException#getMessage(Locale)} will attempt to
     * retrieve the top-most message (i.e., the message from this exception,
     * not from nested exceptions) by querying the named resource bundle.
     * Calls to {@link NestedException#printStackTrace(PrintWriter,Locale)}
     * will do the same, where applicable. The message is not retrieved
     * until one of those methods is called, because the desired locale is
     * passed into <tt>getMessage()</tt> and <tt>printStackTrace()</tt>,
     * not this constructor.
     *
     * @param bundleName      resource bundle name
     * @param messageKey      the key to the message to find in the bundle
     * @param defaultMsg      the default message
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     * @see #VersionMismatchException(String,String,String,Object[],String,String)
     * @see NestedException#getLocalizedMessage
     */
    public VersionMismatchException (String bundleName,
                                     String messageKey,
                                     String defaultMsg,
                                     String expectedVersion,
                                     String foundVersion)
    {
        super (bundleName, messageKey, defaultMsg);
        this.expectedVersion = expectedVersion;
        this.foundVersion = foundVersion;
    }

    /**
     * Constructs an exception containing a resource bundle name, a message
     * key, and a default message (in case the resource bundle can't be
     * found). Using this constructor is equivalent to calling the
     * {@link #VersionMismatchException(String,String,String,Object[],String,String)}
     * constructor, with a null pointer for the <tt>Object[]</tt> parameter.
     * Calls to {@link NestedException#getMessage(Locale)} will attempt to
     * retrieve the top-most message (i.e., the message from this exception,
     * not from nested exceptions) by querying the named resource bundle.
     * Calls to {@link NestedException#printStackTrace(PrintWriter,Locale)}
     * will do the same, where applicable. The message is not retrieved
     * until one of those methods is called, because the desired locale is
     * passed into <tt>getMessage()</tt> and <tt>printStackTrace()</tt>,
     * not this constructor.
     *
     * @param bundleName      resource bundle name
     * @param messageKey      the key to the message to find in the bundle
     * @param defaultMsg      the default message
     * @param msgParams       parameters to the message, if any, or null
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     * @see #VersionMismatchException(String,String,String,Object[],String,String)
     * @see NestedException#getLocalizedMessage
     */
    public VersionMismatchException (String   bundleName,
                                     String   messageKey,
                                     String   defaultMsg,
                                     Object[] msgParams,
                                     String   expectedVersion,
                                     String   foundVersion)
    {
        super (bundleName, messageKey, defaultMsg, msgParams);
        this.expectedVersion = expectedVersion;
        this.foundVersion = foundVersion;
    }

    /**
     * Constructs an exception containing a resource bundle name, a message
     * key, a default message (in case the resource bundle can't be found),
     * and another exception. Using this constructor is equivalent to calling
     * the {@link #VersionMismatchException(String,String,String,Object[],String,String)}
     * constructor, with a null pointer for the <tt>Object[]</tt>
     * parameter. Calls to {@link #getMessage(Locale)} will attempt to
     * retrieve the top-most message (i.e., the message from this
     * exception, not from nested exceptions) by querying the named
     * resource bundle. Calls to
     * {@link #printStackTrace(PrintWriter,Locale)} will do the same, where
     * applicable. The message is not retrieved until one of those methods
     * is called, because the desired locale is passed into
     * <tt>getMessage()</tt> and <tt>printStackTrace()</tt>, not this
     * constructor.
     *
     * @param bundleName      resource bundle name
     * @param messageKey      the key to the message to find in the bundle
     * @param defaultMsg      the default message
     * @param exception       the exception to nest
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     * @see #VersionMismatchException(String,String,String,Object[],String,String)
     * @see NestedException#getMessage(Locale)
     */
    public VersionMismatchException (String    bundleName,
                                     String    messageKey,
                                     String    defaultMsg,
                                     Throwable exception,
                                     String    expectedVersion,
                                     String    foundVersion)
    {
        this (bundleName,
              messageKey,
              defaultMsg,
              null,
              exception,
              expectedVersion,
              foundVersion);
    }

    /**
     * Constructs an exception containing a resource bundle name, a message
     * key, a default message format (in case the resource bundle can't be
     * found), arguments to be incorporated in the message via
     * <tt>java.text.MessageFormat</tt>, and another exception. Calls to
     * {@link NestedException#getMessage(Locale)} will attempt to retrieve the
     * top-most message (i.e., the message from this exception, not from
     * nested exceptions) by querying the named resource bundle. Calls to
     * {@link NestedException#printStackTrace(PrintWriter,Locale)} will do
     * the same, where applicable. The message is not retrieved until one
     * of those methods is called, because the desired locale is passed
     * into <tt>getMessage()</tt> and <tt>printStackTrace()</tt>, not this
     * constructor.
     *
     * @param bundleName      resource bundle name
     * @param messageKey      the key to the message to find in the bundle
     * @param defaultMsg      the default message
     * @param msgParams       parameters to the message, if any, or null
     * @param exception       exception to be nested
     * @param expectedVersion string representing the expected version
     * @param foundVersion    string representing the found version
     *
     * @see #getExpectedVersion
     * @see #getFoundVersion
     * @see #VersionMismatchException(String,String,String,Object[],String,String)
     * @see NestedException#getMessage(Locale)
     */
    public VersionMismatchException (String    bundleName,
                                     String    messageKey,
                                     String    defaultMsg,
                                     Object[]  msgParams,
                                     Throwable exception,
                                     String    expectedVersion,
                                     String    foundVersion)
    {
        super (bundleName, messageKey, defaultMsg, msgParams, exception);
        this.expectedVersion = expectedVersion;
        this.foundVersion = foundVersion;
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Get the expected version string from this exception.
     *
     * @return the expected version string
     */
    public String getExpectedVersion()
    {
        return expectedVersion;
    }

    /**
     * Get the found version string from this exception.
     *
     * @return the found version string
     */
    public String getFoundVersion()
    {
        return foundVersion;
    }
}
