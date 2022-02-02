package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count > LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        boolean empty = table[index] == null;
        if (empty) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
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
        return entry != null &&  entry.key.equals(key) ? entry.value : null;
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
                int i = index;
                do {
                    i++;
                } while ((i < capacity) && table[i] == null);
                return i < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> res;
                do {
                    index++;
                } while ((res = table[index]) == null);
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
