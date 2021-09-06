import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   /**
   * Normal min test.
   */
   @Test 
   public void minTest1() {
      int[] a = {5, 9, 3, 11, 7};
      int expected = 3;
      int actual = Selector.min(a);
      Assert.assertEquals(expected, actual);
   }
/**
* Test for min with repeated numbers in array.
*/
   @Test
   public void minTest2() {
      int[] a = {5, 9, 3, 11, 3, 7};
      int expected = 3;
      int actual = Selector.min(a);
      Assert.assertEquals(expected, actual);
   }
/**
* Tests for min with array of null.
*/
   @Test (expected = IllegalArgumentException.class)
   public void minTest3() {
      int[] a = null;
      Selector.min(a);
   }
   /**
   * Test for min with array length of zero.
   */
   @Test (expected = IllegalArgumentException.class)
   public void minTest4() {
      int[] a = new int[0];
      Selector.min(a);
   }
   
   /**
   * Normal max int test.
   */
   @Test
   public void maxTest1() {
      int[] a = {5, 9, 3, 11, 7};
      Assert.assertEquals(11, Selector.max(a));
   }
   
   @Test
   public void maxTest2() {
      int[] a = {1, 3, 3, 5, 7, 9, 9};
      Assert.assertEquals(9, Selector.max(a));
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxTest3() {
      int[] a = null;
      Selector.min(a);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxTest4() {
      int[] a = new int[0];
      Selector.max(a);
   }
   
   @Test
   public void kminTest1() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 1;
      Assert.assertEquals(2, Selector.kmin(a, k));
   }
   
   @Test 
   public void kminTest2() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int k = 3;
      Assert.assertEquals(4, Selector.kmin(a, k));
   }
   
   @Test 
   public void kmaxTest1() {
      int[] a = {8, 2, 8, 7, 3, 3, 4};
      int k = 3;
      Assert.assertEquals(4, Selector.kmax(a, k));
   }
   
   @Test
   public void rangeTest1() {
      int[] a = {2, 8, 7, 3, 4};
      int low = 1;
      int high = 5;
      int[] expected = {2, 3, 4}; 
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }
   
   @Test
   public void ceilingTest1() {
   int[] a = {8, 2, 8, 7, 3, 3, 4};
   int key = 5;
   Assert.assertEquals(7, Selector.ceiling(a, key));
   }

  @Test 
  public void floorTest1() {
  int[] a = {8, 2, 8, 7, 3, 3, 4};
  int key = 5;
  Assert.assertEquals(4, Selector.floor(a, key));
  }
}
