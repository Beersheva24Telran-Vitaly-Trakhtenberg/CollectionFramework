package telran.util;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TreeMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual)
    {
        assertArrayEquals(expected, actual);
    }

    @BeforeEach
    @Override
    void setUp()
    {
        map = new TreeMap<>();
        super.setUp();
    }
}
