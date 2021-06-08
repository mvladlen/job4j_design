package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int count;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public SimpleLinkedList() {
        count = 0;
        modCount = 0;
    }

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value);
        node.next = node;
        if (count == 0) {
            this.first = node;
        } else {
            last.next = node;
        }
        this.last = node;
        modCount++;
        count++;
    }

    @Override
    public E get(int index) {
        Node<E> node = this.first;
        Objects.checkIndex(index, count);
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.container;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int mod = modCount;
            private Node<E> iterator = first;
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                if (modCount != mod) {
                    throw new ConcurrentModificationException();
                }
                return pointer < count;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = iterator.container;
                iterator = iterator.next;
                pointer++;
                return value;
            }
        };
    }

    private static class Node<E> {
        private final E container;
        private Node<E> next;

        public Node(E value) {
            container = value;
        }
    }
}