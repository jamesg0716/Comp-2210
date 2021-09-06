import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   James Glass (jpg0044@auburn.edu)
* @version  08/31/21
*
*/
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
   private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int minValue = a[0];
      for (int i = 1; i < a.length; i++) {
         if(a[i] <= minValue) {
            minValue = a[i];
         }
      }
      return minValue;
   }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int maxValue = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] >= maxValue) {
            maxValue = a[i];
         }
      }
      return maxValue;
   }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException("Array is empty or there is no kth minimum value");
      }
      
      int[] arr = Arrays.copyOf(a, a.length);
      Arrays.sort(arr);
      int distVal = 1;
      int i = 0;
      
      while (distVal < k && i < arr.length - 1) {
         if (arr[i] != arr[++i]) {
            distVal++;
         }
      }
      
      if (distVal != k) {
         throw new IllegalArgumentException("No kth minimum");
      }
      
      return arr[i];
   }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException("Array is empty or there is no kth maximum value");
      }
      
      int[] arr = Arrays.copyOf(a, a.length);
      Arrays.sort(arr);
      int i = arr.length - 1;
      int distVal = 1;
      
      while (distVal < k && i > 0) {
         if (arr[i] != arr[--i]) {
            distVal++;
         }
      }
   
      if (distVal != k) {
         throw new IllegalArgumentException("No kth maximum");
      }
      return arr[i];
   }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array cannot be empty");
      }
      
      int arr = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            arr++;
         }
      }
      int[] result = new int[arr];
      int ran = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            result[ran++] = a[i];
         }
      }
      return result;
   }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array cannot be empty");
      }
      
      int ceil = Integer.MAX_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key && a[i] < ceil) {
            ceil = a[i];
         }
      }
      
      if (ceil == Integer.MAX_VALUE) {
         throw new IllegalArgumentException("No qualifying value");
      }
   
      return ceil;
   }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array cannot be empty");
      }
   
      int flr = Integer.MIN_VALUE;
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key && a[i] > flr) {
            flr = a[i];
         }
      }
   
      if (flr == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("No qualifying value");
      }
   
   
      return flr;
   }

}
