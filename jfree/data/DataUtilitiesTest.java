package org.jfree.data;

import static org.junit.Assert.*;

 

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jfree.data.DefaultKeyedValues2D;
 
 
import org.junit.*;

public class DataUtilitiesTest extends DataUtilities {
    
    private static final double EPSILON = 0.000000001;
    
    public DataUtilitiesTest(){
        
    }
    
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
    
    
    
    /**** 	CalculateColumnTotal  ****/
    /*Test Case 1*/
    @Test(expected=NullPointerException.class)
    public void testCalculateColumnTotalForDataNullAndColLowerZero() {
        // setup
        final Values2D valuesCalculateColumnTotal = null;
        // exercise
        assertEquals(0, DataUtilities.calculateColumnTotal(valuesCalculateColumnTotal, -2), EPSILON);
        
        // tear-down: NONE in this test method
    }
    
    /*Test Case 2*/
    @Test
    public void testCalculateColumnTotalCoverForAndIf() {
        /*
         *  data:
         *  [1 2]
         *  [1 2]
         *  column: 1
         */
       
    	DefaultKeyedValues2D data = new DefaultKeyedValues2D();
    	data.addValue(1, 0, 0);
    	data.addValue(2, 0, 1);
    	data.addValue(1, 1, 0);
    	data.addValue(2, 1, 1);
    	
    	
        // exercise
         double result = DataUtilities.calculateColumnTotal(data, 1); // verify
         assertEquals(4, result, EPSILON);
        
        // tear-down: NONE in this test method
    }
    
    /*Test Case 2*/
    @Test
    public void testCalculateColumnTotalCoverForAndFalseIf() {
        /*
         *  data:
         *  [1 2]
         *  [1 2]
         *  column: 1
         */
       
    	DefaultKeyedValues2D data = new DefaultKeyedValues2D();
    	data.addValue(null, 0, 0);
    	data.addValue(null, 0, 1);
    	data.addValue(null, 1, 0);
    	data.addValue(null, 1, 1);
    
        // exercise
         double result = DataUtilities.calculateColumnTotal((Values2D)data, 1); // verify
         assertEquals(0, result, EPSILON);
        
        // tear-down: NONE in this test method
    }
    
    /**** 	CreateNumberArray  ****/
    /*Test Case 1*/
    @Test
    public void testCreateNumberArrayValid(){
        double[] data = {1.4, 2.2, 1.0};
        Number[] actual = DataUtilities.createNumberArray(data);
        Number[] expected = {1.4, 2.2, 1.0};
        assertArrayEquals(expected, actual);
    }
    
    /*Test Case 2*/
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArrayInvalid(){
        double[] data = null;
        Number[] expected = null;
        Number[] actual = DataUtilities.createNumberArray(data);
        
        assertArrayEquals(expected, actual);
    }
    
    /**** 	CreateNumberArray2D  ****/
    /*Test Case 1*/
    @Test
    public void testCreateNumberArray2DValid(){
        double[][] data = {{1.2, 1.2}};
        Number[][] actual = DataUtilities.createNumberArray2D(data);
        Number[][] expected = {{1.2, 1.2}};
        assertArrayEquals(expected, actual);
    }
    
    /*Test Case 2*/
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DInvalid(){
        double[][] input = null;
        Number[][] expected = null;
        Number[][] actual = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expected, actual);
    }
    
//    /**** 	getCumulativePercentages  ****/
//    /*Test Case 1*/
//    @Test
//    public void testGetCumulativePercentagesValid(){
//        /*
//         * data :
//         *  KEY	VALUE
//         *   0	  5
//         *   1	  9
//         */
//        //for actual data
//        DefaultKeyedValues data = new DefaultKeyedValues();
//        data.addValue((Comparable)0, 5.0);
//        data.addValue((Comparable)1, 9.0);
//        DefaultKeyedValues expected = new DefaultKeyedValues();
//        expected.addValue((Comparable)0, 0.32142857142857145);
//        expected.addValue((Comparable)1, 0.9);      
//        // exercise
//        KeyedValues actual = DataUtilities.getCumulativePercentages(data);
//        assertEquals(expected, actual);	
//    }
      
    /** TODO:: It is written as IllegalParamException on Specs!!!!!Diff from blackbox testing **/
    /*Test Case 2*/
    @Test(expected= IllegalArgumentException.class)  
    public void testGetCumulativePercentagesInvalid(){
        KeyedValues expected = null; 
        KeyedValues actual = DataUtilities.getCumulativePercentages(null);
        assertEquals(expected, actual);
    }
    
//    /*Test Case 3*/
//    @Test
//    public void testGetCumulativePercentagesCoverFalseIF(){
//        /*
//         * data :
//         *  KEY	VALUE
//         *   0	  5
//         *   1	  9
//         */
//        //for actual data
//        DefaultKeyedValues data = new DefaultKeyedValues();
//        data.addValue((Comparable)0, null);
//        data.addValue((Comparable)1, 1);
//        DefaultKeyedValues expected = new DefaultKeyedValues();
//       expected.addValue((Comparable)0, 0.0);
//        expected.addValue((Comparable)1, 0.9);      
//      
//        // exercise
//        KeyedValues actual = DataUtilities.getCumulativePercentages(data);
//        assertEquals(expected, actual);	
//    }

    
    /**** 	CalculateRowTotal  ****/
    /*Test Case 1*/
    @Test
    public void testCalculateRowTotalForCoverForAndIF()
    {
        // setup
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, 0, 0);
    	data.addValue(2, 0, 1);
    	data.addValue(1, 1, 0);
    	data.addValue(2, 1, 1);
    	Double expected = 3.0;
    	Double actual = DataUtilities.calculateRowTotal(data, 1);
        // exercise
        assertEquals(expected, actual, EPSILON);
  
        // tear-down: NONE in this test method
    }
    
    /*Test Case 2*/
    @Test
    public void testCalculateRowTotalForCoverFalseIF()
    {
        // setup
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1, 0, 0);
    	data.addValue(2, 0, 1);
    	data.addValue(null, 1, 0);
    	data.addValue(2, 1, 1);
    	Double expected = 2.0;
    	Double actual = DataUtilities.calculateRowTotal(data, 1);
        // exercise
        assertEquals(expected, actual, EPSILON);
  
        // tear-down: NONE in this test method
    }
}