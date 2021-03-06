package com.chesstama.model;

import com.chesstama.model.Player.PlayerType;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Piece
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Piece {

    protected final PieceType pieceType;
    protected Position position;
    protected final Player player;

    public Piece(final PieceType pieceType, final Player player, final Position position) {
        this.pieceType = pieceType;
        this.player = player;
        this.position = position;
    }

    public boolean isKing() {
        return this.pieceType == PieceType.KING;
    }

    public String name() {
        return this.pieceType.getName();
    }

    public String shortName() {
        return this.pieceType.getShortName();
    }

    public Player getPlayer() {
        return this.player;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getShortName() {
        return player.getPlayerType().name() + " - " + pieceType.getShortName();
    }

    public String getBackendName() {
        String pieceName = isKing() ? "K" : "P";
        String playerName = player.getPlayerType() == PlayerType.P1 ? "1" : "2";
        return pieceName + playerName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, player, position);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Piece that = (Piece) o;

        return Objects.equals(this.pieceType, that.pieceType) &&
            Objects.equals(this.player, that.player) &&
            Objects.equals(this.position, that.position);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
            .add("pieceType = " + pieceType)
            .add("player = " + player)
            .add("position = " + position)
            .toString();
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    enum PieceType {
        KING("King", "K"),
        PAWN("Pawn", "P");

        private final String name;
        private final String shortName;

        PieceType(final String name, final String shortName) {
            this.name = name;
            this.shortName = shortName;
        }

        public String getName() {
            return name;
        }

        public String getShortName() {
            return shortName;
        }
    }
}
