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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removed_value = (T) array[index];
        int num_moved = size - index - 1;

        if (num_moved > 0) {
            System.arraycopy(array, index + 1, array, index, num_moved);
        }
        array[--size] = null; // clear to let GC do its work
        return removed_value;
    }

    @Override
    public boolean remove(T pattern) {
        return false;
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
        int res = -1;
        for (int i = 0; i < size; i++) {
            if (pattern.equals(array[i])) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int res = -1;
        for (int i = size-1; i >= 0; i--) {
            if (pattern.equals(array[i])) {
                res = i;
                break;
            }
        }
        return res;
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
