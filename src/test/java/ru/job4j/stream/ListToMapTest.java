package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListToMapTest {
    @Test
    public void whenMapClass() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(40, "Surname4"),
                new Student(50, "Surname5"),
                new Student(70, "Surname7"),
                new Student(90, "Surname9")
        );
        Map<String, Student> expected = new HashMap<>();
        expected.put(students.get(0).getSurname(), students.get(0));
        expected.put(students.get(1).getSurname(), students.get(1));
        expected.put(students.get(2).getSurname(), students.get(2));
        expected.put(students.get(3).getSurname(), students.get(3));
        expected.put(students.get(4).getSurname(), students.get(4));
        Map<String, Student> rsl = new ListToMap().collect(students);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenDublicateMapClass() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(40, "Surname4"),
                new Student(10, "Surname1"),
                new Student(50, "Surname5"),
                new Student(70, "Surname7"),
                new Student(90, "Surname9")
        );
        Map<String, Student> expected = new HashMap<>();
        expected.put(students.get(0).getSurname(), students.get(0));
        expected.put(students.get(1).getSurname(), students.get(1));
        expected.put(students.get(3).getSurname(), students.get(3));
        expected.put(students.get(4).getSurname(), students.get(4));
        expected.put(students.get(5).getSurname(), students.get(5));
        Map<String, Student> rsl = new ListToMap().collect(students);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenDublicateKeyMapClass() {
        List<Student> students = List.of(
                new Student(10, "Surname1"),
                new Student(40, "Surname4"),
                new Student(100, "Surname1"),
                new Student(50, "Surname5"),
                new Student(70, "Surname7"),
                new Student(90, "Surname9")
        );
        Map<String, Student> expected = new HashMap<>();
        expected.put(students.get(0).getSurname(), students.get(0));
        expected.put(students.get(1).getSurname(), students.get(1));
        expected.put(students.get(3).getSurname(), students.get(3));
        expected.put(students.get(4).getSurname(), students.get(4));
        expected.put(students.get(5).getSurname(), students.get(5));
        Map<String, Student> rsl = new ListToMap().collect(students);
        assertThat(rsl, is(expected));
    }
}