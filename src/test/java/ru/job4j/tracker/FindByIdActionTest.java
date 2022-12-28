package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdActionTest {
    @Test
    public void executeMemTracker() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        Item item = tracker.add(new Item("Item2"));
        tracker.add(new Item("Item3"));
        FindByIdAction action = new FindByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find item by id ====" + ln + item + ln);
    }

    @Test
    public void execute2MemTracker() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        Item item = tracker.add(new Item("Item3"));
        FindByIdAction action = new FindByIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId() + 1);
        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find item by id ====" + ln
                        + "Заявка с введенным id: " + (item.getId() + 1) + " не найдена." + ln);
    }
}