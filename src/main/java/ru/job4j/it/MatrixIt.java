package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
         int newColumn = column;
         int newRow = row;
         while (newColumn >= data[newRow].length) {
             newColumn -= data[newRow].length;
             newRow++;
             if (newRow == data.length) {
                 return false;
             }
         }
         return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (column >= data[row].length) {
            column -= data[row].length;
            row++;
        }
        return data[row][column++];
    }
}
