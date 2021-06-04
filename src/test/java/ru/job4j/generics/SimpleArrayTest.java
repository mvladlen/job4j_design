package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

public class SimpleArrayTest {


    @Test
    public void add() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
    }

    @Test
    public void set() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        str.set(0, "Fifth");
    }

    @Test
    public void remove() {
        SimpleArray<String> str = new SimpleArray<>(10);
        str.add("First");
        str.remove(0);
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