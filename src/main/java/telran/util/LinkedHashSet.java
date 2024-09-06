package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements SortedSet<T>
{
    private LinkedList<T> list = new LinkedList<>();
    private HashMap<T, Node<T>> map = new HashMap<>();

    @Override
    public T first() {
        return null;
    }

    @Override
    public T last() {
        return null;
    }

    @Override
    public T floor(T key) {
        return null;
    }

    @Override
    public T ceiling(T key) {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T keyFrom, T keyTo) {
        return null;
    }

    @Override
    public T get(Object pattern) {
        return null;
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);
            res = true;
        }

        return res;
    }

    @Override
    public boolean remove(T pattern) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method LinkedHashSet.remove() not implemented yet");
    }

    @Override
    public int size() {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method LinkedHashSet.size() not implemented yet");
    }

    @Override
    public boolean isEmpty() {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method LinkedHashSet.isEmpty() not implemented yet");
    }

    @Override
    public boolean contains(T pattern) {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method LinkedHashSet.contains() not implemented yet");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method LinkedHashSet.iterator() not implemented yet");
    }

}
