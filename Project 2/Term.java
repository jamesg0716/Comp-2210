import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 * @author James Glass - COMP 2210
 * @version 9/28/21
 */
public class Term implements Comparable<Term> {

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
   private String query;
   private long weight;
    
   public Term(String query, long weight) {
      if (query == null) {
         throw new NullPointerException("Query should not be null");
      }
      if (weight < 0) {
         throw new IllegalArgumentException("Weight cannot be negative");
      }
      this.query = query;
      this.weight = weight; 
   }

    /**
     * Compares the two terms in descending order of weight.
     */
   public static Comparator<Term> byDescendingWeightOrder() {
      return new byDescendingWeightOrder2();
   }
   
   public static class byDescendingWeightOrder2 implements Comparator<Term> {
      @Override 
      public int compare(Term termA, Term termB) {
         if (termA.weight < termB.weight) {
            return -1;
         }
         
         else if(termA.weight > termB.weight) {
            return 1;
         }
         
         else 
            return 0;
      }
   }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
   public static Comparator<Term> byPrefixOrder(int length) {
      if (length <= 0) {
         throw new IllegalArgumentException("Length should be greater than zero");
      } 
      return new byPrefixOrder1(length);
   }
   
   public static class byPrefixOrder1 implements Comparator<Term> {
      private int length;
   
      public byPrefixOrder1(int length) {
         this.length = length;
      }
   
      @Override
      public int compare(Term termA, Term termB) {
         int a1 = 0;
         int b1 = 0;
      
         if (termA.query.length() < length) {
            a1 = termA.query.length();
         }
         else {
            a1 = length;
         }
         
         if (termB.query.length() < length) {
         b1 = termB.query.length();
         }
         
         else {
         b1 = length;
         }
         
         return termA.query.substring(0, a1).compareTo(termB.query.substring(0, b1));
      }
   }
   
    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
   @Override
      public int compareTo(Term other) {
      return this.query.compareTo(other.query);
   }
   
    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
   @Override
      public String toString(){
      return this.query + "\t" + this.weight;
   }
   
}


