package com.chesstama.model;

import com.chesstama.util.GameUtil;

import java.util.List;

/**
 * Game
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Game
{
    private static final int NUM_GAME_CARDS = 5;

    private Board board;
    private Player p1;
    private Player p2;

    public Game()
    {
        List<Card> cardsForGame = GameUtil.getRandomSubList(Card.ALL_CARDS, NUM_GAME_CARDS);

        this.p1 = new Player(cardsForGame.subList(0, 2), cardsForGame.get(2), Player.PlayerType.P1);
        this.p2 = new Player(cardsForGame.subList(3, 5), null, Player.PlayerType.P2);
        this.board = new Board(p1, p2);
    }

    public void printState()
    {
        System.out.println("==============");
        System.out.println("P2");
        System.out.println("==============");

        printPlayerCards(p2, Player.PlayerType.P2);

        System.out.println("==============");
        System.out.println("Board");
        System.out.println("==============");
        board.printBoard();


        System.out.println("==============");
        printPlayerCards(p1, Player.PlayerType.P1);
        System.out.println("==============");
        System.out.println("P1");
        System.out.println("==============");
    }

    private void printPlayerCards(Player p, Player.PlayerType playerType)
    {
        System.out.println(playerType + " Cards");
        System.out.println("==============");
        for (Card c : p.getCards())
        {
            c.printCard();
        }

        System.out.println(playerType + " Upcoming Card");
        System.out.println("==============");
        if (p.getUpcomingCard() != null)
        {
            p.getUpcomingCard().printCard();
        }
        else
        {
            System.out.println("EMPTY UPCOMING CARD");
        }
        System.out.println("==============");
    }

    public Board getBoard()
    {
        return board;
    }

    public Player getP1()
    {
        return p1;
    }

    public Player getP2()
    {
        return p2;
    }
}
