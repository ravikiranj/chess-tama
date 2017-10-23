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
public class Player
{
    private List<Piece> pieces;
    private List<Card> cards;
    private Card upcomingCard;
    private PlayerType playerType;

    public Player(List<Card> cards, Card upcomingCard, PlayerType playerType)
    {
        this.pieces = new ArrayList<>();
        this.cards = cards;
        this.upcomingCard = upcomingCard;
        this.playerType = playerType;

        initPieces();
    }

    private void initPieces()
    {
        int row = playerType == P1 ? Board.MIN_ROWS : Board.MAX_ROWS;
        // Add master
        this.pieces.add(new Master(this, new Position(row, MASTER_COL)));

        // Add students
        for (int j = 1; j<= 5; j++)
        {
            if (j == 3)
            {
                continue;
            }

            this.pieces.add(new Student(this, new Position(row, j)));
        }
    }

    public List<Piece> getPieces()
    {
        return pieces;
    }

    public void removePiece(Piece p)
    {
        boolean status = this.pieces.remove(p);
        if (!status)
        {
            throw new IllegalStateException("Unable to find piece " + p);
        }
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public Card getUpcomingCard()
    {
        return upcomingCard;
    }

    public PlayerType getPlayerType()
    {
        return playerType;
    }

    public enum PlayerType
    {
        P1,
        P2;
    }
}
