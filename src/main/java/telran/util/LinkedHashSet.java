package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T>
{
    private final LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();

    @Override
    public boolean add(T obj)
    {
        boolean res = false;
        if (!contains(obj)) {
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);
        }

        return res;
    }

    @Override
    public boolean remove(T pattern)
    {
        int removed_node_index = list.indexOf(pattern);
        boolean res = false;
        if (removed_node_index > -1) {
            list.removeNode(removed_node_index);
            map.remove(pattern);
            res = true;
        }
        return res;
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public boolean contains(T pattern)
    {
        return map.get(pattern) != null;
    }

    @Override
    public T get(Object pattern)
    {
        LinkedList.Node<T> node = map.get(pattern);
        return (node != null) ? node.obj : null;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedHashSetIterator();
    }

    private class LinkedHashSetIterator implements Iterator<T>
    {
        private final Iterator<T> list_iterator = list.iterator();
        private T current_iterator = null;

        @Override
        public boolean hasNext()
        {
            return list_iterator.hasNext();
        }

        @Override
        public T next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current_iterator = list_iterator.next();
            return current_iterator;
        }

        @Override
        public void remove()
        {
            if (current_iterator == null) {
                throw new IllegalStateException("next() has not been called or remove() has already been called after the last call to next()");
            }
            LinkedHashSet.this.remove(current_iterator);
            current_iterator = null;
        }
    }
}