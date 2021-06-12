package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private final SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T s : set) {
            if ((s == null && value == null) || (s.equals(value))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}