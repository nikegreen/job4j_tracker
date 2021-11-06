package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.stream.MatrixToList.convert;

public class MatrixToListTest {

    @Test
    public void convertMatrix() {
        Integer[][] matrix = new Integer[][] {
                new Integer[] {1, 2},
                new Integer[] {3, 4, 5, 6},
                new Integer[] {7, 8}
        };
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> res = convert(matrix);
        assertThat(res, is(expected));
    }
}