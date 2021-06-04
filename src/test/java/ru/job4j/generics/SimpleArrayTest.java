package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        assertTrue(str.get(0).equals("First"));
    }

    @Test
    public void set() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        str.set(0, "Fifth");
        assertTrue(str.get(0).equals("Fifth"));

    }

    @Test
    public void remove() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        str.add("Second");
        str.add("Third");
        str.remove(1);
        assertTrue(str.get(1).equals("Third"));

    }

    @Test
    public void get() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        System.out.println(str.get(0));
    }

    @org.junit.Test
    public void iterator() {
    }
}