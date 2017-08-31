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
        Player p1 = new Player(cardsForGame.subList(0, 2), cardsForGame.get(2), Player.PlayerType.P1);
        Player p2 = new Player(cardsForGame.subList(3, 5), null, Player.PlayerType.P2);

        board = new Board(p1, p2);
    }

    public void printState()
    {
        board.printBoard();
    }
}
