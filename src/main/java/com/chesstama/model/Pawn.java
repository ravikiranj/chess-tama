package com.chesstama.model;

/**
 * Pawn
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Pawn extends Piece {

    public Pawn(final Player player, final Position position) {
        this(PieceType.PAWN, player, position);
    }

    public Pawn(final PieceType pieceType, final Player player, final Position position) {
        super(pieceType, player, position);
    }
}
