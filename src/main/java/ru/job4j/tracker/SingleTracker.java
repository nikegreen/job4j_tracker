package ru.job4j.tracker;

public final class SingleTracker {
    private static Tracker tracker = new Tracker();

    private SingleTracker() {

    }

    public static Tracker getInstance() {
        return SingleTracker.tracker;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public Item[] findByName(String name) {
        return tracker.findByName(name);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    public boolean delete(int id) {
        return tracker.delete(id);
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean equals(Object obj) {
        return tracker.equals(obj);
    }

    public int hashCode() {
        return tracker.hashCode();
    }

    public String toString() {
        return tracker.toString();
    }
}