package com.chesstama.model;

import java.util.List;

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

    public Player(List<Piece> pieces, List<Card> cards, Card upcomingCard)
    {
        this.pieces = pieces;
        this.cards = cards;
        this.upcomingCard = upcomingCard;
    }
}
