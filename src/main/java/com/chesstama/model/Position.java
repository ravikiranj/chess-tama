package com.chesstama.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Position
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Position {

    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    public Position add(final Position p) {
        int newRow = row + p.row;
        int newCol = col + p.col;
        return new Position(newRow, newCol);
    }

    public Position negate() {
        int newRow = row * -1;
        int newCol = col * -1;
        return new Position(newRow, newCol);
    }

    public boolean isValid() {
        return row >= Board.MIN_ROWS && row <= Board.MAX_ROWS &&
               col >= Board.MIN_COLS && col <= Board.MAX_COLS;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position that = (Position) o;

        return Objects.equals(this.col, that.col) &&
            Objects.equals(this.row, that.row);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
            .add("row = " + row)
            .add("col = " + col)
            .toString();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
