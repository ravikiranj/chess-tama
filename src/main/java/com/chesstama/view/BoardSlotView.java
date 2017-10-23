package com.chesstama.view;

import com.chesstama.model.Piece;
import com.chesstama.model.Slot;
import javafx.scene.control.Button;

/**
 * BoardSlotView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardSlotView extends Button
{
    private int row;
    private int col;
    private Slot slot;

    public BoardSlotView(int row, int col, Slot slot)
    {
        super("(" + row + ", " + col + ") - " + slot.getPiece().map(Piece::getShortName).orElse("EMP"));
        this.slot = slot;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Slot getSlot()
    {
        return slot;
    }
}
