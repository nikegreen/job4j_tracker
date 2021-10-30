package ru.job4j.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void semiPerimeter() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 0);
        Triangle t = new Triangle(p1, p2, p3);
        assertEquals(3.41421,
                        t.semiPerimeter(p1.distance(p2),
                            p1.distance(p3),
                            p2.distance(p3)),
                0.001);
    }

    @Test
    public void existTrue() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 0);
        Triangle t = new Triangle(p1, p2, p3);
        assertTrue(t.exist(p1.distance(p2), p1.distance(p3), p2.distance(p3)));
    }

    @Test
    public void existFalse() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(0, 1);
        Triangle t = new Triangle(p1, p2, p3);
        assertFalse(t.exist(p1.distance(p2), p1.distance(p3), p2.distance(p3)));
    }

    @Test
    public void area() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 0);
        Triangle t = new Triangle(p1, p2, p3);
        assertEquals(2, t.area(), 0.001);
    }
}