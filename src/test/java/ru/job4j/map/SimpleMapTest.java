package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putReturnTrue1() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
    }

    @Test
    public void putReturnTrue2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(1, 1));
    }

    @Test
    public void putReturnTrue3() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(1, 2));
    }

    @Test
    public void get1() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void get2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertNull(map.get(2));
    }

    @Test
    public void get3() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertThat(map.get(1), is(1));
    }

    @Test
    public void remove1() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertFalse(map.remove(1));
    }

    @Test
    public void remove2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(2, 1));
        assertFalse(map.remove(1));
    }

    @Test
    public void remove3() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(2, 1));
        assertTrue(map.remove(2));
    }

    @Test
    public void iteratorHasNextReturnFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorHasNext1() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorHasNext2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorHasNext3() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertTrue(map.put(1, 1));
        it.hasNext();
    }

    @Test
    public void iteratorNext1() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNext2() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNext3() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(1));
        it.next();
    }
}