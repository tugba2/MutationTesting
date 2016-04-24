package org.jfree.data.junit;

import org.jfree.data.*;
import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase 
{
	public void setUp()
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values = testValues;
		
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}
	
	public void tearDown()
	{
		values = null;
	}
	
	public void testNullDataColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 0), 0.0000001d);
	}
	
	public void testValidDataAndColumnColumnTotal()
	{
		assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
	}
	
	public void testValidDataInvalidColumnColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 1), 0.0000001d);
	}
	
	private Values2D values;
}
