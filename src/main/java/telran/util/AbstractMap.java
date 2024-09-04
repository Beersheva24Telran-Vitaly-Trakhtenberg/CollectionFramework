package telran.util;

@SuppressWarnings("unchecked")
public abstract class AbstractMap <K, V> implements Map<K, V>
{
    protected Set<Entry<K, V>> set;
    protected abstract Set<K> getEmptyKeySet();
    @Override
    public V get(Object key)
    {
        Entry<K, V> pattern = new Entry<>((K)key, null);
        Entry<K,V> entry = set.get(pattern);
        V res = null;
        if (entry != null) {
            res = entry.getValue();
        }
        return res;
    }

    @Override
    public V put(K key, V value)
    {
        Entry<K, V> new_entry = new Entry<>(key, value);
        Entry<K, V> existed_entry = set.get(new_entry);
        V res = null;
        if (existed_entry == null) {
            set.add(new_entry);
        } else {
            V existed_value = existed_entry.getValue();
            existed_entry.setValue(value);
            res = existed_value;
        }

        return res;
    }

    @Override
    public boolean containsKey(Object key)
    {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method AbstractMap.containsKey not implemented yet");
    }

    @Override
    public boolean containsValue(Object value)
    {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method AbstractMap.containsValue not implemented yet");
    }

    @Override
    public Set<K> keySet()
    {
        Set<K> keySet = getEmptyKeySet();
        //TODO
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return set;
    }

    @Override
    public Collection<V> values()
    {
        // TODO Implement this method
        throw new UnsupportedOperationException("Method AbstractMap.values not implemented yet");
    }

    @Override
    public int size()
    {
        return set.size();
    }

    @Override
    public boolean isEmpty()
    {
        return set.isEmpty();
    }

}