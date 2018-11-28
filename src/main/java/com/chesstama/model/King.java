package com.chesstama.model;

/**
 * King
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class King extends Piece {

    public King(final Player player, final Position position) {
        this(PieceType.KING, player, position);
    }

    public King(final PieceType pieceType, final Player player, final Position position) {
        super(pieceType, player, position);
    }
}
