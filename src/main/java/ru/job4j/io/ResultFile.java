package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            final int SIZE = 9;
            final byte[] LN = System.lineSeparator().getBytes();
            int[][] matrix = Matrix.multiple(SIZE);
            out.write("{".getBytes());
            out.write(LN);
            for (int row = 0; row < SIZE; row++) {
                out.write(" {".getBytes());
                for (int col = 0; col < SIZE; col++) {
                    int elem = matrix[row][col];
                    if (col > 0) {
                        out.write(",".getBytes());
                    }
                    if (elem < 10) {
                        out.write(" ".getBytes());
                    }
                    out.write(Integer.toString(elem).getBytes());
                }
                out.write("}".getBytes());
                if (row < SIZE - 1) {
                    out.write(",".getBytes());
                }
                out.write(LN);
            }
            out.write("}".getBytes());
            out.write(LN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}