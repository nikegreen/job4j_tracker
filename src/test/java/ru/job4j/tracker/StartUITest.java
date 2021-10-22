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
        UserAction action=new CreateAction();
        action.execute(input, tracker);
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
        UserAction action=new CreateAction();
        action.execute(input, tracker);
        action.execute(input, tracker);
        Item[] created = tracker.findAll();
        Item[] expected = new Item[]{new Item("Fix PC"), new Item("fix PC 2")};
        assertEquals(created.length, expected.length);
        assertThat(created[0].getName(), is(expected[0].getName()));
        assertThat(created[1].getName(), is(expected[1].getName()));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()),
                "replaced item"
        };
        Input input = new StubInput(answers);
        UserAction action=new ReplaceAction();
        action.execute(input, tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()),
        };
        Input input = new StubInput(answers);
        UserAction action=new DeleteAction();
        action.execute(input, tracker);
        Item replaced = tracker.findById(item.getId());
        assertNull(replaced);
    }
}