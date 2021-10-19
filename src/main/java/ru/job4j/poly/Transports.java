package ru.job4j.poly;

public class Transports {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[] {new Airplan(),
                new Train(),
                new Autobus(),
                new Train(),
                new Autobus() };
        for (Vehicle current: vehicles) {
            current.move();
        }
    }
}
