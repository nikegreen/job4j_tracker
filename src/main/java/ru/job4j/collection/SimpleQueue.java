package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T res;
        try {
            res = out.pop();
        } catch (NoSuchElementException e) {
            try {
                for (;;) {
                    out.push(in.pop());
                }
            } catch (NoSuchElementException ee) {
                res = out.pop();
            }
        }
        return res;
    }

    public void push(T value) {
        in.push(value);
    }
}
