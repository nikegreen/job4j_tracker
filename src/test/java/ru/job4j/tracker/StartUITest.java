package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertEquals(tracker.findAll().length, 1);
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenAdd2Item() {
        String[] answers = {"Fix PC","fix PC 2"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        StartUI.createItem(input, tracker);
        Item[] created = tracker.findAll();
        Item[] expected = new Item[]{new Item("Fix PC"), new Item("fix PC 2")};
        assertEquals(created.length, expected.length);
        assertThat(created[0].getName(), is(expected[0].getName()));
        assertThat(created[1].getName(), is(expected[1].getName()));
    }
}