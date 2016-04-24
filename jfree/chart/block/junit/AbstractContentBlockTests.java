/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -----------------------
 * AbstractBlockTests.java
 * -----------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: AbstractContentBlockTests.java,v 1.1 2005/06/27 15:35:06 mungady Exp $
 *
 * Changes
 * -------
 * 25-May-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.block.junit;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.block.AbstractContentBlock;
import org.jfree.chart.block.EmptyBlock;

/**
 * Tests for the {@link AbstractContentBlock} class.
 */
public class AbstractContentBlockTests extends TestCase {
    
    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(AbstractContentBlockTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public AbstractContentBlockTests(String name) {
        super(name);
    }
    
    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    public void testEquals() {
        // we are testing AbstractBlock, but use a concrete subclass
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        EmptyBlock b2 = new EmptyBlock(1.0, 2.0);
        assertTrue(b1.equals(b2));
        assertTrue(b2.equals(b2));
        
        // ID
        b1.setID("ABC");
        assertFalse(b1.equals(b2));
        b2.setID("ABC");
        assertTrue(b1.equals(b2));

        // backgroundPaint
        b1.setBackgroundPaint(Color.red);
        assertFalse(b1.equals(b2));
        b2.setBackgroundPaint(Color.red);
        assertTrue(b1.equals(b2));    

        // interiorBackgroundPaint
        b1.setInteriorBackgroundPaint(Color.blue);
        assertFalse(b1.equals(b2));
        b2.setInteriorBackgroundPaint(Color.blue);
        assertTrue(b1.equals(b2));
        
        // margin
        b1.setMargin(1.0, 2.0, 3.0, 4.0);
        assertFalse(b1.equals(b2));
        b2.setMargin(1.0, 2.0, 3.0, 4.0);
        assertTrue(b1.equals(b2));

        // border
        b1.setBorder(1.0, 2.0, 3.0, 4.0);
        assertFalse(b1.equals(b2));
        b2.setBorder(1.0, 2.0, 3.0, 4.0);
        assertTrue(b1.equals(b2));

        // padding
        b1.setPadding(1.0, 2.0, 3.0, 4.0);
        assertFalse(b1.equals(b2));
        b2.setPadding(1.0, 2.0, 3.0, 4.0);
        assertTrue(b1.equals(b2));
    
        // width
        b1.setDefaultWidth(99.0);
        assertFalse(b1.equals(b2));
        b2.setDefaultWidth(99.0);
        assertTrue(b1.equals(b2));
        
        // height
        b1.setDefaultHeight(33.0);
        assertFalse(b1.equals(b2));
        b2.setDefaultHeight(33.0);
        assertTrue(b1.equals(b2));
        
        // bounds
        b1.setBounds(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertFalse(b1.equals(b2));
        b2.setBounds(new Rectangle2D.Double(1.0, 2.0, 3.0, 4.0));
        assertTrue(b1.equals(b2));
        
    }

    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        b1.setBackgroundPaint(Color.green);
        b1.setInteriorBackgroundPaint(
            new GradientPaint(1.0f, 2.0f, Color.red, 3.0f, 4.0f, Color.blue)
        );
        EmptyBlock b2 = null;
        
        try {
            b2 = (EmptyBlock) b1.clone();
        }
        catch (CloneNotSupportedException e) {
            fail(e.toString());
        }
        assertTrue(b1 != b2);
        assertTrue(b1.getClass() == b2.getClass());
        assertTrue(b1.equals(b2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
        EmptyBlock b1 = new EmptyBlock(1.0, 2.0);
        b1.setBackgroundPaint(Color.green);
        b1.setInteriorBackgroundPaint(
            new GradientPaint(1.0f, 2.0f, Color.red, 3.0f, 4.0f, Color.blue)
        );
        EmptyBlock b2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(b1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            b2 = (EmptyBlock) in.readObject();
            in.close();
        }
        catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(b1, b2);
    }
   
}
