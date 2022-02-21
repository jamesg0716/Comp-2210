import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author James Glass (jpg0044@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
    
   public boolean add(T element) {
   
      if (element == null) {
         throw new NullPointerException("Element should not be null.");
      }
      
      if(this.contains(element)) {
         return false;
      }
      
      Node aNode = new Node(element);
     
      if(isEmpty()) {
         front = aNode;
         rear = aNode;
         ++size;
         return true;
      }
      Node next = front;
      Node prev = null;
      
      while(next != null) {
      
         if(aNode.element.compareTo(next.element) < 0) { 
            break;
         }
         prev = next;
         next = next.next;
      }
       
      if(prev == null) {
         front = aNode;   
      }
      else {
         prev.next = aNode;
      }
      
      if(next == null) {
         rear = aNode;   
      }
      else {
         next.prev = aNode;
      }
      aNode.next = next;
      aNode.prev = prev;
           
      size++;
      return true;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
   
      Iterator<T> itr1 = new myIterator<T>();
      T next = null;
      
      while(itr1.hasNext()) {
         next = itr1.next();
         
         if(element.equals(next)) {
            itr1.remove();
            size--;
            return true;
         }
      }
      return false;
   }
   
   public class myIterator<T> implements Iterator<T> {
     
      Node next;
      Node prev = null;
      
      public myIterator() {
         next = front;
      }
      public boolean hasNext(){
         return next != null;
      }
      
      public T next() {
      
         T nextT = null;
         
         if (next != null) {
            nextT = (T)next.element;
            prev = next;
            next = next.next;  
         }
         return nextT; 
      }
      
      public void remove() {
         if(prev == null) {
            return;
         }
            
         if(prev.prev == null) {
            front = prev.next;
         }
         else {
            prev.prev.next = prev.next; 
         }
         if(prev.next == null) {
            rear = prev.prev;
         }
         else {
            prev.next.prev = prev.prev; 
         }
         if(next == null) {
            prev = null;
         }
         else {
            prev = next.prev;
         }
      }  
   }   
   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if (isEmpty() || front == null) {
         return false;
      }
   
      Node bNode = front;
   
      while (bNode != null) {
         if (bNode.element.equals(element)) {
            return true;
         }
         bNode = bNode.next;
      }
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      
      if(size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      return false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
   
      if(size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      return false;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
   
      LinkedSet<T> jSet = new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode != null) {
      
         jSet.add(aNode.element);
         aNode = aNode.next;
      }
   
      Iterator<T> itr2 = s.iterator();
   
      while(itr2.hasNext()) {
         jSet.add(itr2.next());
      }
   
      return jSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
   
      LinkedSet<T> iSet = new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode != null) {
      
         iSet.add(aNode.element);
         aNode = aNode.next;
      }
   
      Iterator<T> itr1 = s.iterator();
   
      while(itr1.hasNext()) {
         iSet.add(itr1.next());
      }
   
      return iSet;
   }



   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
   
      LinkedSet<T> gSet = new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode  != null) {
         
         if(s.contains((T)aNode.element)) {
            gSet.add((T)aNode.element);
         }
      
         aNode = aNode.next;
      }
   
      return gSet;      }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
   
      LinkedSet<T> hSet=new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode != null) {  
       
         if(s.contains((T)aNode.element)) {
            hSet.add((T)aNode.element);
         }
      
         aNode= aNode.next;
      }
   
      return hSet;      }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
      
      LinkedSet<T> cSet = new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode != null) {   
         if(!s.contains((T)aNode.element)) {
            cSet.add((T)aNode.element);
         }
      
         aNode = aNode.next;
      }
   
      return cSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      if(s == null) {
         throw new NullPointerException("s should not be null.");
      }
   
      LinkedSet<T> cSet = new LinkedSet<T>();
   
      Node aNode = front;
   
      while(aNode != null) {   
         if(!s.contains((T)aNode.element)) {
            cSet.add((T)aNode.element);
         }
      
         aNode = aNode.next;
      }
   
      return cSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }

   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
   
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext())
            throw new NoSuchElementException();
      
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }   

   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new DescendingtIterator(rear);
   }

   private class DescendingtIterator implements Iterator<T> {
      Node aNode;
      
      public DescendingtIterator(Node rear) {
         aNode = rear;
      }
   
      public boolean hasNext() {
         return aNode != null && aNode.element != null;
      }
   
      public T next() {
         if(aNode != null) {            
            T itr = aNode.element;
            aNode = aNode.prev;
            return itr;
         }
         return null;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }   

   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      LinkedList<Set<T>> kSet = new LinkedList<Set<T>>();
   
      for(int i = 0; i < Math.pow(2, size); i++) {
      
         Set<T> ele = new LinkedSet<T>();
         Node aNode = front;
      
         for(int j = 0; j < size; j++) {
         
            if(((i >> j) & 1) == 1) {
               ele.add((T)aNode.element);
            }
            aNode = aNode.next;
         }
         kSet.add(ele);
      }
      return kSet.iterator();
   }

   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
