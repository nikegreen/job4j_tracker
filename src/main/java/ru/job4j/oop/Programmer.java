package ru.job4j.oop;

public class Programmer extends Engineer {
    private String lang;

    public Programmer(String name,
                      String surname,
                      String education,
                      String birthday,
                      String diplom,
                      String lang) {
        super(name, surname, education, birthday, diplom);
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void doProgramming(EnjineTask work) {

    }
}
