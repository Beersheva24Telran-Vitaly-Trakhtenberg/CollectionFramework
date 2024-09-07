package telran.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractMapTest
{
    Integer[] keys = {1, -1};
    Map<Integer, Integer> map;

    void setUp() {
        map.put(keys[0], 10);
        map.put(keys[1], 20);
    }

    abstract <T> void runTest(T [] expected, T [] actual);

    @Test
    public void testPutAndGet() {
        map.put(2, 30);
        assertEquals(30, map.get(2));
    }

    @Test
    public void testContainsKey() {
        assertTrue(map.containsKey(keys[0]));
        assertFalse(map.containsKey(2));
    }

    @Test
    public void testContainsValue() {
        assertTrue(map.containsValue(10));
        assertFalse(map.containsValue(30));
    }

    @Test
    public void testKeySet() {
        Set<Integer> keys = map.keySet();
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(-1));
    }

    @Test
    public void testValues() {
        Collection<Integer> values = map.values();
        assertTrue(values.contains(10));
        assertTrue(values.contains(20));
    }

    @Test
    public void testBrokeMap()
    {
        map.keySet().add(1000000);
        assertFalse(map.containsKey(1000000));
    }
}