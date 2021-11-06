package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        List<Card> cards = Arrays.asList(Suit.values()).stream()
                        .flatMap(
                                suit1 -> Stream.of(Value.values())
                                .map(value1 -> new Card(suit1, value1))
                        )
                .collect(Collectors.toList());
        cards.stream().forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "{" + suit + "," + value + "}";
    }
}
