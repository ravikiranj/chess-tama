package com.chesstama.model;

/**
 * Master
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Master extends Piece
{
    public Master(Position position, Player player)
    {
        this(PieceType.MASTER, position, player);
    }

    public Master(PieceType pieceType, Position position, Player player)
    {
        super(pieceType, position, player);
    }
}
