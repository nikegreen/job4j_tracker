package ru.job4j.poly;

public class Train implements Vehicle {

    @Override
    public void move() {
        System.out.println(whoIAm() + " move to Railway");
    }

    @Override
    public String whoIAm() {
        return "Train";
    }
}
