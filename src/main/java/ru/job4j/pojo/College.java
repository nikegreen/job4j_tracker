package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setFio("Petrov I.V.");
        student1.setGroup("AT21");
        student1.setStart(new Date());
        System.out.println(student1.getFio());
        System.out.println(student1.getGroup());
        System.out.println(student1.getStart());
    }
}
