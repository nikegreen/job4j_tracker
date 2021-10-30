package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void calcMinus1() {
        Fact factorial = new Fact();
        factorial.calc(-1);
    }

    @Test
    public void calc1() {
        Fact factorial = new Fact();
        assertEquals(1, factorial.calc(1));
    }

    @Test
    public void calc2() {
        Fact factorial = new Fact();
        assertEquals(2, factorial.calc(2));
    }
}