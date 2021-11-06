package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListCalcFunctionDiapasonTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        ListCalcFunctionDiapason function = new ListCalcFunctionDiapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuaddroFunctionThenQuaddroResults() {
        ListCalcFunctionDiapason function = new ListCalcFunctionDiapason();
        List<Double> result = function.diapason(5, 8, x -> 2 * x * x - 3 * x + 1);
        List<Double> expected = Arrays.asList(36D, 55D, 78D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponFunctionThenExponResults() {
        ListCalcFunctionDiapason function = new ListCalcFunctionDiapason();
        List<Double> result = function.diapason(2, 5, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(4D, 8D, 16D);
        assertThat(result, is(expected));
    }
}