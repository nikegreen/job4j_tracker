package ru.job4j.io;

public class Matrix {
    static int[][] multiple(int size) {
        int[][] m = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                m[row][col] = row * col;
            }
        }
        return m;
    }
}
