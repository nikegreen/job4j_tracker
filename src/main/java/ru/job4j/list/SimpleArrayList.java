package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void resize() {
        container = Arrays.copyOf(container, container.length == 0 ? 16 : container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            resize();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return  value;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        container[size] = null;
        modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.index < size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[this.index++];
            }
        };
    }
}
