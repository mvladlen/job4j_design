package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int count = 0;
    private int modCount = 0;
    private int capacity = 8;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (((float) (count + 1) / capacity) > LOAD_FACTOR) {
            expand();
        }
        int h = indexFor(hash(key.hashCode()));
        if (table[h] == null) {
            table[h] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode * 7;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        MapEntry<K, V>[] tableNew = table;
        table = new MapEntry[capacity * 2];
        count = 0;
        modCount++;
        capacity *= 2;
        int i = 0;
        while (i < tableNew.length) {
            if (tableNew[i] != null) {
                put(tableNew[i].key, tableNew[i].value);
            }
            i++;
        }
    }

    @Override
    public V get(K key) {
        int h = indexFor(hash(key.hashCode()));
        if (table[h] != null && table[h].key.equals(key)) {
            return table[h].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int h = indexFor(hash(key.hashCode()));
        if (table[h] != null && table[h].key.equals(key)) {
            table[h] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int size = count;
            private final MapEntry<K, V>[] data = table;
            private final int expectedModCount = modCount;
            private int point = 0;
            private int extracted = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return extracted < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (data[point] == null) {
                    point++;
                }
                extracted++;
                return data[point].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        final K key;
        final V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
