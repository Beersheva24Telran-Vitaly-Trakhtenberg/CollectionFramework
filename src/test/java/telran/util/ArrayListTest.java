package telran.util;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListTest extends ListTest
{
    ArrayList<String> test;

    @BeforeEach
    @Override
    void setUp()
    {
        String[] src = new String[] {"one", "two", "three"};

        collection = new ArrayList<>(3);
        super.setUp();
        test = new ArrayList<>(10);
        for (String s : src) {
            test.add(s);
        }
    }

    @Test
    void addLastTest()
    {

    }

    @Test
    void addsTest()
    {
        String[] src = new String[] {"one", "two", "three"};
        String[] expected = new String[] {"one", "two", "OK", "three"};

        assertArrayEquals(src, test.toArray());
        //System.out.println(Arrays.toString(test.toArray()));

        test.add(2, "OK");
        assertArrayEquals(expected, test.toArray());
        //System.out.println(Arrays.toString(test.toArray()));
    }

    @Test
    void getTest()
    {
        assertEquals("two", test.get(1));
    }

    @Test
    void removeTest()
    {
        String[] expected = new String[] {"one", "three"};

        String removed = test.remove(1);
        assertEquals("two", removed);
        assertArrayEquals(expected, test.toArray());
    }

    @Test
    void indexOfTest()
    {
        Integer[] src = new Integer[] {100, 2, 15, -10, 15, 100, 200, 25};

        ArrayList<Integer> testInt = new ArrayList<>(50);
        for (Integer i : src) {
            testInt.add(i);
        }

        assertEquals(2, testInt.indexOf(15));
    }

    @Test
    void lastIndexOfTest()
    {
        Integer[] src = new Integer[] {100, 2, 15, -10, 15, 100, 200, 25};

        ArrayList<Integer> testInt = new ArrayList<>(50);
        for (Integer i : src) {
            testInt.add(i);
        }

        assertEquals(4, testInt.lastIndexOf(15));
    }

    @Test
    void iteratorTest()
    {
        Integer[] src = new Integer[] {100, 2, 15, -10, 15, 100, 200, 25};

        ArrayList<Integer> testInt = new ArrayList<>(50);
        for (Integer i : src) {
            testInt.add(i);
        }

        Iterator<String> iterator = test.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("two", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("three", iterator.next());
        assertFalse(iterator.hasNext());

        Iterator<Integer> iterator1 = testInt.iterator();
        assertTrue(iterator1.hasNext());
        assertEquals(100, iterator1.next());
        assertTrue(iterator1.hasNext());
        assertEquals(2, iterator1.next());
        assertTrue(iterator1.hasNext());
        assertEquals(15, iterator1.next());
        assertTrue(iterator1.hasNext());
        assertEquals(-10, iterator1.next());
    }
}
