package ru.job4j.oop;

public class Battery {
    private int load;

    public Battery() {
        load = 0;
    }

    public Battery(int power) {
        load = power;
    }

    public void exchange(Battery another) {
        another.load += this.load;
        this.load = 0;
    }

    public static void main(String[] args) {
        Battery first = new Battery(100);
        Battery second = new Battery(50);
        System.out.println("first=" + first.load + " second=" + second.load);
        first.exchange(second);
        System.out.println("first=" + first.load + " second=" + second.load);
    }
}
