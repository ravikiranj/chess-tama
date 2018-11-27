package com.chesstama.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Slot
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Slot {

    private final Position position;
    private Optional<Piece> piece;

    public Slot(final int row, final int col) {
        this.position = new Position(row, col);
        this.piece = Optional.empty();
    }

    public Optional<Piece> getPiece() {
        return piece;
    }

    public void setPiece(final Piece piece) {
        this.piece = Optional.ofNullable(piece);
    }

    @Override
    public String toString() {
        return piece.map(Piece::getShortName)
                    .orElse("");
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private String getPrettyStringWithPos() {
        String pieceName = piece.map(Piece::getShortName)
                                .orElse("");

        StringBuilder sb = new StringBuilder();
        sb.append('(').append(position.getRow()).append(", ").append(position.getCol()).append(')');

        if (StringUtils.isNotEmpty(pieceName)) {
            sb.append(" - ");
            sb.append(pieceName);
        }

        return sb.toString();
    }

    public Position getPosition() {
        return position;
    }
}
