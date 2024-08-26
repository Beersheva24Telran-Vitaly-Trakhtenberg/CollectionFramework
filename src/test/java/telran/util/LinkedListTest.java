package telran.util;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest extends ListTest
{
    LinkedList<String> testString;
    LinkedList<Integer> testInteger;

    @BeforeEach
    @Override
    void setUp()
    {
        String[] srcString = new String[] {"one", "two", "three", "four", "five"};
        Integer[] srcInteger = new Integer[] {1,2,3,4,5,6,7,8,9,1,2,6,5};

        collection = new LinkedList<>();
        super.setUp();

        testString = new LinkedList<>();
        for (String s : srcString) {
            testString.add(s);
        }

        testInteger = new LinkedList<>();
        for (Integer i : srcInteger) {
            testInteger.add(i);
        }
    }

    @Test
    void addTest()
    {
        String newString = "Inserted";
        Integer newInteger = -100;
        testString.add(2, newString);
        testInteger.add(4, newInteger);

        assertEquals(newString, testString.get(2));
        assertEquals(newInteger, testInteger.get(4));
    }

    @Test
    void removeTest()
    {
        String removedString = testString.remove(2);
        Integer removedInteger = testInteger.remove(4);

        assertEquals(removedString, "three");
        assertEquals(removedInteger, 5);
    }

    @Test
    void getTest()
    {
        assertEquals(testString.get(2), "three");
        assertEquals(testInteger.get(4), 5);
    }

    @Test
    void getIndexesTest()
    {
        assertEquals(testString.indexOf("one"), 0);
        assertEquals(testInteger.indexOf(1), 0);
        assertEquals(testInteger.lastIndexOf(1), 9);
    }

    @Test
    void sizeTest()
    {
        assertEquals(testString.size(), 5);
        assertEquals(testInteger.size(), 13);
    }

    @Test
    void isEmptyTest()
    {
        assertFalse(testString.isEmpty());
        assertFalse(testInteger.isEmpty());
    }

    @Test
    void containsTest()
    {
        assertTrue(testString.contains("four"));
        assertFalse(testString.contains("no"));
        assertTrue(testInteger.contains(2));
    }

    @Test
    void iteratorTest()
    {
        Iterator<Integer> iterator = testInteger.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), testInteger.get(i));
            i++;
        }
    }
}
