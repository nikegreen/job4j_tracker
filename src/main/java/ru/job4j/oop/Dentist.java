package ru.job4j.oop;

public class Dentist extends Doctor {
    private int speed;

    public Dentist(String name,
                   String surname,
                   String education,
                   String birthday,
                   String specialization,
                   int speed) {
        super(name, surname, education, birthday, specialization);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void makeDentalProsthetics(People sick) {

    }
}
