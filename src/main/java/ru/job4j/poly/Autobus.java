package ru.job4j.poly;

public class Autobus implements Vehicle {

    @Override
    public void move() {
        System.out.println(whoIAm() + " move to road.");
    }

    @Override
    public String whoIAm() {
        return "Bus";
    }
}
