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
 * ---------------------
 * LegendTitleTests.java
 * ---------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: LegendTitleTests.java,v 1.6 2005/07/19 14:14:02 mungady Exp $
 *
 * Changes
 * -------
 * 25-Feb-2005 : Version 1 (DG);
 * 16-Mar-2005 : Extended testEquals() (DG);
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

/**
 * Some tests for the {@link LegendTitle} class.
 */
public class LegendTitleTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(LegendTitleTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public LegendTitleTests(String name) {
        super(name);
    }

    /**
     * Check that the equals() method distinguishes all fields.
     */
    public void testEquals() {
        XYPlot plot1 = new XYPlot();
        LegendTitle t1 = new LegendTitle(plot1);
        LegendTitle t2 = new LegendTitle(plot1);
        assertEquals(t1, t2);
        
        t1.setBackgroundPaint(
            new GradientPaint(1.0f, 2.0f, Color.red, 3.0f, 4.0f, Color.yellow)
        );
        assertFalse(t1.equals(t2));
        t2.setBackgroundPaint(
            new GradientPaint(1.0f, 2.0f, Color.red, 3.0f, 4.0f, Color.yellow)
        );
        assertTrue(t1.equals(t2));
        
        t1.setLegendItemGraphicEdge(RectangleEdge.BOTTOM);
        assertFalse(t1.equals(t2));
        t2.setLegendItemGraphicEdge(RectangleEdge.BOTTOM);
        assertTrue(t1.equals(t2));
        
        t1.setLegendItemGraphicAnchor(RectangleAnchor.BOTTOM_LEFT);
        assertFalse(t1.equals(t2));
        t2.setLegendItemGraphicAnchor(RectangleAnchor.BOTTOM_LEFT);
        assertTrue(t1.equals(t2));
        
        t1.setLegendItemGraphicLocation(RectangleAnchor.TOP_LEFT);
        assertFalse(t1.equals(t2));
        t2.setLegendItemGraphicLocation(RectangleAnchor.TOP_LEFT);
        assertTrue(t1.equals(t2));
        
        t1.setItemFont(new Font("Dialog", Font.PLAIN, 19));
        assertFalse(t1.equals(t2));
        t2.setItemFont(new Font("Dialog", Font.PLAIN, 19));
        assertTrue(t1.equals(t2));
    }

    /**
     * Two objects that are equal are required to return the same hashCode. 
     */
    public void testHashcode() {
        XYPlot plot1 = new XYPlot();
        LegendTitle t1 = new LegendTitle(plot1);
        LegendTitle t2 = new LegendTitle(plot1);
        assertTrue(t1.equals(t2));
        int h1 = t1.hashCode();
        int h2 = t2.hashCode();
        assertEquals(h1, h2);
    }
    
    /**
     * Confirm that cloning works.
     */
    public void testCloning() {
        XYPlot plot = new XYPlot();
        LegendTitle t1 = new LegendTitle(plot);
        t1.setBackgroundPaint(
            new GradientPaint(1.0f, 2.0f, Color.red, 3.0f, 4.0f, Color.yellow)
        );
        LegendTitle t2 = null;
        try {
            t2 = (LegendTitle) t1.clone();
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

        XYPlot plot = new XYPlot();
        LegendTitle t1 = new LegendTitle(plot);
        LegendTitle t2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(t1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            t2 = (LegendTitle) in.readObject();
            in.close();
        }
        catch (Exception e) {
            fail(e.toString());
        }
        assertTrue(t1.equals(t2));
        assertTrue(t2.getSources()[0].equals(plot));

    }

    private static final double EPSILON = 0.000000001;
    
    /**
     * Adds a {@link LegendTitle} to a grid and checks that the unconstrained
     * arrangement returns the correct size.
     */
    public void testArrangeFN() {
        LegendTitle t = new LegendTitle(new PiePlot());
        t.setDefaultHeight(34.0);
        t.setMargin(1.0, 2.0, 3.0, 4.0);
        t.setPadding(5.0, 6.0, 7.0, 8.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = t.arrange(g2, new RectangleConstraint(100.0, null), 
                new ArrangeParams());
        assertEquals(100.0, r.getWidth(), EPSILON);
        assertEquals(34.0, r.getHeight(), EPSILON);        
    }
    
    /**
     * Adds a {@link LegendTitle} to a grid and checks that the unconstrained
     * arrangement returns the correct size.
     */
    public void testArrangeNF() {
        LegendTitle t = new LegendTitle(new PiePlot());
        t.setDefaultWidth(34.0);
        t.setMargin(1.0, 2.0, 3.0, 4.0);
        t.setPadding(5.0, 6.0, 7.0, 8.0);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = t.arrange(g2, new RectangleConstraint(null, 100.0), 
                new ArrangeParams());
        assertEquals(34.0, r.getWidth(), EPSILON);        
        assertEquals(100.0, r.getHeight(), EPSILON);
    }
    
}
