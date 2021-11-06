package ru.job4j.stream;

import java.util.Date;

public class Builder {
    private  Car car = null;

    public Builder() {
        Car car = new Car();
    }

    public Builder buildGosNumber(String gosNumber) {
        car.setGosNumber(gosNumber);
        return this;
    }

    public Builder buildManufactured(Date manufactured) {
        car.setManufactured(manufactured);
        return this;
    }

    public Builder buildByDate(Date byDate) {
        car.setByDate(byDate);
        return this;
    }

    public Builder buildSeat(int seat) {
        car.setSeat(seat);
        return this;
    }

    public Builder buidWeight(int weight) {
        car.setWeight(weight);
        return this;
    }

    public Builder buidFuel(double fuel) {
        car.setFuel(fuel);
        return this;
    }

    public Builder buldName(String name) {
        car.setName(name);
        return this;
    }

    public Car build() {
        return car;
    }
}
