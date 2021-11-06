package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
    public Map<String, Student> collect(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(
                        Collectors.toMap(s -> s.getSurname(), s -> s)
                );
    }

    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student(70, "Petrov"),
                new Student(10, "Sidorov"),
                new Student(70, "Petrov"),
                new Student(0,  "Ivanov"),
                new Student(40, "Kuznetsov")
        );
        Map<String, Student> map = new ListToMap().collect(list);
        System.out.println(map);
    }
}