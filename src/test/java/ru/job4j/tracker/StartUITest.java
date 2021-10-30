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
        UserAction action = new CreateAction(new StubOutput());
        action.execute(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertEquals(tracker.findAll().length, 1);
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenAdd2Item() {
        String[] answers = {"Fix PC", "fix PC 2"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        UserAction action = new CreateAction(new StubOutput());
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
        UserAction action = new ReplaceAction(new StubOutput());
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
        UserAction action = new DeleteAction(new StubOutput());
        action.execute(input, tracker);
        Item replaced = tracker.findById(item.getId());
        assertNull(replaced);
    }

    @Test
    public void whenInitCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenInitReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenInitDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }
}