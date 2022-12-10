package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {

    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        List<Card> cards = Stream.of(Suit.values())
                        .flatMap(
                                suit1 -> Stream.of(Value.values())
                                .map(value1 -> new Card(suit1, value1))
                        )
                .collect(Collectors.toList());
        cards.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "{" + suit + "," + value + "}";
    }
}
