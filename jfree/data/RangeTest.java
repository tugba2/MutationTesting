package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

import junit.framework.TestCase;

public class RangeTest extends Range {

	public RangeTest() {
		super(-1, 1);
	}

	/**
	 * 
	 */
	public Range exampleRange;

	public static final long serialVersionUID = 1L;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		exampleRange = new Range(-1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	/** Range **/
	/* test case 1 */
	@Test(expected = IllegalArgumentException.class)
	public void testRangeLowerGreaterThanUpper() {
		Range rangeTry = new Range(10.0, 1.0);
	}

	/** constrain **/
	/* test case 1 */
	@Test
	public void testConstrainForInRange() {
		Range rangeForConstrain = new Range(1.6, 3.6);
		double tempDouble = 2.5;
		double expected = 2.5;
		assertEquals(expected, rangeForConstrain.constrain(tempDouble),.000000001d);
	}

	/* test case 2 */
	@Test
	public void testConstrainForValueGreaterThanUpper() {
		Range rangeForConstrain = new Range(1.6, 3.6);
		double tempDouble = 5.5;
		double expected = 3.6;
		assertEquals(expected, rangeForConstrain.constrain(tempDouble),.000000001d);
	}

	/* test case 3 */
	@Test
	public void testConstrainForValueLessThanLower() {
		Range rangeForConstrain = new Range(1.6, 3.6);
		double tempDouble = 0.5;
		rangeForConstrain.constrain(tempDouble);
	}

	/** GetLength **/
	/* test case 1 */
	@Test
	public void testGetLengthMethod() {
		Range rangeTry = new Range(1.0, 10.0);
		double tempDouble = rangeTry.getLength();
		assertEquals(9.0, tempDouble, .000000001d);
	}

	/** expand **/
	/* test case 1 */
	@Test
	public void testExpandWithNotNullRange() {
		Range rangeTry = new Range(2.0, 6.0);
		assertNotNull(Range.expand(rangeTry, 0.25, 0.5));
	}

	/* test case 2 */
	@Test(expected = IllegalArgumentException.class)
	public void testExpandWithNullRange() {
		Range rangeTry = null;
		Range.expand(rangeTry, 0.25, 0.5);
	}

	/** shift **/
	/* test case 1 */
	@Test
	public void testShiftLowerBoundOfValueIsGreaterThanZero() {
		Range rangeTry = new Range(2.0, 6.0);
		Range returnRange = new Range(2.000000001,6.000000001);
		assertEquals(returnRange, Range.shift(rangeTry, .000000001d));
	}

	/* test case 2 */
	@Test
	public void testShiftValueLowerBoundIsLessThanZero() {
		Range rangeTry = new Range(-1.0, 6.0);
		Range returnRange = new Range(-0.999999999,6.000000001);
		assertEquals(returnRange, Range.shift(rangeTry, .000000001d));
	}

	/* test case 3 */
	@Test
	public void testShiftValueLowerBoundIsEqualToZero() {
		Range rangeTry = new Range(0.0, 6.0);
		Range returnRange = new Range(0.000000001,6.000000001);
		assertEquals(returnRange, Range.shift(rangeTry, .000000001d));
	}

	/** toString **/
	/* test case 1 */
	@Test
	public void testToString() {
		Range rangeTry = new Range(2.0, 6.0);
		assertEquals("Range[2.0,6.0]", rangeTry.toString());
	}

	/** hash code **/
	/* test case 1 */
	@Test
	public void testHashCode() {
		Range rangeTry = new Range(2.0, 6.0);
		int tempInt = -2145910784;
		assertEquals(tempInt,rangeTry.hashCode());
	}

	/** contains **/
	/* test case 1 */
	@Test
	public void testContainsMethod() {
		Range rangeTry = new Range(1.0, 10.0);
		assertTrue(rangeTry.contains(3));
	}

	/** expandToInclued **/
	/* test case 1 */
	@Test
	public void testExpandToIncludeInRangeNull() {
		Range rangeTemp = null;
		double value = 3.0;
		Range returnRange = expandToInclude(rangeTemp, value);
		Range expectedRange = new Range(3.0,3.0);
		assertEquals(expectedRange, returnRange);
	}

	/* test case 2 */
	@Test
	public void testExpandToIncludeInRangeLowerISGreaterThanValue() {
		Range rangeTemp = new Range(1.0, 3.0);
		double value = 0.5;
		Range returnRange = expandToInclude(rangeTemp, value);
		Range expectedRange = new Range(0.5,3.0);
		assertEquals(expectedRange, returnRange);
	}

	/* test case 3 */
	@Test
	public void testExpandToIncludeInRangeUpperBoundIsLessThanValue() {
		Range rangeTemp = new Range(1.0, 3.0);
		double value = 5.0;
		Range returnRange = expandToInclude(rangeTemp, value);
		Range expectedRange = new Range(1.0,5.0);
		assertEquals(expectedRange, returnRange);
	}

	/* test case 4 */
	@Test
	public void testExpandToIncludeInRangeUpperBoundIsEqualToValue() {
		Range rangeTemp = new Range(1.0, 3.0);
		double value = 3.0;
		Range returnRange = expandToInclude(rangeTemp, value);
		Range expectedRange = new Range(1.0,3.0);
		assertEquals(expectedRange, returnRange);

	}

	/** combine **/
	/* test case 1 */
	@Test
	public void testCombineNULLandNULL() {
		Range range1 = null;
		Range range2 = null;
		assertNull(Range.combine(range1, range2));
	}

	/* test case 2 */
	@Test
	public void testCombineNULLandNotNULL() {
		Range range1 = null;
		Range range2 = new Range(1, 3);
		Range actual = Range.combine(range1, range2);
		assertEquals(range2, actual);
	}

	/* test case 3 */
	@Test
	public void testCombineNotNULLandNULL() {
		Range range2 = null;
		Range range1 = new Range(1, 3);
		Range actual = Range.combine(range1, range2);
		assertEquals(range1, actual);
	}

	/* test case 4 */
	@Test
	public void testCombineNotNULLandNotNULL() {
		Range range2 = new Range(1, 8);
		Range range1 = new Range(1, 3);
		Range actual = Range.combine(range1, range2);
		assertEquals(range2, actual);
	}

	/* test case 3 */
	// @Test
	// public void testCombineNotNULLandNULL(){
	// Range range2 = null;
	// Range range1 = new Range(1,3);
	// Range actual = Range.combine(range1, range2);
	// assertEquals(range1, actual);
	// }
	//
	/** getLowerBound **/
	/* test case 1 */
	@Test
	public void testGetLowerBoundWithARange() {
		Range range2 = new Range(-1.9, 3.8);
		double lowerBound = range2.getLowerBound();
		assertEquals(-1.9, lowerBound, .000000001d);
	}

	// /* test case 2 */
	// @Test
	// public void testGetLowerBoundWithADotOnTheAxis(){
	// Range range = new Range(4.6,4.6);
	// double upperBound = range.getUpperBound();
	// assertEquals(4.6, upperBound, .000000001d);
	// }

	/** getUpperBound **/
	/* test case 1 */
	@Test
	public void testGetUpperBoundWithARange() {
		Range range = new Range(1.0, 6.0);
		double upperBound = range.getUpperBound();
		assertEquals(6.0, upperBound, .000000001d);
	}

	// /* test case 2 */
	// @Test
	// public void testGetUpperBoundWithADotOnTheAxis(){
	// Range range = new Range(4.0,4.0);
	// double upperBound = range.getUpperBound();
	// assertEquals(4.0, upperBound, .000000001d);
	// }

	/** intersects **/
	/* test case 1 */
	@Test
	public void testIntersectsFirstArgumentGreaterThanSecond() {
		Range range = new Range(2.0, 6.0);
		assertFalse(range.intersects(4, 1));
	}

	/* test case 2 */
	@Test
	public void testIntersectsLowerOfFirstRangeGreaterOrEqualsSecondRangeLower() {
		Range range = new Range(2.0, 6.0);
		assertTrue(range.intersects(0.0, 5.0));
	}

	/* test case 3 */
	@Test
	public void testIntersectsFirstArgumentSmallerThanSecond() {
		Range range = new Range(2.0, 6.0);
		assertTrue(range.intersects(3, 4));
	}

	/* test case 4 */
	@Test
	public void testIntersectsFirstArgumentEqualsSecond() {
		Range range = new Range(2.0, 6.0);
		assertTrue(range.intersects(5.0, 5.0));
	}

	/** getCentralValue **/
	/* test case 1 */
	@Test
	public void testGetCentralValueWithARange() {
		Range range = new Range(-3.6, -1.6);
		double centralValue = range.getCentralValue();
		assertEquals(-2.6, centralValue, .000000001d);
	}

//	/* test case 2 */
//	@Test
//	public void testGetCentralValueWithADotOnTheAxis() {
//		Range range = new Range(-7.9, -7.9);
//		double centralValue = range.getCentralValue();
//		assertEquals(-7.9, centralValue, .000000001d);
//	}
	
	
	
	
	
	/*new tests*/
	@Test
	public void testRangeLowerUpper(){
		try{
			Range rangeTry = new Range(10.0, 1.0);
			rangeTry.getLength();
		}
		catch(Exception e){
			String actual = e.getMessage();
			String expected = "Range(double, double): require lower (10.0) <= upper (1.0).";
			assertEquals(expected, actual);
		}
		
	}
	
	
	/* test case 1 */
	@Test
	public void testExpandWithException() {
		try{
		Range rangeTry = null;
		Range.expand(rangeTry, 0.25, 0.5);
		}catch(Exception e){
			String actual = e.getMessage();
			String expected = "Null \'range\' argument.";
			assertEquals(expected, actual);
			
		}
	}/*üstü 82*/
	/* test case 1 */
	@Test
	public void testShiftJumbl() {
		Range rangeTry = new Range(2.0, 6.0);
		Range returnRange = new Range(2.000000001,6.000000001);
		assertEquals(returnRange, Range.shift(rangeTry, .000000001d,true));
	}/*üstü 84*/
	 	
	@Test
	public void testShiftWithNoZeroCrossing() {
		boolean allowZeroCrossing=false;
		Range rangeTry = new Range(0.2, 0.3);
		Range returnRange = new Range(0.200000001,0.300000001);
		assertEquals(returnRange, Range.shift(rangeTry, .000000001d,allowZeroCrossing));
	}/*üstü 85*/
	
//	@Test
//	public void testShiftWithNoZeroCrossing2() {
//		Range rangeTry = new Range(0.2, 0.3);
//		Range returnRange = new Range(0.200000001,0.300000001);
//		assertEquals(returnRange, Range.shift(rangeTry, .000000001d,true));
//	}
//	
//	 
	@Test
	public void testEqualsNotRangeObj() {
		String data = "aaa";
		Range rangeTry = new Range(2, 6);
		assertFalse(rangeTry.equals(data));
	}/*üstü 86*/

	@Test
	public void testEqualsNotObjLowerNotEqualRangeLower() {
		Range rangeTry = new Range(2, 6);
		Range rangeTry2 = new Range(3, 6);
		assertFalse(rangeTry.equals(rangeTry2));
	}/*üstü 87*/
	@Test
	public void testEqualsNotObjLowerNotEqualRangeUpper() {
		Range rangeTry = new Range(2, 6);
		Range rangeTry2 = new Range(2, 5);
		assertFalse(rangeTry.equals(rangeTry2));
	}
	
}