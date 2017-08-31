package com.chesstama.model;

/**
 * Master
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Master extends Piece
{
    public Master(Player player, Position position)
    {
        this(PieceType.MASTER, player, position);
    }

    public Master(PieceType pieceType, Player player, Position position)
    {
        super(pieceType, player, position);
    }
}
