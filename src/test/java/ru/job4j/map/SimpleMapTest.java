package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest  {

       SimpleMap<Integer, String> map = new SimpleMap<>();
       String stringOne = "one";
       String stringTwo = "two";

   @Test
    public void whenPut() {
        map.put(stringOne.hashCode(), stringOne);
        assertEquals(stringOne, map.get(stringOne.hashCode()));
    }

    @Test
    public void whenPutTwo() {
        map.put(stringOne.hashCode(), stringOne);
        map.put(stringTwo.hashCode(), stringTwo);
        assertEquals(stringOne, map.get(stringOne.hashCode()));
        assertEquals(stringTwo, map.get(stringTwo.hashCode()));
    }

    @Test
    public void whenGetFromEmpty() {
        assertNull(map.get(stringOne.hashCode()));
    }

    @Test
    public void whenGetFromFilled() {
        map.put(stringTwo.hashCode(), stringTwo);
        assertNull(map.get(stringOne.hashCode()));
    }

    @Test
    public void whenRemoveFirst() {
        map.put(stringOne.hashCode(), stringOne);
        map.put(stringTwo.hashCode(), stringTwo);
        assertEquals(stringOne, map.get(stringOne.hashCode()));
        map.remove(stringOne.hashCode());
        assertNull(map.get(stringOne.hashCode()));
    }

    @Test
    public void whenRemoveAll() {
        map.put(stringOne.hashCode(), stringOne);
        map.put(stringTwo.hashCode(), stringTwo);
        assertEquals(stringOne, map.get(stringOne.hashCode()));
        map.remove(stringTwo.hashCode());
        map.remove(stringOne.hashCode());
        assertNull(map.get(stringOne.hashCode()));
    }


    @Test
    public void whenIterator() {
        map.put(stringOne.hashCode(), stringOne);
        map.put(stringTwo.hashCode(), stringTwo);
        Iterator<Integer> iterator = map.iterator();
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorEnds() {
        map.put(stringOne.hashCode(), stringOne);
        Iterator<Integer> iterator = map.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test
    public void whenExpanded() {
        map.put(1, stringOne);
        map.put(2, stringTwo);
        map.put(3, stringOne);
        map.put(4, stringTwo);
        map.put(5, stringOne);
        map.put(6, stringTwo);
        map.put(7, stringOne);
        map.put(8, stringTwo);
        map.put(9, stringOne);
        map.put(10, stringTwo);
        Iterator<Integer> iterator = map.iterator();
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
    }


}