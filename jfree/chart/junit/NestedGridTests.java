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
 * --------------------
 * NestedGridTests.java
 * --------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: NestedGridTests.java,v 1.4 2005/07/19 14:24:22 mungady Exp $
 *
 * Changes
 * -------
 * 11-May-2005 : Version 1 (DG);
 * 
 */

package org.jfree.chart.junit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.NestedGrid;
import org.jfree.chart.NestedGridKey;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.block.ArrangeParams;
import org.jfree.chart.block.ArrangeResult;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.ColorBlock;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.block.FlowArrangement;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;

/**
 * Some tests for the {@link NestedGrid} class.
 */
public class NestedGridTests extends TestCase {

    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(NestedGridTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public NestedGridTests(String name) {
        super(name);
    }

    /**
     * Some general checks.
     */
    public void testGeneral() {
        ColorBlock b1 = new ColorBlock(Color.red, 10.0, 20.0);
        ColorBlock b2 = new ColorBlock(Color.blue, 30.0, 40.0);
        
        NestedGrid g1 = new NestedGrid();
        g1.put(0, 0, b1);
        assertEquals(b1, g1.get(0, 0));
        
        NestedGrid g2 = new NestedGrid();
        g2.put(0, 0, b1);
        g2.put(new NestedGridKey(0, 0), RectangleEdge.LEFT, b2);
        assertEquals(b2, g2.get(0, 0));
        assertEquals(b1, g2.get(0, 1));

        NestedGrid g3 = new NestedGrid();
        g3.put(0, 0, b1);
        g3.put(new NestedGridKey(0, 0), RectangleEdge.RIGHT, b2);
        assertEquals(b1, g3.get(0, 0));
        assertEquals(b2, g3.get(0, 1));
    
        NestedGrid g4 = new NestedGrid();
        g4.put(0, 0, b1);
        g4.put(new NestedGridKey(0, 0), RectangleEdge.TOP, b2);
        assertEquals(b2, g4.get(0, 0));
        assertEquals(b1, g4.get(1, 0));

        NestedGrid g5 = new NestedGrid();
        g5.put(0, 0, b1);
        g5.put(new NestedGridKey(0, 0), RectangleEdge.BOTTOM, b2);
        assertEquals(b1, g5.get(0, 0));
        assertEquals(b2, g5.get(1, 0));
    }
    
    private static final double EPSILON = 0.000000001;
    
    /**
     * Adds a {@link TextTitle} to a grid and checks that the unconstrained
     * arrangement returns the correct size.
     */
    public void testTextTitleSize() {
        NestedGrid grid = new NestedGrid();
        TextTitle t = new TextTitle("X");
        t.setDefaultWidth(12.0);
        t.setDefaultHeight(34.0);
        t.setMargin(1.0, 2.0, 3.0, 4.0);
        t.setPadding(5.0, 6.0, 7.0, 8.0);
        grid.put(0, 0, t);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(12.0, r.getWidth(), EPSILON);
        assertEquals(34.0, r.getHeight(), EPSILON);        
    }
    
    /**
     * Adds a {@link LegendTitle} to a grid and checks that the unconstrained
     * arrangement returns the correct size.
     */
    public void testLegendTitleSize() {
        NestedGrid grid = new NestedGrid();
        LegendTitle t = new LegendTitle(new PiePlot());
        t.setDefaultWidth(12.0);
        t.setDefaultHeight(34.0);
        t.setMargin(1.0, 2.0, 3.0, 4.0);
        t.setPadding(5.0, 6.0, 7.0, 8.0);
        grid.put(0, 0, t);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(12.0, r.getWidth(), EPSILON);
        assertEquals(34.0, r.getHeight(), EPSILON);        
    }

    /**
     * Adds an {@link XYPlot} to a grid and checks that the unconstrained
     * arrangement returns the correct size.
     */
    public void testXYPlotSize() {
        NestedGrid grid = new NestedGrid();
        XYPlot plot = new XYPlot();
        plot.setMargin(RectangleInsets.ZERO_INSETS);
        plot.setDefaultWidth(12.0);
        plot.setDefaultHeight(34.0);
        grid.put(0, 0, plot);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(12.0, r.getWidth(), EPSILON);
        assertEquals(34.0, r.getHeight(), EPSILON);
    }
    
    public void testNumberAxisHeight() {
        NestedGrid grid = new NestedGrid();
        NumberAxis axis = new NumberAxis("Axis");
        axis.setDefaultHeight(123.4);
        axis.setFixedAxisEdge(RectangleEdge.LEFT);
        axis.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, axis);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getHeight(), EPSILON);
    }
    
    public void testNumberAxisWidth() {
        NestedGrid grid = new NestedGrid();
        NumberAxis axis = new NumberAxis("Axis");
        axis.setDefaultWidth(123.4);
        axis.setFixedAxisEdge(RectangleEdge.TOP);
        axis.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, axis);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
    }

    public void testDateAxisHeight() {
        NestedGrid grid = new NestedGrid();
        DateAxis axis = new DateAxis("Axis");
        axis.setDefaultHeight(123.4);
        axis.setFixedAxisEdge(RectangleEdge.LEFT);
        axis.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, axis);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getHeight(), EPSILON);
    }
    
    public void testDateAxisWidth() {
        NestedGrid grid = new NestedGrid();
        DateAxis axis = new DateAxis("Axis");
        axis.setDefaultWidth(123.4);
        axis.setFixedAxisEdge(RectangleEdge.BOTTOM);
        axis.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, axis);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
    }

    public void testPeriodAxisWidth() {
        NestedGrid grid = new NestedGrid();
        PeriodAxis axis = new PeriodAxis("Axis");
        axis.setDefaultWidth(123.4);
        axis.setFixedAxisEdge(RectangleEdge.BOTTOM);
        axis.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, axis);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(123.4, r.getWidth(), EPSILON);
    }

    /**
     * Add two empty blocks to a grid and arrange without constraints.  The
     * specified default sizes incorporate the margin settings.
     */
    public void testBlocks1() {
        NestedGrid grid = new NestedGrid();
        EmptyBlock b1 = new EmptyBlock(12.3, 45.6);
        b1.setMargin(1.0, 2.0, 3.0, 4.0);
        EmptyBlock b2 = new EmptyBlock(78.9, 10.1);
        b2.setMargin(1.0, 2.0, 3.0, 4.0);
        grid.put(0, 0, b1);
        grid.put(1, 0, b2);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(78.9, r.getWidth(), EPSILON);
        assertEquals(55.7, r.getHeight(), EPSILON);
    }
    
    public void testBlocks2() {
        NestedGrid grid = new NestedGrid();
        EmptyBlock b1 = new EmptyBlock(10.0, 20.0);
        EmptyBlock b2 = new EmptyBlock(30.0, 40.0);
        grid.put(0, 0, b1);
        grid.put(1, 0, b2);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        grid.arrange(g2, new RectangleConstraint(15.0, 30.0), 
                new ArrangeParams());
        assertEquals(15.0, b1.getBounds().getWidth(), EPSILON);
        assertEquals(10.0, b1.getBounds().getHeight(), EPSILON);
        assertEquals(15.0, b2.getBounds().getWidth(), EPSILON);
        assertEquals(20.0, b2.getBounds().getHeight(), EPSILON);
    }
        
    /**
     * Adds a {@link BlockContainer} to a grid and checks that the 
     * unconstrained arrangement returns the correct size (set as the default
     * width and height).
     */
    public void testBlockContainerSize() {
        NestedGrid grid = new NestedGrid();
        BlockContainer bc = new BlockContainer(new FlowArrangement());
        bc.setDefaultWidth(12.0);
        bc.setDefaultHeight(34.0);
        bc.setMargin(1.0, 2.0, 3.0, 4.0);
        bc.setPadding(5.0, 6.0, 7.0, 8.0);
        grid.put(0, 0, bc);
        BufferedImage image = new BufferedImage(100, 80, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ArrangeResult r = grid.arrange(g2, RectangleConstraint.NONE, 
                new ArrangeParams());
        assertEquals(12.0, r.getWidth(), EPSILON);
        assertEquals(34.0, r.getHeight(), EPSILON);        
    }
    
}
