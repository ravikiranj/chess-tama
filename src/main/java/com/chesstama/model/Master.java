package com.chesstama.model;

/**
 * Master
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Master extends Piece {

    public Master(final Player player, final Position position) {
        this(PieceType.MASTER, player, position);
    }

    public Master(final PieceType pieceType, final Player player, final Position position) {
        super(pieceType, player, position);
    }
}
