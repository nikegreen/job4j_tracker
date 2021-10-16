package ru.job4j.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void distance1() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,2);
        assertEquals(2, p1.distance(p2), 0.0000001);
    }

    @Test
    public void distance2() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(2,0);
        assertEquals(2, p1.distance(p2), 0.0000001);
    }

    @Test
    public void distance3() {
        Point p1 = new Point(0,2);
        Point p2 = new Point(2,2);
        assertEquals(2, p1.distance(p2), 0.0000001);
    }
}