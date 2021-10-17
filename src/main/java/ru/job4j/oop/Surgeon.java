package ru.job4j.oop;

public class Surgeon extends Doctor {
    private int skill;

    public Surgeon(String name,
                   String surname,
                   String education,
                   String birthday,
                   String specialization,
                   int skill) {
        super(name, surname, education, birthday, specialization);
        this.skill = skill;
    }

    public int getSkill() {
        return skill;
    }

    public void surgery(People sick) {

    }
}
