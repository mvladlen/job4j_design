package ru.job4j.generics;

import java.util.Objects;

public class SimpleArray<T> {
    private final T[] array;
    private int filled;
    private int point;

    public SimpleArray(int size) {
        this.array = (T[]) new Object[size];
        filled = 0;
        point = 0;
    }

    public void add(T model) {
        this.array[filled++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, filled);
        this.array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, filled);
        System.arraycopy(this.array, index + 1, this.array, index, this.array.length - index - 1);
        filled--;
    }

    public T get(int index) {
        Objects.checkIndex(index, filled);
        return this.array[index];
    }

    public boolean hasNext() {
        return point < array.length;
    }

    public T next() {
        return array[point++];
    }

}
