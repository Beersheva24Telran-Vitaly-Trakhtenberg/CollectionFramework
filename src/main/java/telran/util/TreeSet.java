package telran.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TreeSet<T> implements Set<T>
{
    private Node<T> root;
    private final Comparator<T> comparator;
    private int size = 0;

    public TreeSet(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    public TreeSet() {
        this((Comparator<T>)Comparator.naturalOrder());
    }

    @Override
    public T get(Object pattern)
    {
        Node<T> tmp_node = this.getParentOrNode((T) pattern);
        return tmp_node != null && comparator.compare(tmp_node.obj, (T) pattern) == 0 ? tmp_node.obj : null;
    }

    @Override
    public boolean removeIf(Predicate<T> predicate) {
        return Set.super.removeIf(predicate);
    }

    @Override
    public void clear() {
        Set.super.clear();
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;

        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if(root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;

        }
        return res;
    }

    @Override
    public boolean remove(T pattern)
    {
        boolean res = false;

        Node<T> removed_node = this.getParentOrNode(pattern);
        if (removed_node != null) {
            Node<T> tmp_left = removed_node.left;
            Node<T> tmp_right = removed_node.right;
            if (tmp_left == null && tmp_right == null) {
                replaceNodeInParent(removed_node, null);
                res = true;
            } else if (tmp_left != null && tmp_right != null) {
                Node<T> node_successor = findMin(removed_node.right);
                removed_node.obj = node_successor.obj;
                replaceNodeInParent(node_successor, node_successor.right);
                res = true;
            } else {
                replaceNodeInParent(removed_node, removed_node.left == null ? removed_node.right : removed_node.left);
                res = true;
            }
            size--;
        }

        return res;
    }

    private void replaceNodeInParent(Node<T> removed_node, Node<T> replaced_node)
    {
        if (removed_node.parent == null) {
            root = replaced_node;
        } else if (removed_node == removed_node.parent.left) {
            removed_node.parent.left = replaced_node;
        } else {
            removed_node.parent.right = replaced_node;
        }
        if (replaced_node != null) {
            replaced_node.parent = removed_node.parent;
        }
    }

    private Node<T> findMin(Node<T> node)
    {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(T pattern)
    {
        boolean res = false;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext() && !res) {
            res = Objects.compare(iterator.next(), pattern, comparator) == 0;
        }
        return res;
//        return this.getParentOrNode(pattern) != null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        Set.super.forEach(action);
    }

    private static class Node<T>
    {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        Node(T obj)
        {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T>
    {
        private final Stack<Node<T>> stack = new Stack<>();
        private Node<T> current;
        private Node<T> last_returned;

        public TreeSetIterator() {
            current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        @Override
        public T next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = stack.pop();
            last_returned = current;
            T result = current.obj;
            current = current.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            return result;
        }

        @Override
        public void remove() {
            if (last_returned == null) {
                throw new IllegalStateException();
            }
            TreeSet.this.remove(last_returned.obj);
            last_returned = null;
        }
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if (parent == null) {
            addRoot(node);
        } else {
            if (comparator.compare(node.obj, parent.obj) > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
            node.parent = parent;
        }
    }

    private void addRoot(Node<T> node) {
        root = node;
    }

    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        int compRes;
        while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            current = compRes > 0 ? current.right : current.left;
        }
        return current;
    }

    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        if(res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res = compRes == 0 ? res : null;
        }

        return res;

    }

    private Node<T> getParent(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes;
        while (current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : null;
    }
}
