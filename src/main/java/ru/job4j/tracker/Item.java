package ru.job4j.tracker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import lombok.Data;

@Data
public class Item implements Comparable<Item> {

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(
            "dd-MMMM-EEEE-yyyy HH:mm:ss");
    private int id;
    private String name;
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, Timestamp dateTime) {
        this.id = id;
        this.name = name;
        this.created = dateTime;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", created=" + DATEFORMAT.format(created)
                + "}";
    }

    @Override
    public int compareTo(Item another) {
        int res = name.compareTo(another.name);
        return res == 0 ? created.compareTo(another.created) : res;
    }
}