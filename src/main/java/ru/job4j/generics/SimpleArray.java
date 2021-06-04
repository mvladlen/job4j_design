package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable {
    private final ArrayList<T> array;
    private int filled;

    public SimpleArray(int size) {
        this.array = new ArrayList<>(size);

    }

    public void add(T model) {
        this.array.add(model);
    }

    public void set(int index, T model) {
        this.array.set(index, model);
    }

    public void remove(int index) {
        this.array.remove(index);
    }

    public T get(int index) {
        return this.array.get(index);
    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }
}
