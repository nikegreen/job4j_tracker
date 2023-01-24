package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenTestFindById() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item bug = new Item("Bug");
            Item item = tracker.add(bug);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenTestFindAll() {
        try (HbmTracker tracker = new HbmTracker()) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            Item first = new Item("First");
            first.setParticipates(List.of());
            Item second = new Item("Second");
            second.setParticipates(List.of());
            int index = tracker.findAll().size();
            first = tracker.add(first);
            second = tracker.add(second);
            Item result = tracker.findAll().get(index);
            assertThat(result.getId()).isEqualTo(first.getId());
            assertThat(result.getName()).isEqualTo(first.getName());
            assertThat(result.getCreated().format(dateTimeFormatter))
                    .isEqualTo(first.getCreated().format(dateTimeFormatter));
            assertThat(result.getParticipates())
                    .containsExactlyElementsOf(first.getParticipates());
            result = tracker.findAll().get(index  + 1);
            assertThat(result.getId()).isEqualTo(second.getId());
            assertThat(result.getName()).isEqualTo(second.getName());
            assertThat(result.getCreated().format(dateTimeFormatter))
                    .isEqualTo(second.getCreated().format(dateTimeFormatter));
            assertThat(result.getParticipates())
                    .containsExactlyElementsOf(second.getParticipates());
        }
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item first = new Item("First");
            Item second = new Item("Second");
            int count = tracker.findByName(first.getName()).size();
            tracker.add(first);
            tracker.add(second);
            tracker.add(new Item("First"));
            tracker.add(new Item("Second"));
            tracker.add(new Item("First"));
            List<Item> result = tracker.findByName(first.getName());
            assertThat(result.size()).isEqualTo(count + 3);
        }
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item first = new Item("First");
            Item second = new Item("Second");
            tracker.add(first);
            tracker.add(second);
            tracker.add(new Item("First"));
            tracker.add(new Item("Second"));
            tracker.add(new Item("First"));
            List<Item> result = tracker.findByName(second.getName());
            assertThat(result.get(1).getName()).isEqualTo(second.getName());
        }
    }

    @Test
    public void whenReplace() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item bug = new Item();
            bug.setName("Bug");
            bug.setParticipates(List.of());
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item();
            bugWithDesc.setName("Bug with description");
            bugWithDesc.setParticipates(List.of());
            tracker.replace(id, bugWithDesc);
            assertThat(tracker.findById(id).getName()).isEqualTo("Bug with description");
        }
    }

    @Test
    public void whenDelete() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item bug = new Item();
            bug.setName("Bug");
            tracker.add(bug);
            int id = bug.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id)).isNull();
        }
    }
}