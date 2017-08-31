package com.chesstama.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Move
 *
 * @author ravikiranj
 * @since Aug 2017
 */
public class Move
{
    private int row;
    private int col;

    public Move(int row, int col)
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
        Move move = (Move) o;
        return row == move.row && col == move.col;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(row, col);
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
