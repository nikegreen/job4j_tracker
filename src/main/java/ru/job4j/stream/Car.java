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

    public Date getManufactured() {
        return manufactured;
    }

    public Date getByDate() {
        return byDate;
    }

    public int getSeat() {
        return seat;
    }

    public int getWeight() {
        return weight;
    }

    public double getFuel() {
        return fuel;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "{name=" + this.name
                + ",number=" + this.gosNumber
                + ",seat=" + this.seat
                + ",weight=" + this.weight
                + ",manufactured=" + this.manufactured
                + ",by=" + this.byDate
                + ",fuel=" + this.fuel + "}";
    }

    static class Builder {
        private String gosNumber;
        private Date manufactured;
        private Date byDate;
        private int seat;
        private int weight;
        private double fuel;
        private String name;

        public Builder buildGosNumber(String gosNumber) {
            this.gosNumber = gosNumber;
            return this;
        }

        public Builder buildManufactured(Date manufactured) {
            this.manufactured = manufactured;
            return this;
        }

        public Builder buildByDate(Date byDate) {
            this.byDate = byDate;
            return this;
        }

        public Builder buildSeat(int seat) {
            this.seat = seat;
            return this;
        }

        public Builder buidWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder buidFuel(double fuel) {
            this.fuel = fuel;
            return this;
        }

        public Builder buldName(String name) {
            this.name = name;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.byDate = this.byDate;
            car.manufactured = this.manufactured;
            car.fuel = this.fuel;
            car.gosNumber = this.gosNumber;
            car.name = this.name;
            car.seat = this.seat;
            car.weight = this.weight;
            return car;
        }
    }

    public static void main(String[] args) {
        Car car = new Builder()
                .buidFuel(45.0)
                .buidWeight(1296)
                .buildByDate(new Date(2004, 10, 17))
                .buildSeat(5)
                .buildManufactured(new Date(2003, 11, 4))
                .buildGosNumber("n999au99")
                .buldName("Toyota RAV4")
                .build();
        System.out.println(car);
    }
}
