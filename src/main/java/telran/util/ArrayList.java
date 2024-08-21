package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayList<T> implements List<T>
{
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size;

    public ArrayList(int capacity)
    {
        array = new Object[capacity];
    }
    public ArrayList()
    {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(int index, T obj)
    {
        if (size == array.length) {
            reallocate();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    @Override
    public boolean add(T obj)
    {
        if (size == array.length) {
            reallocate();
        }
        array[size++] = obj;
        return true;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        return 0;
    }

    @Override
    public int lastIndexOf(T pattern) {
        return 0;
    }

    private void reallocate()
    {
        array = Arrays.copyOf(array, array.length * 2);
    }

    private void reallocate(int additional_items)
    {
        array = Arrays.copyOf(array, array.length + additional_items);
    }

    @Override
    public boolean remove(T pattern) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(T pattern) {
        return false;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }


    public T[] toArray() {
        return (T[]) Arrays.copyOf(array, size, array.getClass());
    }
}
