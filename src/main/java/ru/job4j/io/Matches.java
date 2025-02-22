package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            int matches;
            int max = Math.min(3, count);
            System.out.println("Осталось спичек:" + count);
            do {
                System.out.println(player + " введите число от 1 до " + max + ":");
                matches = Integer.parseInt(input.nextLine());
            } while (matches < 1 || matches > max);
            turn = !turn;
            count -= matches;
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
