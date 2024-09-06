package telran.util;

import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class AbstractMap <K, V> implements Map<K, V>
{
    protected Set<Entry<K, V>> set;
    private Set<K> keySet;

    protected abstract Set<K> getEmptyKeySet();

    public AbstractMap() {
        this.keySet = new HashSet<>();
    }

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
            keySet.add(key);
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
        return keySet.contains((K)key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        boolean res = false;
        Iterator<Entry<K, V>> iterator = set.iterator();
        while (!res && iterator.hasNext()) {
            Entry<K, V> item = iterator.next();
            if (Objects.equals(value, item.getValue())) { res = true; }
        }
        return res;
    }

    @Override
    public Set<K> keySet()
    {
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return set;
    }

    @Override
    public Collection<V> values()
    {
        Collection<V> values = new ArrayList<>();
        set.forEach(entry -> values.add(entry.getValue()));
        return values;
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