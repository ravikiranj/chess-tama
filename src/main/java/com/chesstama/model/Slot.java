package com.chesstama.model;

/**
 * Slot
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Slot
{
    private Piece piece;

    public Slot()
    {
        this.piece = null;
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }
}
