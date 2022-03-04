package ru.job4j.tracker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return getId() == item.getId()
                && getName().equals(item.getName())
                && getCreated().equals(item.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreated());
    }
}