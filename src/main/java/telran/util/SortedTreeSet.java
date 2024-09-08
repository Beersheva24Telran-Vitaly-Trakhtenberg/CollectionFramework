package telran.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class SortedTreeSet<T> implements SortedSet<T> {
    private static class Node<T>
    {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> current = getLeastFrom(root);
        Node<T> prev;
        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        @Override
        public T next()
        {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = current;
            current = getNextCurrent(current);
            return prev.obj;
        }

        @Override
        public void remove()
        {
            if(prev == null)
            {
                throw new IllegalStateException();
            }
            removeNode(prev);
            prev = null;
        }

    }

    private Node<T> root;
    private Comparator<T> comparator;
    int size;
    public SortedTreeSet(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }
    public SortedTreeSet()
    {
        this((Comparator<T>)Comparator.naturalOrder());
    }

    @Override
    public boolean add(T obj)
    {
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

    private void addAfterParent(Node<T> node)
    {
        Node<T> parent = getParent(node.obj);
        if(comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    private void addRoot(Node<T> node)
    {
        root = node;
    }

    @Override
    public boolean remove(T pattern)
    {
        boolean res = false;
        Node<T> node = getNode(pattern);
        if (node != null) {
            removeNode(node);
            res = true;
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
        return getNode(pattern) != null;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern)
    {
        Node<T> node = getNode((T)pattern);

        return node == null ? null : node.obj;
    }

    private Node<T> getParentOrNode(T pattern)
    {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }

    private Node<T> getNode(T pattern)
    {
        Node<T> res = getParentOrNode(pattern);
        if (res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res =  compRes == 0 ? res : null;
        }
        return res;
    }

    private Node<T> getParent(T pattern)
    {
        Node<T> res = getParentOrNode(pattern);
        int compRes = comparator.compare(pattern, res.obj);
        return compRes == 0 ? null : res;

    }

    private Node<T> getLeastFrom(Node<T> node)
    {
        if (node != null) {
            while(node.left != null) {
                node = node.left;
            }
        }
        return node;
    }

    private Node<T> getGreatestFrom(Node<T> node)
    {
        if (node != null) {

            while(node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    private Node<T> getGreaterParent(Node<T> node)
    {
        Node<T> parent = node.parent;
        while(parent != null && parent.right == node) {
            node = node.parent;
            parent = node.parent;
        }
        return parent;
    }

    private Node<T> getNextCurrent(Node<T> current)
    {
        //Algorithm see on the board
        return current.right != null ? getLeastFrom(current.right) :
                getGreaterParent(current);
    }

    private void removeNode(Node<T> node)
    {
        if(node.left != null && node.right != null) {
            removeJunction(node);
        } else {
            removeNonJunction(node);
        }

        size--;
    }

    private void removeJunction(Node<T> node)
    {
        Node<T> substitute = getGreatestFrom(node.left);
        node.obj = substitute.obj;
        removeNonJunction(substitute);
    }

    private void removeNonJunction(Node<T> node)
    {
        Node<T> parent = node.parent;
        Node<T> child = node.left != null ? node.left : node.right;
        if(parent == null) {
            root = child; //actual root removing
        } else if(node == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        if(child != null) {
            child.parent = parent;
        }
        setNulls(node);
    }

    private void setNulls(Node<T> node)
    {
        node.obj = null;
        node.parent = node.left = node.right = null;

    }

    @Override
    public T first()
    {
        Node<T> current = root;
        if (current == null) {
            throw new NoSuchElementException("SortedTree is empty");
        }
        while (current.left != null) {
            current = current.left;
        }
        return  current.obj;
    }

    @Override
    public T last()
    {
        Node<T> current = root;
        if (current == null) {
            throw new NoSuchElementException("SortedTree is empty");
        }
        while (current.right != null) {
            current = current.right;
        }
        return  current.obj;
    }

    @Override
    public T floor(T key)
    {
        Node<T> current = root;
        T res = null;

        while(current != null) {
            int cmp = comparator.compare(current.obj, key);
            if (cmp == 0) {
                res = current.obj;
                break;
            } else if (cmp > 0) {
                current = current.left;
            } else {
                res = current.obj;
                current = current.right;
            }
        }

        return res;
    }

    @Override
    public T ceiling(T key)
    {
        Node<T> current = root;
        T res = null;

        while(current != null) {
            int cmp = comparator.compare(current.obj, key);
            if (cmp == 0) {
                res = current.obj;
                break;
            } else if (cmp > 0) {
                res = current.obj;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return res;
    }

    @Override
    public SortedSet<T> subSet(T keyFrom, T keyTo)
    {
        if (comparator.compare(keyFrom, keyTo) >= 0) {
            throw new IllegalArgumentException("keyFrom must be less than keyTo");
        }
        SortedTreeSet<T> res_subset = new SortedTreeSet<T>(this.comparator);

        Node<T> current = getNode(keyFrom);
        if (current == null) {
            current = getNextCurrent(getParentOrNode(keyFrom));
        }

        while (current != null && comparator.compare(current.obj, keyTo) < 0) {
            res_subset.add(current.obj);
            current = getNextCurrent(current);
        }

        return res_subset;
    }
}