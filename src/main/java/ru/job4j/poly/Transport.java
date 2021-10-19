package ru.job4j.poly;

import java.util.Currency;

public interface Transport {
    void move();

    void setPassenger(int number);

    int charge(int litre);
}
