package com.chesstama.model;

import java.util.Optional;

/**
 * Slot
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Slot {

    private Optional<Piece> piece;

    public Slot() {
        this.piece = Optional.empty();
    }

    public Optional<Piece> getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = Optional.of(piece);
    }

    @Override
    public String toString() {
        if (!piece.isPresent()) {
            return "EMPTY";
        }
        Piece p = piece.get();
        StringBuilder sb = new StringBuilder();
        sb.append(p.player.getPlayerType());
        sb.append("-");
        sb.append(p.pieceType.getShortName());

        return sb.toString();
    }
}
