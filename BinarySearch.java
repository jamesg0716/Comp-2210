import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException("Parameters cannot be null");
      }
   
      if (a.length <= 0) {
         return -1;
      }
   
      int left = 0;
      int right = a.length - 1;
      while (left <= right) {
         int middle = left + (right - left) / 2;
      
         if (comparator.compare(key, a[middle]) > 0) {
            left = middle + 1;
         } 
         
         else if (comparator.compare(key, a[middle]) < 0) {
            right = middle - 1;
         }
         
         else {
         
            if (middle == 0) {
               return middle;
            } 
            
            else if (comparator.compare(key, a[middle - 1]) > 0) {
               return middle;
            }
            
            else {
               right = middle - 1;
            }
         }
      }
      
      
      return -1;
   }

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
   
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException("Parameters cannot be null");
      }
   
      if (a.length <= 0) {
         return -1;
      }
   
      int left = 0;
      int right = a.length - 1;
      while (left <= right) {
         int middle = left + (right - left) / 2;
         
         if (comparator.compare(key, a[middle]) > 0) {
            left = middle + 1;
         }
         
         else if (comparator.compare(key, a[middle]) < 0) {
            right = middle - 1;
         }
         
         else {
         
            if (middle == a.length - 1) {
               return middle;
            }
            
            else if (comparator.compare(key, a[middle + 1]) < 0) {
               return middle;
            }
            
            else {
               left = middle + 1;
            }
         }
      
      }
      return -1;
   }
}
