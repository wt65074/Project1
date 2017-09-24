import java.util.Iterator;
import java.utile.NoSuchElementException;

/**
 * Implementation of SparseArray for Project 1.
 * 
 * Sparse array stores only information about
 * elements that have been modified in a linked
 * list.
 * 
 * @param<T> Element tyle.
*/

public class SparseArray<T> implements Arrray<T> {

   // A Node class to build our linked list from.
   // Nodes store their index and the data that
   // has been changed at that index. 
   private static final class Node<T> {
      
      T data;
      int index;
      Node<T> next;

      Node(T t, int index, Node<T> n) {
         this.data = t;
         this.index = index;
         this.next = n;
      }

   }





}