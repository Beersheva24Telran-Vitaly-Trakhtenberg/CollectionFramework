package telran.util;

@SuppressWarnings("unchecked")
public interface Map<K, V>
{
    public static class Entry<K, V> implements Comparable<Entry<K,V>>
    {
        private final K key;
        private V value;
        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
        public V getValue()
        {
            return value;
        }
        public K getKey()
        {
            return key;
        }
        public void setValue(V value)
        {
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> other)
        {
            return ((Comparable<K>)key).compareTo(other.getKey());
        }
        @Override
        public int hashCode()
        {
            return key.hashCode();
        }
        @Override
        public boolean equals (Object obj)
        {
            Entry<K,V> entry = (Entry<K,V>) obj;

            return key.equals(entry.key);
        }
    }
    V get(Object key);
    default  V getOrDefault(Object key, V defaultValue)
    {
        V res = get(key);
        if (res == null) {
            res = defaultValue;
        }
        return res;
    }
    V put(K key, V value);
    default V putIfAbsent(K key, V value)
    {
        V old_value = get(key);
        if (old_value == null) {
            Entry<K, V> new_entry = new Entry<>(key, value);
            keySet().add(new_entry.getKey());
            entrySet().add(new_entry);
        }
        return old_value;
    }
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    Set<K> keySet();
    Set<Entry<K,V>> entrySet();
    Collection<V> values();
    int size();
    boolean isEmpty();

}