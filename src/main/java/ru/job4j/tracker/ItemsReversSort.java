package ru.job4j.tracker;

import java.util.Comparator;

public class ItemsReversSort implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        int res = second.getName().compareTo(first.getName());
        return res == 0 ? second.getCreated().compareTo(first.getCreated()) : res;
    }
}
