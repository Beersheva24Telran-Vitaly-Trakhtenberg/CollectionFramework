package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T>
{
    private static class Node<T>
    {
        T obj;
        Node<T> next;
        Node<T> prev;
        Node(T obj)
        {
            this.obj = obj;
        }
    }
    private class LinkedListIterator implements Iterator<T>
    {
        private Node<T> current = head;
        private Node<T> last_returned = null;
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            last_returned = current;
            T data = current.obj;
            current = current.next;
            return data;
        }
        @Override
        public void remove() {
            if (last_returned == null) {
                throw new IllegalStateException("next() has not been called or remove() has already been called after the last call to next()");
            }

            Node<T> prev = last_returned.prev;
            Node<T> next = last_returned.next;

            if (prev != null) {
                prev.next = next;
            } else {
                head = next;
            }

            if (next != null) {
                next.prev = prev;
            } else {
                tail = prev;
            }

            last_returned.obj = null;
            last_returned.prev = null;
            last_returned.next = null;
            last_returned = null;
            size--;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    private void checkIndex(int index, boolean sizeInclusive)
    {
        int limit = sizeInclusive ? size : size - 1;
        if (index < 0 || index > limit) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private Node<T> getNode(int index)
    {
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    private Node<T> getNodeFromTail(int index)
    {
        Node<T> current = tail;
        for (int tmp_indexi = size - 1; tmp_indexi > index; tmp_indexi--) {
            current = current.prev;
        }
        return current;
    }

    private Node<T> getNodeFromHead(int index) {
        Node<T> current = head;
        for (int tmp_indexi = 0; tmp_indexi < index; tmp_indexi++) {
            current = current.next;
        }
        return current;
    }

    private void addNode(Node<T> node, int index)
    {
        if (index == 0) {
            addHead(node);
        } else if (index == size) {
            addTail(node);
        } else {
            addMiddle(node, index);
        }
        size++;
    }

    private void addMiddle(Node<T> nodeToInsert, int index)
    {
        Node<T> nodeBefore = getNode(index);
        Node<T> nodeAfter = nodeBefore.prev;
        nodeToInsert.next = nodeBefore;
        nodeToInsert.prev = nodeAfter;
        nodeBefore.prev = nodeToInsert;
        nodeAfter.next = nodeToInsert;
    }

    private void addTail(Node<T> node)
    {
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    private void addHead(Node<T> node)
    {
        if (head == null) {
            head = tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    private void removeNode(int index)
    {
        if (index == 0) {
            removeHead();
        } else if (index == size) {
            removeTail();
        } else {
            removeMiddle(index);
        }
        size--;
    }

    private void removeMiddle(int index)
    {
        Node<T> removedNode = getNode(index);
        Node<T> nodeBefore = removedNode.prev;
        Node<T> nodeAfter = removedNode.next;
        if (nodeBefore != null) {
            nodeBefore.next = nodeAfter;
        }
        if (nodeAfter != null) {
            nodeAfter.prev = nodeBefore;
        }
        clearGarrbage(removedNode);
    }

    private void removeTail()
    {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node <T> old_tail = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        clearGarrbage(old_tail);
    }

    private void removeHead()
    {
        Node <T> old_head = head;
        Node <T> node = getNode(0);
        if (head == null) {
            head = tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        clearGarrbage(old_head);
    }

    private void clearGarrbage(Node<T> obj)
    {
        obj.next = null;
        obj.prev = null;
        obj.obj = null;
    }

    @Override
    public void add(int index, T obj)
    {
        checkIndex(index, true);
        Node<T> node = new Node<>(obj);
        addNode(node, index);
    }

    @Override
    public T remove(int index)
    {
        checkIndex(index, true);

        Node<T> removed_node = getNode(index);
        T removed_data = removed_node.obj;
        removeNode(index);

        return removed_data;
    }

    @Override
    public T get(int index)
    {
        checkIndex(index, false);
        return getNode(index).obj;
    }

    @Override
    public int indexOf(T pattern)
    {
        int index = 0;
        while(index < size && !Objects.equals(getNode(index).obj, pattern)) {
            index++;
        }
        return index == size ? -1 : index;
    }

    @Override
    public int lastIndexOf(T pattern)
    {
        int index = size - 1;
        while(index >= 0 && !Objects.equals(getNode(index).obj, pattern)) {
            index--;
        }
        return index;
    }

    @Override
    public boolean add(T obj)
    {
        Node<T> node = new Node<>(obj);
        addNode(node, size);
        return true;
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
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
}
