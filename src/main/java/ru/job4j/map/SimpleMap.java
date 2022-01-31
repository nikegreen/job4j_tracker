package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count / capacity > LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> entry = table[index];
        boolean empty = entry == null;
        if (empty) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        } else if (entry.key.equals(key)) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            empty = true;
        }
        return empty;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] old = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : old) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
        return entry == null ? null : entry.key.equals(key) ? entry.value : null;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> entry = table[index];
        boolean res = false;
        if (entry != null) {
            res = entry.key.equals(key);
            count--;
            modCount++;
            table[index] = null;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = -1;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean res = false;
                for (int i = index + 1; (i < capacity) && !res; i++) {
                    res = table[i] != null;
                }
                return res;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> res = null;
                int i = index + 1;
                for (; (i < capacity) && res == null; i++) {
                    res = table[i];

                }
                index = i;
                return res.key;
            }
        };
    }

    private static class MapEntry<K, V> {
        private final K key;

        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
