package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;

    class Node {
        private Node prev;
        private Node next;
        private E value;

        public Node(Node thePrev, E theValue, Node theNext) {
            prev = thePrev;
            value = theValue;
            next = theNext;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }
    }

    private int modCount;
    private Node first;
    private Node last;

    @Override
    public void add(E value) {
        Node v = new Node(last, value, null);
        if (first == null) {
            first = v;
        } else {
            first.setNext(v);
        }
        last = v;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node node = first;
        while (node != null && index >= 0) {
            if (index == 0) {
                return node.getValue();
            }
            node = node.getNext();
            index--;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node node = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                E val = null;
                if (hasNext()) {
                    val = node.getValue();
                    node = node.getNext();
                }
                return val;
            }
        };
    }
}