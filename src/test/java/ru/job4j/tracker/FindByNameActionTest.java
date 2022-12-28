package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameActionTest {
    @Test
    public void executeMemTracker() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "Item2";
        tracker.add(new Item("Item1"));
        Item item = tracker.add(new Item(name));
        tracker.add(new Item("Item3"));
        FindByNameAction action = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(name);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find items by name ====" + ln + item.toString() + ln);
    }

    @Test
    public void execute2MemTracker() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        tracker.add(new Item("Item3"));
        FindByNameAction action = new FindByNameAction(out);
        Input input = mock(Input.class);
        String name = "item4";
        when(input.askStr(any(String.class))).thenReturn(name);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find items by name ====" + ln
                        + "Заявки с именем: " + name + " не найдены." + ln);
    }

    @Test
    public void execute3MemTracker() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "Item2";
        tracker.add(new Item("Item1"));
        Item item1 = tracker.add(new Item(name));
        Item item2 = tracker.add(new Item(name));
        tracker.add(new Item("Item3"));
        FindByNameAction action = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(name);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find items by name ====" + ln
                        + item1.toString() + ln
                        + item2.toString() + ln);
    }
}
