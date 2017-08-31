package com.chesstama.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Position
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Position
{
    private int row;
    private int col;

    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Position that = (Position) o;

        return Objects.equals(this.col, that.col) &&
               Objects.equals(this.row, that.row);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(col, row);
    }

    @Override
    public String toString()
    {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("col = " + col)
                .add("row = " + row)
                .toString();
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }
}