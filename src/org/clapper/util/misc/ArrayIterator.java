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
import java.util.NoSuchElementException;

/**
 * The <code>ArrayIterator</code> class provides a bridge between an array
 * of objects and an <code>Iterator</code>. It's useful in cases where you
 * have an array, but you need an <code>Iterator</code>; using an instance
 * of <code>ArrayIterator</code> saves copying the array's contents into a
 * <code>Collection</code>, just to get an <tt>Iterator</tt>.
 *
 * @see java.util.Iterator
 *
 * @version <tt>$Revision$</tt>
 */
public class ArrayIterator implements Iterator
{
    /*----------------------------------------------------------------------*\
                           Private Data Elements
    \*----------------------------------------------------------------------*/

    /**
     * The underlying Array.
     */
    private Object array[] = null;

    /**
     * The next array index.
     */
    private int next = 0;

    /*----------------------------------------------------------------------*\
                                Constructor
    \*----------------------------------------------------------------------*/

    /**
     * Allocate a new <code>ArrayIterator</code> object that will
     * iterate over the specified array of objects.
     *
     * @param array  The array over which to iterate
     */
    public ArrayIterator (Object array[])
    {
        this.array = array;
    }

    /**
     * Allocate a new <code>ArrayIterator</code> object that will iterate
     * over the specified array of objects, starting at a particular index.
     * The index isn't checked for validity until <tt>next()</tt> is called.
     *
     * @param array  The array over which to iterate
     * @param index  The index at which to start
     */
    public ArrayIterator (Object array[], int index)
    {
        this.array = array;
        this.next  = index;
    }

    /*----------------------------------------------------------------------*\
                              Public Methods
    \*----------------------------------------------------------------------*/

    /**
     * Get the index of the next element to be retrieved. This index value
     * might be past the end of the array.
     *
     * @return the index
     */
    public int getNextIndex()
    {
        return next;
    }

    /**
     * Determine whether the underlying <code>Iterator</code> has more
     * elements.
     *
     * @return <code>true</code> if and only if a call to
     *         <code>next()</code> will return an element,
     *         <code>false</code> otherwise.
     *
     * @see #next
     */
    public boolean hasNext()
    {
        return (next < array.length);
    }

    /**
     * Get the next element from the underlying array.
     *
     * @return the next element from the underlying array
     *
     * @exception java.util.NoSuchElementException
     *            No more elements exist
     *
     * @see #previous()
     * @see java.util.Iterator#next
     */
    public Object next() throws NoSuchElementException
    {
        Object result = null;

        try
        {
            result = array[next++];
        }

        catch (ArrayIndexOutOfBoundsException ex)
        {
            throw new NoSuchElementException();
        }

        return result;
    }

    /**
     * Get the previous element from the underlying array. This method
     * decrements the iterator's internal index by one, and returns the
     * corresponding element.
     *
     * @return the previous element from the underlying array
     *
     * @exception java.util.NoSuchElementException
     *            Attempt to move internal index before the first array element
     *
     * @see #next()
     */
    public Object previous() throws NoSuchElementException
    {
        Object result = null;

        try
        {
            result = array[--next];
        }

        catch (ArrayIndexOutOfBoundsException ex)
        {
            throw new NoSuchElementException();
        }

        return result;
    }

    /**
     * Required by the <tt>Iterator</tt> interface, but not supported by
     * this class.
     *
     * @throws UnsupportedOperationException  unconditionally
     */
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}