package ru.job4j.tracker;

import java.util.Arrays;
import java.util.ArrayList;

public class Tracker {
    private final ArrayList<Item> items = new ArrayList<Item>();

    public Item add(Item item) {
        item.setId(items.size());
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = this.indexOf(id);
        return index == -1 ? null : this.items.get(index);
    }

    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> rsl = new ArrayList<Item>();
        int count = 0;
        for (Item item : items) {
            if (item.getName().equals(name)) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    public ArrayList<Item> findAll() {
        return items;
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

    public boolean replace(int id, Item item) {
        int index = this.indexOf(id);
        boolean result = index != -1;
        if (result) {
            item.setId(id);
            items.set(index, item);
        }
        return result;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            items.remove(index);
        }
        return result;
    }
}