import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author James Glass (jpg0044@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   List<String> EMPTY_LADDER = new ArrayList<>();
   
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   private TreeSet<String> lexicon;

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
      
         lexicon = new TreeSet<String>();
         
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
    /////
   public int getWordCount() {
      return lexicon.size();
   }
   
   /////
   public boolean isWord(String str) {
      str = str.toLowerCase();
      return lexicon.contains(str);
   }
   
   /////
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      int dist = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            dist++;
         }
      }
      return dist;
   }
   
   /////
   public List<String> getNeighbors(String word) {
      List<String> neigh = new ArrayList<String>();
      if (!isWord(word)) {
         return neigh;
      }
      Iterator<String> itr1 = lexicon.iterator();
      String myWord;
      while (itr1.hasNext()) {
         myWord =itr1.next();
         if (getHammingDistance(word, myWord) == 1) {
            neigh.add(myWord);
         }
      }
      return neigh;
   }
   
   /////
   public boolean isWordLadder(List<String> sequence) {
      if (sequence == null || sequence.isEmpty()) {
         return false;
      }
      if (!isWord(sequence.get(0).toLowerCase())) {
         return false;
      }
      for (int i = 1; i < sequence.size(); i++) {
         String string1 = sequence.get(i).toLowerCase();
         String string2 = sequence.get(i - 1).toLowerCase();
         if (!isWord(string1)) {
            return false;
         }
         if (getHammingDistance(string1, string2) != 1) {
            return false;
         }
      }
      return true;
   }
   
   /////
   public List<String> getMinLadder(String start, String end) {
      start = start.toLowerCase();
      end = end.toLowerCase();
      ArrayList<String> list2 = new ArrayList<String>();
      List<String> lad = new ArrayList<String>();
      
      if (start.equals(end)) {
         lad.add(start);
         return lad;
      }
      
      if (getHammingDistance(start, end) == -1) {
         return EMPTY_LADDER;
      }
      
      if (isWord(start) && isWord(end)) {
         list2 = customList(start, end);
      }
      
      if (list2.isEmpty()) {
         return EMPTY_LADDER;
      } 
      
      for (int i = list2.size() -1; i >= 0; i--) {
         lad.add(list2.get(i));
      }
      return lad;
   }
   
   
   /////
   private ArrayList<String> customList(String start, String end) {
      Deque<Node> n1 = new ArrayDeque<Node>();
      HashSet<String> hash = new HashSet<String>();
      ArrayList<String> list2 = new ArrayList<String>();
      hash.add(start);
      n1.addLast(new Node(start, null));
      Node finish = new Node(end, null);
      outerloop:
      while (!n1.isEmpty()) {
         Node currentNode =  n1.removeFirst();
         String word = currentNode.word;
         List<String> neigh = getNeighbors(word);
         for (String beside : neigh) {
            if (!hash.contains(beside)) {
               hash.add(beside);
               n1.addLast(new Node(beside, currentNode));
               if (beside.equals(end)) {
                  finish.child = currentNode;
                  break outerloop;
               }
            }
         }
      }
      
      if (finish.child == null) {
         return list2;
      }
      Node n2 = finish;
      while (n2 != null) {
         list2.add(n2.word);
         n2 = n2.child;
      }
      
      return list2;
   }  
   
   private class Node {
      String word;
      Node child;
   
      public Node(String str3, Node n) {
         word = str3;
         child = n;
      }
   }
    
}

