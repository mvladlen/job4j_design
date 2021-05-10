package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = -1;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return hasNextColumn() || hasNextRow();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][++column];
    }

    private boolean hasNextColumn() {
        return data[row].length > column + 1;
    }

    private boolean hasNextRow() {
        column = -1;
        while (data.length > ++row) {
            if (hasNextColumn()) {
                return true;
            }
        }
        return false;
    }
}
