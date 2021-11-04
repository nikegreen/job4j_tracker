package ru.job4j.tracker;

import java.util.Comparator;

public class ItemsSort implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        int res = first.getName().compareTo(second.getName());
        return res == 0 ? first.getCreated().compareTo(second.getCreated()) : res;
    }
}
