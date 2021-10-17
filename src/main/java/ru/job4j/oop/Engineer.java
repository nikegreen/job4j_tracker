package ru.job4j.oop;

public class Engineer extends Profession {
    private String diplom;

    public Engineer(String name,
                    String surname,
                    String education,
                    String birthday,
                    String diplom) {
        super(name, surname, education, birthday);
        this.diplom = diplom;
    }

    public String getDiplom() {
        return diplom;
    }

    public Documentation getDocumentation(EnjineTask work) {
        return (Documentation) null;
    }
}
