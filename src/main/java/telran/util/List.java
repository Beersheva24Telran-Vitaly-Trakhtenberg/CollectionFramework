package telran.util;

import java.util.function.Predicate;

public interface List<T> extends Collection<T>
{
    void add(int index, T obj);
    T remove(int index);
    T get(int index);
    int indexOf(T pattern);
    int lastIndexOf(T pattern);
}
