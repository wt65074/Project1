import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array implementation using a basic Java array.
 *
 * The obvious implementation of the Array interface, absolutely
 * positively nothing fancy going on here.
 *
 * There are two reasons for this class to exist: First it's an
 * example for the style of code we're about to write a lot of.
 * Second it's actually useful because generics don't play well
 * with basic arrays in Java; we'll use SimpleArray in lots of
 * places where basic arrays would give us generic grief.
 *
 * @param <T> Element type.
 */
public class SimpleArray<T> implements Array<T> {
    // The obvious representation of our abstract Array.
    private T[] data;

    /**
     * Create a new SimpleArray.
     *
     * @param n Length of array, must be &gt; 0.
     * @param t Initial value to store in each slot.
     * @throws LengthException if n &le; 0.
     */
    public SimpleArray(int n, T t) throws LengthException {
        if (n <= 0) {
            throw new LengthException();
        }

        // This cast works around Java's problems with generic arrays.
        // The resulting warning is acceptable because there simply is
        // no better way of doing this.
        this.data = (T[]) new Object[n];

        // Array slots are null by default, no need to initialize twice.
        if (t != null) {
            // Slightly faster than running our own loop.
            Arrays.fill(this.data, t);
        }
    }

    // If we let ArrayIndexOutOfBoundsException propagate, we leak an
    // implementation detail we should probably hide. (Also that name
    // is so horrible, it deserves to live in a dark cave in Mordor.)

    @Override
    public T get(int i) throws IndexException {
        try {
            return this.data[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    @Override
    public void put(int i, T t) throws IndexException {
        try {
            this.data[i] = t;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexException();
        }
    }

    @Override
    public int length() {
        return this.data.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    // An iterator to traverse the array from front to back.
    // (So we can use Array with for-each loops just like we
    // could basic Java arrays.) Using an inner class (not a
    // nested class) allows us to access the outer object's
    // "data" member without any stunts.
    private final class ArrayIterator implements Iterator<T> {
        // Current position in the basic Java array.
        int current;

        @Override
        public T next() throws NoSuchElementException {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T t = SimpleArray.this.data[this.current];
            this.current += 1;
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.current < SimpleArray.this.data.length;
        }
    }

    @Override
    public String toString() {
        int n = this.data.length;
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < n; i++) {
            s.append(this.data[i]);
            if (i < n - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
