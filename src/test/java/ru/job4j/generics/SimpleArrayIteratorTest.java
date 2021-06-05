package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleArrayIteratorTest {

    @Test
    public void hasNext() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        Iterator iterator = str.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void next() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        Iterator iterator = str.iterator();
        assertEquals(iterator.next(), "First");

    }
}