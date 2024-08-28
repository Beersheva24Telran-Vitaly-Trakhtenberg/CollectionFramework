package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
@SuppressWarnings("unchecked")

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
    public T remove(int index)
    {
        checkIndex(index, false);
        T res = (T)array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return res;
    }

    private void checkIndex(int index, boolean sizeInclusive) {
        int limit = sizeInclusive ? size : size - 1;
        if (index < 0 || index > limit) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public boolean remove(T pattern)
    {
        int index = indexOf(pattern);
        if (index > -1) {
            remove(index);
        }
        return index > -1;
    }

    @Override
    public T get(int index)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern)
    {
        int index = 0;
        while(index < size && !Objects.equals(array[index], pattern)) {
            index++;
        }
        return index == size ? -1 : index;
    }

    @Override
    public int lastIndexOf(T pattern)
    {
        int index = size - 1;
        while(index >= 0 && !Objects.equals(array[index], pattern)) {
            index--;
        }
        return index;
    }

    @Override
    public boolean removeIf(Predicate<T> predicate)
    {
        int write_index = -1;
        Predicate<T> negated_predicate = predicate.negate();
        for (int read_index = 0; read_index < size; read_index++) {
            if (negated_predicate.test((T) array[read_index])) {
                array[++write_index] = array[read_index];
            }
        }
        for (int i = write_index; i < size; i++) {
            array[i] = null;
        }
        size = write_index;

        boolean res = write_index < size;
        return res;
    }

    public static double getTime(Runnable testMethod) {
        for (int i = 0; i < 20; i ++) {
            testMethod.run();
        }
        int count = 10;

        while(true) {
            long begin =  System.nanoTime();

            for (int i = 0; i < count; i ++)
                testMethod.run();

            long end = System.nanoTime();

            if ((end - begin) < 1000000000) {
                count *= 100000;
                continue;
            }

            return (double)(end - begin) / count;
        }
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
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(T pattern)
    {
        return indexOf(pattern) > -1;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator()
    {
        return new ArrayListIterator();
    }

    public T[] toArray()
    {
        return (T[]) Arrays.copyOf(array, size, array.getClass());
    }

/*
CW & HW 22
 */
    private class ArrayListIterator implements Iterator<T> {
        int currentIndex = 0;
        private boolean flNext = false;
        @Override
        public boolean hasNext() {
            flNext = true;
            return currentIndex < size;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[currentIndex++];
        }

        @Override
        public void remove() {
            if(!flNext) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(--currentIndex);
            flNext = false;
        }
    }
}
