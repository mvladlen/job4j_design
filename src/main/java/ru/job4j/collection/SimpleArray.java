package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] contaner;
    private int modCount;
    private int filled;

    public SimpleArray() {
        this.contaner = (T[]) new Object[1];
        this.filled = 0;
        this.modCount = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, filled);
        return contaner[index];
    }

    public void add(T model) {
        if (filled >= contaner.length) {
            T[] newContainer = (T[]) new Object[filled+1];
            System.arraycopy(this.contaner, 0, newContainer, 0, filled);
            contaner = newContainer;
        }
        contaner[filled++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private final int size = filled;
            private final T[] data = contaner;
            private final int expectedModCount  = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount !=modCount) throw new ConcurrentModificationException();
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[point++];
            }
        };
    }
}
