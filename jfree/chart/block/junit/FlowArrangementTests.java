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
 * -------------------------
 * FlowArrangementTests.java
 * -------------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: FlowArrangementTests.java,v 1.3 2005/07/19 14:25:21 mungady Exp $
 *
 * Changes
 * -------
 * 04-Feb-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.block.junit;

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
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.block.FlowArrangement;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.VerticalAlignment;

/**
 * Tests for the {@link FlowArrangement} class.
 */
public class FlowArrangementTests extends TestCase {
    
    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(FlowArrangementTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public FlowArrangementTests(String name) {
        super(name);
    }
    
    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    public void testEquals() {
        FlowArrangement f1 = new FlowArrangement(
            HorizontalAlignment.LEFT, VerticalAlignment.TOP, 1.0, 2.0
        );
        FlowArrangement f2 = new FlowArrangement(
            HorizontalAlignment.LEFT, VerticalAlignment.TOP, 1.0, 2.0
        );
        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f1));

        f1 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.TOP, 1.0, 2.0
        );
        assertFalse(f1.equals(f2));
        f2 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.TOP, 1.0, 2.0
        );
        assertTrue(f1.equals(f2));

        f1 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.0, 2.0
        );
        assertFalse(f1.equals(f2));
        f2 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.0, 2.0
        );
        assertTrue(f1.equals(f2));
    
        f1 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.1, 2.0
        );
        assertFalse(f1.equals(f2));
        f2 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.1, 2.0
        );
        assertTrue(f1.equals(f2));
        
        f1 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.1, 2.2
        );
        assertFalse(f1.equals(f2));
        f2 = new FlowArrangement(
            HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM, 1.1, 2.2
        );
        assertTrue(f1.equals(f2));
        
    }

    /**
     * Immutable - cloning is not necessary.
     */
    public void testCloning() {
        FlowArrangement f1 = new FlowArrangement();
        assertFalse(f1 instanceof Cloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
        FlowArrangement f1 = new FlowArrangement(
            HorizontalAlignment.LEFT, VerticalAlignment.TOP, 1.0, 2.0
        );
        FlowArrangement f2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(f1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            f2 = (FlowArrangement) in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(f1, f2);
    }
    
    public static final double EPSILON = 0.000000001;
    
    public void testArrangeNNWithFlowArrangement() {
        BlockContainer bc = new BlockContainer(new FlowArrangement());
        bc.add(new EmptyBlock(1.0, 2.0));
        bc.setMargin(1.0, 2.0, 3.0, 4.0);
        bc.setPadding(5.0, 6.0, 7.0, 8.0);
        bc.setDefaultHeight(123.4);
        bc.setDefaultWidth(234.5);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = bc.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(234.5, r.getWidth(), EPSILON);
        assertEquals(123.4, r.getHeight(), EPSILON);
    }
   
}
