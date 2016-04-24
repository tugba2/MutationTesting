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
 * -----------------------------
 * AttributedTextTitleTests.java
 * -----------------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: AttributedTextTitleTests.java,v 1.3 2005/07/19 14:14:02 mungady Exp $
 *
 * Changes
 * -------
 * 23-Jun-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.title.junit;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.AttributedString;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.block.ArrangeParams;
import org.jfree.chart.block.ArrangeResult;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.title.AttributedTextTitle;

/**
 * Tests for the {@link AttributedTextTitle} class.
 */
public class AttributedTextTitleTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(AttributedTextTitleTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public AttributedTextTitleTests(String name) {
        super(name);
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    public void testEquals() {
        AttributedTextTitle t1 = new AttributedTextTitle();
        AttributedTextTitle t2 = new AttributedTextTitle();
        assertTrue(t1.equals(t2));
        assertTrue(t2.equals(t1));
        
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
        AttributedTextTitle t1 = new AttributedTextTitle();
        AttributedTextTitle t2 = new AttributedTextTitle();
        assertTrue(t1.equals(t2));
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }
    
    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        AttributedTextTitle t1 = new AttributedTextTitle();
        AttributedTextTitle t2 = null;
        try {
            t2 = (AttributedTextTitle) t1.clone();
        }
        catch (CloneNotSupportedException e) {
            System.err.println("Failed to clone.");
        }
        assertTrue(t1 != t2);
        assertTrue(t1.getClass() == t2.getClass());
        assertTrue(t1.equals(t2));
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {

        AttributedTextTitle t1 = new AttributedTextTitle(
                new AttributedString("Test").getIterator());
        AttributedTextTitle t2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(t1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            t2 = (AttributedTextTitle) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(t1, t2);

    }

    private static final double EPSILON = 0.0000001;
    
    private static final double EPSILON_F = 0.5;

    /** 
     * Test the arrangement with no constraints.
     */
    public void testArrangeNN1() {
        AttributedTextTitle title = new AttributedTextTitle(
                new AttributedString("This is a test.").getIterator());
        title.setDefaultWidth(123.4);
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.scale(120.0 / 72.0, 120.0 / 72.0);
        ArrangeResult r = title.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
        assertEquals(19.99, r.getHeight(), EPSILON_F);
    }

    public void testArrangeNN2() {
        AttributedString as = new AttributedString("This is a test.");
        as.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, 
                5, 7);
        AttributedTextTitle title = new AttributedTextTitle(as.getIterator());
        title.setMargin(1.0, 2.0, 3.0, 4.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.scale(120.0 / 72.0, 120.0 / 72.0);
        ArrangeResult r = title.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(92.0, r.getWidth(), EPSILON_F);
        assertEquals(20.02, r.getHeight(), EPSILON_F);
    }

    public void testArrangeFF() {
        AttributedTextTitle title = new AttributedTextTitle(
                new AttributedString("This is a test.").getIterator());
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
        AttributedTextTitle title = new AttributedTextTitle(
                new AttributedString("This is a test.").getIterator());
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
        AttributedTextTitle title = new AttributedTextTitle(
                new AttributedString("This is a test.").getIterator());
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
