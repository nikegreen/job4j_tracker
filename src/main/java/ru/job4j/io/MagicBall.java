package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        System.out.print("Я великий Оракул. Что ты хочешь узнать? ");
        Scanner input = new Scanner(System.in);
        String question = input.nextLine();
        int answer = new Random().nextInt(3);
        String res = switch (answer) {
            case 0 -> "да";
            case 1 -> "нет";
            default -> "может быть";
        };
        System.out.println(question + " - " + res + ".");
    }
}
