package ru.job4j.it;

import static java.util.List.of;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.*;

public class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = of(
                of(1).iterator(),
                of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.next(), is(2));
        assertThat(flat.next(), is(3));
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = of(
                of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.next(), is(2));
        assertThat(flat.next(), is(3));
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = of(
                of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.hasNext(), is(true));
        assertThat(flat.hasNext(), is(true));
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = of(
                of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = of(
                of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        flat.next();
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = of(
                of().iterator(),
                of().iterator(),
                of().iterator(),
                of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertTrue(flat.hasNext());
        assertThat(1, is(flat.next()));
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = of(
                of().iterator(),
                of().iterator(),
                of().iterator(),
                of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        assertFalse(flat.hasNext());
    }

    @Test
    public void whenSeveralEmpty2ThenReturnFalse() {
        Iterator<Iterator<Object>> it = Collections.emptyIterator();
        FlatMap flat = new FlatMap(it);
        assertFalse(flat.hasNext());
    }
}