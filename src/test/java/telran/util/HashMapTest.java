package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        assertArrayEquals(expected, actual);
    }

    @BeforeEach
    @Override
    void setUp() {
        map = new HashMap<>();
        super.setUp();
    }

}
