package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container;
    private int modCount;
    private int filled;

    public SimpleArray() {
        this.container = (T[]) new Object[1];
        this.filled = 0;
        this.modCount = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, filled);
        return container[index];
    }

    public void add(T model) {
        if (filled >= container.length) {
            expand();
        }
        container[filled++] = model;
        modCount++;
    }

    private void expand() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int size = filled;
            private final T[] data = container;
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
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
