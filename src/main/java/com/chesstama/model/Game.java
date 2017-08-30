package com.chesstama.model;

/**
 * Game
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Game
{
    private Board board;
    private Player p1;
    private Player p2;

    public Game()
    {
        board = new Board(p1, p2);
    }

    public void printState()
    {
        board.printBoard();
    }
}
