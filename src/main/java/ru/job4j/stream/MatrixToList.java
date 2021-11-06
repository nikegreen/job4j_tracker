package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {
    public static List<Integer> convert(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(v -> Stream.of(v)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Integer[][] matrix = new Integer[][]{
                new Integer[] {1, 2},
                new Integer[] {3, 4, 5, 6},
                new Integer[] {7, 8}
        };
        Stream<Integer[]> mi = Stream.of(matrix);
        Stream<Integer> s = mi.flatMap(v -> Stream.of(v));
        List<Integer> list = s.collect(Collectors.toList());
        list.stream().forEach((str) -> System.out.println(str));
        List<Integer> res = convert(matrix);
        res.stream().forEach((str) -> System.out.println(str));
    }
}
