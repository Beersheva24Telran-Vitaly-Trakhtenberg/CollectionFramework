package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hash_table;
    float factor;
    int size;

    private class HashSetIterator implements Iterator<T> {
        //Hint:
        private Iterator<T> current_iterator = null;
        //private Iterator<T> prevIterator;
        private int index_iterator = 0;

        @Override
        public boolean hasNext() {
            while ((current_iterator == null || !current_iterator.hasNext()) && index_iterator < hash_table.length) {
                if (hash_table[index_iterator] != null) {
                    current_iterator = hash_table[index_iterator].iterator();
                }
                index_iterator++;
            }
            return current_iterator != null && current_iterator.hasNext();
        }

        @Override
        public T next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return current_iterator.next();
        }

        @Override
        public void remove()
        {
            if (current_iterator == null) {
                throw new IllegalStateException("next() has not been called or remove() has already been called after the last call to next()");
            }
            current_iterator.remove();
            size--;
        }
    }

    public HashSet(int hashTableLength, float factor) {
        hash_table = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hash_table.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hash_table);
            size++;
        }
        return res;

    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
            table[index] = list; //???
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hash_code = obj.hashCode();
        return Math.abs(hash_code % length);
    }

    private void hashTableReallocation() {
        List<T> []temp_table = new List[hash_table.length * 2];
        for(List<T> list: hash_table) {
            if(list != null) {
                list.forEach(obj -> addObjInHashTable(obj, temp_table));
                list.clear(); //??? for testing if it doesn't work remove this statement
            }
        }
        hash_table = temp_table;

    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        int index = getIndex((T)pattern, hash_table.length);
        List<T> list = hash_table[index];

        if (list != null) {
            int list_index = list.indexOf((T)pattern);
            if (list_index != -1) {
                list.remove(list_index);
                res = true;
                size--;
                if (list.isEmpty()) {
                    hash_table[index] = null;
                }
            }
        }

        return res;
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
        int index = getIndex(pattern, hash_table.length);
        List<T> list = hash_table[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern)
    {
        T t_pattern = (T) pattern;
        int index = getIndex(t_pattern, hash_table.length);
        List<T> list = hash_table[index];

        T res = null;
        if (list != null) {
            int list_index = list.indexOf(t_pattern);
            if (list_index != -1) {
                res = list.get(list_index);
            }
        }

        return res;
    }

}
