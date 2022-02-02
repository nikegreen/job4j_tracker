package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            lines = in.lines()
                    .filter(value -> {
                        boolean res = false;
                        String[] str = value.split(" ");
                        if (str.length > 1) {
                            res = "404".equals(str[str.length - 2]);
                        }
                        return res;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}