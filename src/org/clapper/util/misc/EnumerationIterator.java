/*---------------------------------------------------------------------------*\
  $Id$
  ---------------------------------------------------------------------------
  This software is released under a Berkeley-style license:

  Copyright (c) 2004 Brian M. Clapper. All rights reserved.

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

import java.util.Iterator;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * The <tt>EnumerationIterator</tt> class is an adapter that makes a
 * <tt>java.util.Enumeration</tt> object look and behave like a
 * <tt>java.util.Iterator</tt> objects. The <tt>EnumerationIterator</tt>
 * class implements the <tt>Iterator</tt> interface and wraps an existing
 * <tt>Enumeration</tt> object. This class is the conceptual opposite of
 * the <tt>Collections.enumeration()</tt> method in the <tt>java.util</tt>
 * package.
 *
 * @see java.util.Iterator
 * @see java.util.Enumeration
 *
 * @version <tt>$Revision$</tt>
 */
public class EnumerationIterator implements Iterator
{
    /*----------------------------------------------------------------------*\
                           Private Data Elements
    \*----------------------------------------------------------------------*/

    /**
     * The underlying Enumeration.
     */
    private Enumeration enum = null;

    /*----------------------------------------------------------------------*\
                                Constructor
    \*----------------------------------------------------------------------*/

    /**
     * Allocate a new <tt>EnumerationIterator</tt> object that will
     * forward its calls to the specified <tt>Enumeration</tt>.
     *
     * @param enum  The <tt>Enumeration</tt> to which to forward calls
     */
    public EnumerationIterator (Enumeration enum)
    {
        this.enum = enum;
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Determine whether the underlying <tt>Enumeration</tt> has more
     * elements.
     *
     * @return <tt>true</tt> if and only if a call to
     *         <tt>next()</tt> will return an element,
     *         <tt>false</tt> otherwise.
     *
     * @see #next()
     * @see Enumeration#hasMoreElements
     */
    public boolean hasNext()
    {
        return enum.hasMoreElements();
    }

    /**
     * Get the next element from the underlying <tt>Enumeration</tt>.
     *
     * @return the next element from the underlying <tt>Enumeration</tt>
     *
     * @exception NoSuchElementException No more elements exist
     *
     * @see Iterator#next
     */
    public Object next() throws NoSuchElementException
    {
        return enum.nextElement();
    }

    /**
     * Removes from the underlying collection the last element returned by
     * the iterator. Not supported by this class.
     *
     * @throws IllegalStateException         doesn't
     * @throws UnsupportedOperationException unconditionally
     */
    public void remove()
        throws IllegalStateException,
               UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}