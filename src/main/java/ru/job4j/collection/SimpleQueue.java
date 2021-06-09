package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private int outCount = 0;

    public T poll() {
        copyInOut();
        outCount--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCount++;
    }

    private void copyInOut() {
        if (outCount == 0)
            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }

    }

}