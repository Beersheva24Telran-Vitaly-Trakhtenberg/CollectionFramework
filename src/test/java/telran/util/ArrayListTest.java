package telran.util;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

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
}
