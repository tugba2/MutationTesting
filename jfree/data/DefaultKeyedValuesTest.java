package org.jfree.data;
 
import org.jfree.util.SortOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultKeyedValuesTest extends DefaultKeyedValues {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double EPSILON = 0.000000001;
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
        
        
        
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
	/**
	 * 
	 */
    @Test 
    public void testGetValue(){
    	//int data = 5;
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.setValue((Comparable)0, 11);
    	data.setValue((Comparable)1, 22);
    	Number expected = 11.0;
    	Number actual = data.getValue(0);
    	assertEquals(expected, actual);
    	
    }
    @Test 
    public void testGetValueKeyNull(){
    	//int data = 5;
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.setValue((Comparable)0, null); 
    	Number actual = data.getValue(0);  	
    	assertNull(actual);
    	
    }
    
    @Test
    public void testGetLists(){
    	List expected = new ArrayList();
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.setValue((Comparable)0,1);
    	data.setValue((Comparable)1,2);
    	data.setValue((Comparable)2,3);
    	expected.add(0);
    	expected.add(1);
    	expected.add(2);
    	List actual = data.getKeys();
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testAddValue(){
    	Comparable key = 0;
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue(key, 11);
    	
    }
    
    @Test
    public void testRemoveValue(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 11);
    	data.removeValue((Comparable)0);
    }
    @Test
    public void testRemoveValueIfFalse(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 11);
    	data.removeValue((Comparable)1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSetValueKeyNull(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.setValue(null, 0);
    }
    
    @Test
    public void testSetValueKeyIndexBiggerZero(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 11);
    	data.setValue((Comparable)0, 22);
    	
    }
    
   
    @Test
    public void testSortByKeys(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)2, 11);  
    	data.addValue((Comparable)1, 11);  
    	data.addValue((Comparable)3, 11); 
    	data.sortByKeys(SortOrder.ASCENDING);
    	
    }
    
    @Test
    public void testSortByValues(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)2, 11);  
    	data.addValue((Comparable)1, 21);  
    	data.addValue((Comparable)3, 33); 
    	data.sortByValues(SortOrder.DESCENDING);
    	
    }
    
    @Test
    public void testEquals(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 5);
    	boolean result = data.equals(data);
    	assertTrue(result);
    }
    
    @Test
    public void testEqualsV1Null(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, null);
    	
    	DefaultKeyedValues data2 = new DefaultKeyedValues();
    	data2.addValue((Comparable)0, 5); 
    	boolean result = data.equals(data2);
    	assertFalse(result);
    }
    
    @Test
    public void testEqualsV1NullV2Null(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, null);
    	
    	DefaultKeyedValues data2 = new DefaultKeyedValues();
    	data2.addValue((Comparable)0, null); 
    	boolean result = data.equals(data2);
    	assertTrue(result);
    }
    
    @Test
    public void testEqualsCountisDifferentItemCount(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 1);
    	data.addValue((Comparable)1, 2);
    	DefaultKeyedValues data2 = new DefaultKeyedValues();
    	data2.addValue((Comparable)0, 3); 
    	boolean result = data.equals(data2);
    	assertFalse(result);
    }
    
    @Test
    public void testEqualsK1NotEqualK2(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 11); 
    	DefaultKeyedValues data2 = new DefaultKeyedValues();
    	data2.addValue((Comparable)3, 11); 
    	boolean result = data.equals(data2);
    	assertFalse(result);
    }
    
    @Test
    public void testEqualsV1NotEqualV2(){
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	data.addValue((Comparable)0, 11); 
    	DefaultKeyedValues data2 = new DefaultKeyedValues();
    	data2.addValue((Comparable)0, 22); 
    	boolean result = data.equals(data2);
    	assertFalse(result);
    }

    /*
    @Test 
    public void testGetKeyItemNull(){
    	//int data = 5;
    	DefaultKeyedValues data = new DefaultKeyedValues();
    	Comparable key = null;
    	data.setValue(key, 0); 
    	Comparable actual = data.getKey(0);  	
    	assertNull(actual);
    	
    }
    */
}
