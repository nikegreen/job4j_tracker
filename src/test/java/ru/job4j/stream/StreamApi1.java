package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, -3, 4, -1, 2);
        List<Integer> out = list.stream().filter(i -> i >= 0).collect(Collectors.toList());
        System.out.println(out);
    }
}
