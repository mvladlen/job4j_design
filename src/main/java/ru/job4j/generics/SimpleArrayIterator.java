package ru.job4j.generics;
import java.util.Iterator;

public class SimpleArrayIterator<T> implements Iterator<T> {
        private final T[] data;
        private int point = 0;

    public SimpleArrayIterator(T[] data) {
        this.data = data;
    }

        @Override
        public boolean hasNext() {
            return point < data.length;
        }

        @Override
        public T next() {
            return data[point++];
        }
}
