package ru.job4j.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxTest {

    @Test
    public void max2N1() {
        assertEquals(4, Max.max(1, 4));
    }

    @Test
    public void max2N2() {
        assertEquals(4, Max.max(4, 1));
    }

    @Test
    public void testMax3N1() {
        assertEquals(4, Max.max(4, 1, 2));
    }

    @Test
    public void testMax3N2() {
        assertEquals(4, Max.max(4, 2, 1));
    }

    @Test
    public void testMax3N3() {
        assertEquals(4, Max.max(1, 2, 4));
    }

    @Test
    public void testMax3N4() {
        assertEquals(4, Max.max(2, 1, 4));
    }

    @Test
    public void testMax3N5() {
        assertEquals(4, Max.max(1, 4, 2));
    }

    @Test
    public void testMax3N6() {
        assertEquals(4, Max.max(2, 4, 1));
    }

    @Test
    public void testMax4N1() {
        assertEquals(4, Max.max(4, 1, 2, 3));
    }

    @Test
    public void testMax4N2() {
        assertEquals(4, Max.max(4, 1, 3, 2));
    }

    @Test
    public void testMax4N3() {
        assertEquals(4, Max.max(4, 2, 1, 3));
    }

    @Test
    public void testMax4N4() {
        assertEquals(4, Max.max(4, 2, 3, 1));
    }

    @Test
    public void testMax4N5() {
        assertEquals(4, Max.max(4, 3, 1, 2));
    }

    @Test
    public void testMax4N6() {
        assertEquals(4, Max.max(4, 3, 2, 1));
    }

    @Test
    public void testMax4N7() {
        assertEquals(4, Max.max(1, 4, 2, 3));
    }

    @Test
    public void testMax4N8() {
        assertEquals(4, Max.max(1, 4, 3, 2));
    }

    @Test
    public void testMax4N9() {
        assertEquals(4, Max.max(2, 4, 1, 3));
    }

    @Test
    public void testMax4N10() {
        assertEquals(4, Max.max(2, 4, 3, 1));
    }

    @Test
    public void testMax4N11() {
        assertEquals(4, Max.max(3, 4, 2, 1));
    }

    @Test
    public void testMax4N12() {
        assertEquals(4, Max.max(3, 4, 1, 2));
    }

    @Test
    public void testMax4N13() {
        assertEquals(4, Max.max(1, 2, 4, 3));
    }

    @Test
    public void testMax4N14() {
        assertEquals(4, Max.max(2, 1, 4, 3));
    }

    @Test
    public void testMax4N15() {
        assertEquals(4, Max.max(3, 1, 4, 2));
    }

    @Test
    public void testMax4N16() {
        assertEquals(4, Max.max(3, 2, 4, 1));
    }

    @Test
    public void testMax4N17() {
        assertEquals(4, Max.max(1, 2, 3, 4));
    }

    @Test
    public void testMax4N18() {
        assertEquals(4, Max.max(2, 1, 3, 4));
    }

    @Test
    public void testMax4N19() {
        assertEquals(4, Max.max(1, 3, 2, 4));
    }

    @Test
    public void testMax4N20() {
        assertEquals(4, Max.max(2, 3, 1, 4));
    }

    @Test
    public void testMax4N21() {
        assertEquals(4, Max.max(3, 1, 2, 4));
    }

    @Test
    public void testMax4N22() {
        assertEquals(4, Max.max(3, 2, 1, 4));
    }
}