package ru.job4j.oop;

public class Builder extends Engineer {
    private String typeConstruct;

    public Builder(String name,
                   String surname,
                   String education,
                   String birthday,
                   String diplom,
                   String typeConstruct) {
        super(name, surname, education, birthday, diplom);
        this.typeConstruct = typeConstruct;
    }

    public String getTypeConstruct() {
        return typeConstruct;
    }

    public void doBuilding(EnjineTask work) {

    }
}
