import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of SparseArray for Project 1.
 *
 * Sparse array stores only information about
 * elements that have been modified in a linked
 * list. This data type is better than a SimpleArray
 * in situations where a large number of elements
 * in the array will remain mostly unchanged throughout
 * the arrays lifetime. This array does not make very much
 * sense to use for a small number of elements or
 * for a array of elements in which many elements are
 * going to be changed.
 *
 * @param <T> Element tyle.
*/
public class SparseArray<T> implements Array<T> {

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
    private T defaultValue;

    /**
     * Create a new SparseArray.
     *
     * @param l The length of the array.
     * @param d The default, unmodified value for the elements in the array.
     *
     * @throws LengthException Throws an exception if the length is negative.
    */
    public SparseArray(int l, T d) throws LengthException {

        if (l <= 0) {
            throw new LengthException();
        }

        this.length = l;
        this.defaultValue = d;

    }

    /**
     * Find the node with the specified index, or, if that node does
     * not exist return the node before the where we would expect the
     * index to be. If no nodes are in the list, null is returned.
     *
     * @param index The index to find in the list.
     * @return The node with the specified index, or, if that node does
     *     not exist return the node before the where we would expect the
     *     index to be. If no nodes are in the list, null is returned.
     * @throws IndexException Throws an exception if the index is out of bounds.
     *
    */
    private Node<T> findNodeOrPrevious(int index) throws IndexException {

        if (index < 0 || index >= this.length()) {
            throw new IndexException();
        }

        Node<T> currentNode = this.first;
        Node<T> previousNode = null;

        while (true) {

            if (currentNode == null || index < currentNode.index) {
                // Reached the end of the list for the node we are
                // looking for is in between previous and next.
                // If first was null, we're just going to return null.
                return previousNode;
            } else if (index == currentNode.index) {
                // The current node is the node we were looking for
                // so we're going to the current node.
                return currentNode;
            } else {
                // We need to keep looking for the node or for the
                // correct place to place the node.
                previousNode = currentNode;
                currentNode = previousNode.next;
            }

        }

    }

    @Override
    public T get(int i) throws IndexException {

        Node<T> foundNode = this.findNodeOrPrevious(i);

        if (foundNode == null || foundNode.index != i) {
            return this.defaultValue;
        } else {
            return foundNode.data;
        }

    }

    @Override
    public void put(int i, T t) throws IndexException {

        Node<T> foundNode = this.findNodeOrPrevious(i);

        if (foundNode == null) {
            // The first node is null
            Node<T> newNode = new Node<>(t, i, null);
            this.first = newNode;
        } else if (foundNode.index != i) {
            // We found the node before the node we we're looking for.
            // We need to place a new node in the linked list.
            Node<T> newNode = new Node<>(t, i, foundNode.next);
            foundNode.next = newNode;
        } else {
            // The node we were looking for exists.
            // We jut have to change the node's data.
            foundNode.data = t;
        }

    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    // An iterator to traverse the array.
    private final class ArrayIterator implements Iterator<T> {

        // Current position in the linked list.
        Node<T> currentNode;
        // Current index in the array.
        int currentIndex;

        ArrayIterator() {
            this.currentNode = SparseArray.this.first;
            this.currentIndex = 0;
        }

        @Override
        public T next() throws NoSuchElementException {

            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            T toReturn;

            if (this.currentNode == null) {
                // We are at the end of the linked list, we can just return
                // default values until the end.
                toReturn = SparseArray.this.defaultValue;
            } else if (this.currentNode.index == this.currentIndex) {
                // We are at the position in the linked list where the index is.
                // Return the node's data, and move on to the next node.
                toReturn = this.currentNode.data;
                this.currentNode = this.currentNode.next;
            } else {
                // The node we are at in the linked list does not have the index
                // we are looking for. Return a default value.
                toReturn = SparseArray.this.defaultValue;
            }

            // Increment the current index
            this.currentIndex++;
            return toReturn;

        }

        @Override
        public boolean hasNext() {
            return this.currentIndex < SparseArray.this.length();
        }

    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("[");

        Node<T> currentNode = this.first;

        int index = 0;

        for (T item: this) {

            s.append(item);

            if (index < this.length - 1) {
                s.append(", ");
            }

            index++;

        }

        s.append("]");

        return s.toString();

    }

}