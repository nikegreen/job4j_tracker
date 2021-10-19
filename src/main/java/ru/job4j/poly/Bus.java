package ru.job4j.poly;

public class Bus implements Transport {
    private int tank;
    private int passenger;
    private final int price = 45;

    @Override
    public void move() {

    }

    @Override
    public void setPassenger(int number) {
        this.passenger = number;
    }

    @Override
    public int charge(int litre) {
        this.tank += litre;
        return litre * price;
    }
}
