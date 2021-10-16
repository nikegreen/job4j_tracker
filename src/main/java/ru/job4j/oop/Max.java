package ru.job4j.oop;

public class Max {
    public static int max(int left, int right) {
        return left > right ? left : right;
    }

    public static int max(int left, int middle, int right) {
        return max(max(left, middle), right);
    }

    public static int max(int left, int second, int third, int right) {
        return max(max(left, second), max(third, right));
    }
}
