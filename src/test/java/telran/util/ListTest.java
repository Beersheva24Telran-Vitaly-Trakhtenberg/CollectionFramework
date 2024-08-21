package telran.util;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest
{
    List<Integer> list;

    @Override
    void setUp()
    {
        super.setUp();
        list = (List<Integer>) collection;
    }

}
