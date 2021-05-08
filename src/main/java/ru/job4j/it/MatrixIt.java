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
        try {
            if (findNextColumn() >= 0) {
                return true;
            }
        } catch (NoSuchElementException ec) {
            try {
                if (findNextRow() >= 0) {
                    return true;
                }
            } catch (NoSuchElementException er) {
                return false;
            }
        }
        return false;

    }

    @Override
    public Integer next() {
/*
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
*/
        try {
            this.column = findNextColumn();
        } catch (NoSuchElementException ec) {
            this.row = findNextRow();
            this.column = 0;
        }
        return data[this.row][this.column];
    }

    private int findNextColumn() throws NoSuchElementException {
        if (data[row].length < column + 2) {
            throw new NoSuchElementException();
        }
        return column + 1;
    }

    private int findNextRow() throws NoSuchElementException {
        int row = this.row;
        while (data.length > row + 1) {
            row++;
            if (data[row].length > 0) {
                return row;
            }
        }
        throw new NoSuchElementException();
    }
}
