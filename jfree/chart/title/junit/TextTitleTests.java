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
 * -------------------
 * TextTitleTests.java
 * -------------------
 * (C) Copyright 2004, 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: TextTitleTests.java,v 1.8 2005/07/19 14:14:03 mungady Exp $
 *
 * Changes
 * -------
 * 17-Feb-2004 : Version 1 (DG);
 * 06-Jun-2005 : Use GradientPaint in equals() test (DG);
 *
 */

package org.jfree.chart.title.junit;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.block.ArrangeParams;
import org.jfree.chart.block.ArrangeResult;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.title.TextTitle;

/**
 * Tests for the {@link TextTitle} class.
 */
public class TextTitleTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(TextTitleTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public TextTitleTests(String name) {
        super(name);
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    public void testEquals() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
        assertEquals(t1, t2);
        
        t1.setText("Test 1");
        assertFalse(t1.equals(t2));
        t2.setText("Test 1");
        assertTrue(t1.equals(t2));
        
        Font f = new Font("SansSerif", Font.PLAIN, 15);
        t1.setFont(f);
        assertFalse(t1.equals(t2));
        t2.setFont(f);
        assertTrue(t1.equals(t2));
        
        // paint
        t1.setPaint(new GradientPaint(1.0f, 2.0f, Color.red, 
                3.0f, 4.0f, Color.blue));
        assertFalse(t1.equals(t2));
        t2.setPaint(new GradientPaint(1.0f, 2.0f, Color.red, 
                3.0f, 4.0f, Color.blue));
        assertTrue(t1.equals(t2));
        
        // backgroundPaint
        t1.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.red, 
                2.0f, 1.0f, Color.blue));
        assertFalse(t1.equals(t2));
        t2.setBackgroundPaint(new GradientPaint(4.0f, 3.0f, Color.red, 
                2.0f, 1.0f, Color.blue));
        assertTrue(t1.equals(t2));
        
    }

    /**
     * Two objects that are equal are required to return the same hashCode. 
     */
    public void testHashcode() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = new TextTitle();
        assertTrue(t1.equals(t2));
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }
    
    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        TextTitle t1 = new TextTitle();
        TextTitle t2 = null;
        try {
            t2 = (TextTitle) t1.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println("TextTitleTests.testCloning: failed to clone.");
        }
        assertTrue(t1 != t2);
        assertTrue(t1.getClass() == t2.getClass());
        assertTrue(t1.equals(t2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        TextTitle t1 = new TextTitle("Test");
        TextTitle t2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(t1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            t2 = (TextTitle) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(t1, t2);

    }

    private static final double EPSILON = 0.0000001;
    
    private static final double EPSILON_F = 0.5;

    public void testArrangeNN() {
        TextTitle title = new TextTitle("This is a test.");
        title.setDefaultWidth(123.4);
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = title.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
        assertEquals(19.99, r.getHeight(), EPSILON_F);
    }

    public void testArrangeFF() {
        TextTitle title = new TextTitle("This is a test.");
        title.setDefaultWidth(123.4);
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = title.arrange(g2, 
                new RectangleConstraint(123.4, 56.7), new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
        assertEquals(56.7, r.getHeight(), EPSILON);
    }
    
    public void testArrangeFN() {
        TextTitle title = new TextTitle("This is a test.");
        title.setDefaultWidth(123.4);
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = title.arrange(g2, new RectangleConstraint(99.9, null),
                new ArrangeParams());
        assertEquals(99.9, r.getWidth(), EPSILON);
        assertEquals(19.99, r.getHeight(), EPSILON_F);
    }

    public void testArrangeNF() {
        TextTitle title = new TextTitle("This is a test.");
        title.setDefaultWidth(123.4);
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = title.arrange(g2, new RectangleConstraint(null, 29.9),
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
        assertEquals(29.9, r.getHeight(), EPSILON);
    }
 
}
