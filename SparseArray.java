import java.util.Iterator;
import java.util.NoSuchElementException;

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

   private Node<T> first;
   private int length;
   private T default;

   /** 
    * Create a new SparseArray.
    *
    * @param l The length of the array.
    * @param d The default, unmodified value for the elements in the array.
   */
   public SparseArray(int l, T d) throws LengthException {
      
      if (n <= 0) {
         throw new LengthException();
      }

      length = l;
      defualt = d;

   }

   /**
    * Find the node with the specified index, or, if that node does
    * not exist return the node before the where we would expect the
    * index to be. If no nodes are in the list, null is returned.
    *
    * @param index The index to find in the list.
    * @return The node with the specified index, or, if that node does
    * not exist return the node before the where we would expect the
    * index to be. If no nodes are in the list, null is returned.
    */
   private Node<T> findNodeOrPrevious(int index) throws IndexException {

      if (index < 0 || index >= this.length()) {
         throw new IndexException();
      }

      Node<T> currentNode = first;
      Node<T> previousNode;

      while (true) {

         if (currentNode == null || index < currentNode.index) {
            // Reached the end of the list for the node we are
            // looking for is in between previous and next.
            // If first was null, we're just going to return null.
            return previousNode;

         }

         else if (index == currentNode.index) {
            // The current node is the node we were looking for
            // so we're going to the current node.
            return currentNode;

         }

         else {

            // We need to keep looking for the node or for the
            // correct place to place the node. 
            previousNode = currentNode;
            currentNode = previousNode.next;

         }

      }

   }

   @Override
   public T get(int i) throws IndexException {

      Node<T> foundNode = findNodeOrPrevious(i);

      if (foundNode == null || foundNode.index != i) {
         return this.defualt;
      }

      else {
         return foundNode.data;
      }

   }

   @Override
   public void put(int i, T t) throws IndexException {

      Node<T> foundNode = findNodeOrPrevious(i);

      if (foundNode == null) {
         // The first node is null
         Node<T> newNode = new Node<>(value, index, null);
         this.first = newNode;
      }

      else if (foundNode.index != i) {
         // We found the node before the node we we're looking for.
         // We need to place a new node in the linked list.
         Node<T> newNode = new Node<>(value, index, foundNode.next);
         foundNode.next = newNode;
      }

      else {
         // The node we were looking for exists.
         // We jut have to change the node's data.
         foundNode.data = value;
      }

   }

   @Override
   public int length() {
      return this.length;
   }





}