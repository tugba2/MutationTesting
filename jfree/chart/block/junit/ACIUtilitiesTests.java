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
 * ----------------------
 * ACIUtilitiesTests.java
 * ----------------------
 * (C) Copyright 2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: ACIUtilitiesTests.java,v 1.1 2005/11/16 11:03:58 mungady Exp $
 *
 * Changes
 * -------
 * 11-Nov-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.block.junit;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jfree.chart.block.ACIUtilities;

/**
 * Tests for the {@link ACIUtilities} class.
 */
public class ACIUtilitiesTests extends TestCase {
    
    /**
     * Returns the tests as a test suite.
     *
     * @return The test suite.
     */
    public static Test disabledSuite() {
        return new TestSuite(ACIUtilitiesTests.class);
    }

    /**
     * Constructs a new set of tests.
     *
     * @param name  the name of the tests.
     */
    public ACIUtilitiesTests(String name) {
        super(name);
    }
    
    /**
     * Confirm that the equals() method can distinguish all the required fields.
     */
    public void testEquals() {
        AttributedString s1 = new AttributedString("");
        AttributedString s2 = new AttributedString("");
        assertTrue(ACIUtilities.equal(s1.getIterator(), s2.getIterator()));
        assertFalse(ACIUtilities.equal(s1.getIterator(), null));
        assertFalse(ACIUtilities.equal(null, s2.getIterator()));
        assertTrue(ACIUtilities.equal(null, null));
        
        s1 = new AttributedString("X");
        s2 = new AttributedString("X");
        assertTrue(ACIUtilities.equal(s1.getIterator(), s2.getIterator()));
        
        s1 = new AttributedString("Y");
        assertFalse(ACIUtilities.equal(s1.getIterator(), s2.getIterator()));
        s2 = new AttributedString("Y");
        assertTrue(ACIUtilities.equal(s1.getIterator(), s2.getIterator())); 
        
        s1.addAttribute(TextAttribute.FONT, new Font("Dialog", Font.PLAIN, 12));
        assertFalse(ACIUtilities.equal(s1.getIterator(), s2.getIterator()));
        s2.addAttribute(TextAttribute.FONT, new Font("Dialog", Font.PLAIN, 12));
        assertTrue(ACIUtilities.equal(s1.getIterator(), s2.getIterator())); 
        
        s1.addAttribute(TextAttribute.FONT, new Font("Dialog", Font.BOLD, 12));
        assertFalse(ACIUtilities.equal(s1.getIterator(), s2.getIterator()));
        s2.addAttribute(TextAttribute.FONT, new Font("Dialog", Font.BOLD, 12));
        assertTrue(ACIUtilities.equal(s1.getIterator(), s2.getIterator())); 
    }
   
}
