package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] data;
    private int point = 0;
    private final int size;

    public SimpleArrayIterator(T[] data, int size) {
        this.data = data;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return point < size;
    }

    @Override
    public T next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
