package org.kefirsf.tk;

import java.util.*;

/**
 * The map for flash scope.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class FlashMap implements Map<String, Object> {
    private final Map<String, Object> old;
    private final Map<String, Object> young;

    public FlashMap(Map<String, Object> old) {
        if (old == null) {
            throw new IllegalArgumentException("Parameter old of constructor of FlashMap can\'t be null.");
        }

        this.old = Collections.unmodifiableMap(old);
        this.young = new HashMap<String, Object>();
    }

    public FlashMap() {
        this.old = Collections.emptyMap();
        this.young = new HashMap<String, Object>();
    }

    public int size() {
        return old.size() + young.size();
    }

    public boolean isEmpty() {
        return young.isEmpty() && old.isEmpty();
    }

    public boolean containsKey(Object key) {
        return young.containsKey(key) || old.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return young.containsValue(value) || old.containsValue(value);
    }

    public Object get(Object key) {
        Object value = young.get(key);
        if (value == null) {
            value = old.get(key);
        }
        return value;
    }

    public Object put(String key, Object value) {
        return young.put(key, value);
    }

    public Object remove(Object key) {
        return young.remove(key);
    }

    public void putAll(Map<? extends String, ?> t) {
        young.putAll(t);
    }

    public void clear() {
        young.clear();
    }

    public Set<String> keySet() {
        Set<String> keySet = new HashSet<String>();
        keySet.addAll(young.keySet());
        keySet.addAll(old.keySet());
        return keySet;
    }

    public Collection<Object> values() {
        Set<Object> valueSet = new HashSet<Object>();
        valueSet.addAll(old.values());
        valueSet.addAll(young.values());
        return valueSet;
    }

    public Set<Entry<String, Object>> entrySet() {
        Set<Entry<String, Object>> entrySet = new HashSet<Entry<String, Object>>();
        entrySet.addAll(young.entrySet());
        entrySet.addAll(old.entrySet());
        return entrySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashMap)) return false;

        FlashMap flashMap = (FlashMap) o;

        return old.equals(flashMap.old) && young.equals(flashMap.young);

    }

    @Override
    public int hashCode() {
        int result = old.hashCode();
        result = 31 * result + young.hashCode();
        return result;
    }

    public Map<String, Object> getYoung() {
        return young;
    }
}
