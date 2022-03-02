package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MemTracker implements Store {
    private int ids = 1;
    private final List<Item> items = new ArrayList<Item>();

    @Override
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    @Override
    public Item findById(int id) {
        int index = this.indexOf(id);
        return index == -1 ? null : this.items.get(index);
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> rsl = new ArrayList<Item>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(items);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = this.indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items.set(index, item);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            items.remove(index);
        }
        return result;
    }

    @Override
    public void close() {
    }
}