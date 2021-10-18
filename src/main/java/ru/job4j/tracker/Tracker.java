package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = this.indexOf(id);
        return index == -1 ? null : this.items[index];
    }

    public Item[] findByName(String name) {
        Item[] rsl = new Item[items.length];
        int count = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName().equals(name)) {
                rsl[count++] = item;
            }
        }
        return Arrays.copyOf(rsl, count);
    }

    public Item[] findAll() {
        return items;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
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
            this.items[index].setName(item.getName());
        }
        return result;
    }
}