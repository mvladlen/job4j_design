package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (isEmpty()) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (!isEmpty()) {
            node.next = head;
        }
        head = node;
    }


    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public boolean revert() {
        if (isEmpty() || head.next == null) {
            return false;
        }
        Node<T> node = head;
        Node<T> previous = null;
        while (node.next != null) {
            head = node.next;
            node.next = previous;
            previous = node;
            node = head;
        }
        head.next = previous;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}