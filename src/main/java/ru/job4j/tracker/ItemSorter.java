package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemSorter {
    public static void main(String[] args) {
        List<Item> list = new ArrayList<>();
        list.add(new Item("Petr"));
        list.add(new Item("Ivan"));
        list.add(new Item("Roman"));
        for (Item item : list) {
            System.out.println(item);
        }
        System.out.println("by id:");
        Collections.sort(list, new ItemsSort());
        for (Item item : list) {
            System.out.println(item);
        }
        System.out.println("reverse by id:");
        Collections.sort(list, new ItemsReversSort());
        for (Item item : list) {
            System.out.println(item);
        }
    }
}
