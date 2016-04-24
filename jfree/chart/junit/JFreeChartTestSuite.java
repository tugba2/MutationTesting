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
 * ------------------------
 * JFreeChartTestSuite.java
 * ------------------------
 * (C) Copyright 2002-2005, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: JFreeChartTestSuite.java,v 1.5 2005/06/17 09:21:20 mungady Exp $
 *
 * Changes:
 * --------
 * 11-Jun-2002 : Version 1 (DG);
 * 30-Sep-2002 : Added tests for com.jrefinery.data (DG);
 * 17-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 13-Mar-2003 : Added tests for new com.jrefinery.data.time package (DG);
 * 17-Feb-2004 : Added tests for org.jfree.chart.title package (DG);
 * 20-May-2004 : Added tests for org.jfree.chart.entity package (DG);
 * 30-Jul-2004 : Added tests for org.jfree.data.gantt package (DG);
 * 23-Aug-2004 : Restructured org.jfree.data (DG);
 * 18-Jan-2005 : Added main() method (DG);
 * 08-Jun-2005 : Added tests for org.jfree.chart.needle package (DG);
 *
 */

package org.jfree.chart.junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.annotations.junit.AnnotationsPackageTests;
import org.jfree.chart.axis.junit.AxisPackageTests;
import org.jfree.chart.block.junit.BlockPackageTests;
import org.jfree.chart.entity.junit.EntityPackageTests;
import org.jfree.chart.labels.junit.LabelsPackageTests;
import org.jfree.chart.needle.junit.NeedlePackageTests;
import org.jfree.chart.plot.junit.PlotPackageTests;
import org.jfree.chart.renderer.category.junit.RendererCategoryPackageTests;
import org.jfree.chart.renderer.junit.RendererPackageTests;
import org.jfree.chart.renderer.xy.junit.RendererXYPackageTests;
import org.jfree.chart.title.junit.TitlePackageTests;
import org.jfree.chart.ui.junit.ChartUIPackageTests;
import org.jfree.chart.urls.junit.UrlsPackageTests;
import org.jfree.data.category.junit.DataCategoryPackageTests;
import org.jfree.data.gantt.junit.DataGanttPackageTests;
import org.jfree.data.junit.DataPackageTests;
import org.jfree.data.statistics.junit.DataStatisticsPackageTests;
import org.jfree.data.time.junit.DataTimePackageTests;
import org.jfree.data.xy.junit.DataXYPackageTests;

/**
 * A test suite for the JFreeChart class library that can be run using
 * JUnit (<code>http://www.junit.org<code>).
 */
public class JFreeChartTestSuite extends TestCase {

    /**
     * Returns a test suite to the JUnit test runner.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        TestSuite suite = new TestSuite("JFreeChart");
        suite.addTest(ChartPackageTests.disabledSuite());
        suite.addTest(AnnotationsPackageTests.disabledSuite());
        suite.addTest(AxisPackageTests.disabledSuite());
        suite.addTest(BlockPackageTests.disabledsuite());
        suite.addTest(EntityPackageTests.disabledSuite());
        suite.addTest(LabelsPackageTests.disabledSuite());
        suite.addTest(NeedlePackageTests.disabledSuite());
        suite.addTest(PlotPackageTests.disabledSuite());
        suite.addTest(RendererPackageTests.disabledSuite());
        suite.addTest(RendererCategoryPackageTests.disabledSuite());
        suite.addTest(RendererXYPackageTests.disabledSuite());
        suite.addTest(TitlePackageTests.disabledSuite());
        suite.addTest(ChartUIPackageTests.disabledSuite());
        suite.addTest(UrlsPackageTests.disabledSuite());
        suite.addTest(DataPackageTests.disabledSuite());
        suite.addTest(DataCategoryPackageTests.disabledSuite());
        suite.addTest(DataStatisticsPackageTests.disabledSuite());
        suite.addTest(DataTimePackageTests.disabledSuite());
        suite.addTest(DataXYPackageTests.disabledSuite());
        suite.addTest(DataGanttPackageTests.disabledSuite());
        return suite;
    }
    
    /**
     * Runs the test suite using JUnit's text-based runner.
     * 
     * @param args  ignored.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(disabledSuite());
    }

}