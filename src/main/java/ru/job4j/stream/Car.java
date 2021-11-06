package ru.job4j.stream;

import java.util.Date;
import java.util.Objects;

public class Car {
    private String gosNumber;
    private Date manufactured;
    private Date byDate;
    private int seat;
    private int weight;
    private double fuel;
    private String name;

    public Car() {

    }

    public String getGosNumber() {
        return gosNumber;
    }

    public void setGosNumber(String gosNumber) {
        this.gosNumber = gosNumber;
    }

    public Date getManufactured() {
        return manufactured;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public Date getByDate() {
        return byDate;
    }

    public void setByDate(Date byDate) {
        this.byDate = byDate;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return getSeat() == car.getSeat()
                && getWeight() == car.getWeight()
                && Double.compare(car.getFuel(), getFuel()) == 0
                && getGosNumber().equals(car.getGosNumber())
                && getManufactured().equals(car.getManufactured())
                && getByDate().equals(car.getByDate())
                && getName().equals(car.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getGosNumber(),
                getManufactured(),
                getByDate(),
                getSeat(),
                getWeight(),
                getFuel(),
                getName()
        );
    }
}
