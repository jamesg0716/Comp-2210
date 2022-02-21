import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;
   private int length;
	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException("Terms should not be null");
      }
      this.length = terms.length;
      this.terms = new Term[length]; 
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      if (prefix == null) {
         throw new NullPointerException("Prefix should not be null");
      }
      
      int TermC = BinarySearch.firstIndexOf(terms, new Term(prefix, 0),
         Term.byPrefixOrder(prefix.length()));
         
      int TermD = BinarySearch.lastIndexOf(terms, new Term(prefix, 0), 
         Term.byPrefixOrder(prefix.length()));
         
      int matches = prefixMatch(prefix);
      Term[] t1 = new Term[matches];
      
      if (TermC != -1 && TermD != -1) {
         for (int i = 0; i < matches; i++) {
            t1[i] = terms[TermC + i];
         }
      }  
      
      Arrays.sort(t1, Term.byDescendingWeightOrder());
                  
      return t1;
   }
   
   public int prefixMatch(String prefix) {
      Term findTerm = new Term(prefix, 0);
      int TermC = BinarySearch.firstIndexOf(terms, findTerm, Term.byPrefixOrder(prefix.length()));
      
      int TermD = BinarySearch.lastIndexOf(terms, findTerm, Term.byPrefixOrder(prefix.length()));
      return TermD - TermC + 1;
   }

}

