package ru.job4j.tracker;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemsReversSortTest {
    @Test
    public void compare() {
        List<Item> list = new ArrayList<>();
        Item petr = new Item("Petr");
        Item ivan = new Item("Ivan");
        Item roma = new Item("Roman");
        list.add(petr);
        list.add(ivan);
        list.add(roma);
        List<Item> res = new ArrayList<>();
        res.add(roma);
        res.add(petr);
        res.add(ivan);
        Collections.sort(list, new ItemsReversSort());
        assertThat(list, is(res));
    }
}