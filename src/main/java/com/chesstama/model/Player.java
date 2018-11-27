package com.chesstama.model;

import java.util.ArrayList;
import java.util.List;

import static com.chesstama.model.Board.MASTER_COL;
import static com.chesstama.model.Player.PlayerType.P1;

/**
 * Player
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Player {

    private final List<Piece> pieces;
    private final List<Piece> removedPieces;
    private final List<Piece> capturedPieces;
    private final List<Card> cards;
    private Card upcomingCard;
    private final PlayerType playerType;

    public Player(final List<Card> cards, final Card upcomingCard, final PlayerType playerType) {
        this.pieces = new ArrayList<>();
        this.removedPieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        this.cards = cards;
        this.upcomingCard = upcomingCard;
        this.playerType = playerType;

        initPieces();
    }

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    private void initPieces() {
        int row = playerType == P1 ? Board.MAX_ROWS : Board.MIN_ROWS;
        // Add master
        this.pieces.add(new Master(this, new Position(row, MASTER_COL)));

        // Add students
        for (int j = Board.MIN_COLS; j <= Board.MAX_COLS; j++) {
            if (j == MASTER_COL) {
                continue;
            }

            this.pieces.add(new Student(this, new Position(row, j)));
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Piece> getRemovedPieces() {
        return removedPieces;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public void removePiece(final Piece p) {
        boolean status = this.pieces.remove(p);
        if (!status) {
            throw new IllegalStateException("Unable to find piece " + p);
        }
        this.removedPieces.add(p);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getUpcomingCard() {
        return upcomingCard;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setUpcomingCard(final Card upcomingCard) {
        this.upcomingCard = upcomingCard;
    }

    public enum PlayerType {
        P1,
        P2
    }
}
