package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.Iterator;

//{3, -10, 20, 1, 10, 8, 100 , 17}
public class SortedSetTest extends SetTest {
    SortedSet<Integer> sortedSet;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        sortedSet = (SortedSet<Integer>) collection;

    }

    @Test
    void floorTest() {
        assertEquals(10, sortedSet.floor(10));
        assertNull(sortedSet.floor(-11));
        assertEquals(10, sortedSet.floor(11));
        assertEquals(100, sortedSet.floor(101));
    }

    @Test
    void ceilingTest() {
        assertEquals(10, sortedSet.ceiling(10));
        assertNull(sortedSet.ceiling(101));
        assertEquals(17, sortedSet.ceiling(11));
        assertEquals(-10, sortedSet.ceiling(-11));
    }

    @Test
    void firstTest() {
        assertEquals(-10, sortedSet.first());
    }

    @Test
    void lastTest() {
        assertEquals(100, sortedSet.last());
    }

    @Test
    void subSetTest() {
        Integer[] expected = { 10, 17 };
        Integer[] actual = sortedSet.subSet(10, 20).stream().toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
    }
/*
private SortedTreeSet<Integer> treeSet;

    @BeforeEach
    void setUp() {
        treeSet = new SortedTreeSet<>();
    }

    @Test
    void testAdd() {
        assertTrue(treeSet.add(5));
        assertTrue(treeSet.add(3));
        assertTrue(treeSet.add(7));
        assertFalse(treeSet.add(5)); // Duplicate
        assertEquals(3, treeSet.size());
    }

    @Test
    void testRemove() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertTrue(treeSet.remove(5));
        assertFalse(treeSet.remove(5)); // Already removed
        assertEquals(2, treeSet.size());
    }

    @Test
    void testContains() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertTrue(treeSet.contains(5));
        assertFalse(treeSet.contains(4));
    }

    @Test
    void testFirst() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertEquals(3, treeSet.first());
    }

    @Test
    void testLast() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertEquals(7, treeSet.last());
    }

    @Test
    void testFloor() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertEquals(5, treeSet.floor(6));
        assertEquals(7, treeSet.floor(7));
        assertNull(treeSet.floor(2));
    }

    @Test
    void testCeiling() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        assertEquals(5, treeSet.ceiling(4));
        assertEquals(3, treeSet.ceiling(3));
        assertNull(treeSet.ceiling(8));
    }

    @Test
    void testSubSet() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        treeSet.add(1);
        treeSet.add(9);

        SortedSet<Integer> subset = treeSet.subSet(3, 8);
        assertTrue(subset.contains(3));
        assertTrue(subset.contains(5));
        assertTrue(subset.contains(7));
        assertFalse(subset.contains(1));
        assertFalse(subset.contains(9));
    }

    @Test
    void testEmpty() {
        assertTrue(treeSet.isEmpty());
        treeSet.add(5);
        assertFalse(treeSet.isEmpty());
    }

    @Test
    void testClear() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);
        treeSet.clear();
        assertTrue(treeSet.isEmpty());
    }

    @Test
    void testIterator() {
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(7);

        Iterator<Integer> it = treeSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertEquals(5, it.next());
        assertEquals(7, it.next());
        assertFalse(it.hasNext());
    }
*/
}

