package telran.util;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

public class ArrayListTest extends ListTest
{
    @BeforeEach
    @Override
    void setUp()
    {
        collection = new ArrayList<>(3);
        super.setUp();
    }

    @Test
    void addLastTest()
    {

    }

    @Test
    void addByIndexTest()
    {
        String[] src = new String[] {"one", "two", "three"};
        String[] expected = new String[] {"one", "two", "OK", "three"};

        ArrayList test = new ArrayList(10);
        for (String s : src) {
            test.add(s);
        }
        assertArrayEquals(src, test.toArray());
        //System.out.println(Arrays.toString(test.toArray()));

        test.add(2, "OK");
        assertArrayEquals(expected, test.toArray());
        //System.out.println(Arrays.toString(test.toArray()));
    }

}
