package ru.job4j.poly;

public class Airplan implements Vehicle {
    @Override
    public void move() {
        System.out.println(whoIAm() + " a fly.");
    }

    @Override
    public String whoIAm() {
        return "Airplan";
    }
}
