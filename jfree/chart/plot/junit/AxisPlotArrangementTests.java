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
 * AxisPlotArrangementTests.java
 * -----------------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: AxisPlotArrangementTests.java,v 1.2 2005/07/19 14:18:20 mungady Exp $
 *
 * Changes
 * -------
 * 11-May-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot.junit;

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
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.plot.AxisPlotArrangement;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.Size2D;

/**
 * Some tests for the {@link AxisPlotArrangement} class.
 */
public class AxisPlotArrangementTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(AxisPlotArrangementTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public AxisPlotArrangementTests(String name) {
        super(name);
    }
    
    /**
     * Some checks for the equals() method.
     */
    public void testEquals() {
        AxisPlotArrangement a1 = new AxisPlotArrangement();
        AxisPlotArrangement a2 = new AxisPlotArrangement();
        assertTrue(a1.equals(a2));
        assertTrue(a2.equals(a1));
    }
    
    /**
     * Serialize an instance, restore it, and check for equality.
     */
    public void testSerialization() {
        AxisPlotArrangement a1 = new AxisPlotArrangement();
        AxisPlotArrangement a2 = null;
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(a1);
            out.close();

            ObjectInput in = new ObjectInputStream(
                new ByteArrayInputStream(buffer.toByteArray())
            );
            a2 = (AxisPlotArrangement) in.readObject();
            in.close();
        }
        catch (Exception e) {
            fail(e.toString());
        }
        assertEquals(a1, a2);
    }
    
    private static final double EPSILON = 0.00000000000001;
    
    public void testArrangeWithMargin() {
        BufferedImage image = new BufferedImage(
            200, 100, BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2 = image.createGraphics();

        BlockContainer container 
            = new BlockContainer(new AxisPlotArrangement());
        container.setMargin(1.0, 2.0, 3.0, 4.0);
        container.add(new EmptyBlock(5.0, 10.0), RectangleEdge.TOP);
        container.add(new EmptyBlock(6.0, 11.0), RectangleEdge.LEFT);
        container.add(new EmptyBlock(7.0, 12.0), RectangleEdge.BOTTOM);
        container.add(new EmptyBlock(8.0, 13.0), RectangleEdge.RIGHT);
       
        RectangleConstraint constraint = new RectangleConstraint(100.0, 110.0);
        ArrangeResult r = container.arrange(g2, constraint, 
                new ArrangeParams());
        Size2D size = r.getSize();
        
        assertEquals(100.0, size.width, EPSILON);
        assertEquals(110.0, size.height, EPSILON);
        AxisPlotArrangement arrangement 
            = (AxisPlotArrangement) container.getArrangement();
        assertEquals(6.0, arrangement.getX0(), EPSILON);
        assertEquals(86.0, arrangement.getX1(), EPSILON);
        assertEquals(10.0, arrangement.getY0(), EPSILON);
        assertEquals(94.0, arrangement.getY1(), EPSILON);
    }


}
