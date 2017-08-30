package com.chesstama.model;

/**
 * Board
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Board
{
    private static final int MAX_ROWS = 5;
    private static final int MAX_COLS = 5;

    private Slot[][] board;
    private Player p1;
    private Player p2;

    public Board(Player p1, Player p2)
    {
        // We will make the board a 1-index based grid in order to simplify checks
        this.board = new Slot[MAX_ROWS+1][MAX_COLS+1];
        this.p1 = p1;
        this.p2 = p2;

        initBoard();

    }

    private void initBoard()
    {
        for (int i = 1; i <= MAX_ROWS; i++)
        {
            for (int j = 1; j <= MAX_COLS; j++)
            {
                board[i][j] = new Slot();
                // Player 1
                // Player 2
                // everything else
            }
        }
    }

    public void printBoard()
    {
        for (int i = 1; i <= MAX_ROWS; i++)
        {
            for (int j = 1; j <= MAX_COLS; j++)
            {
                System.out.print(board[i][j]);
                if (j != MAX_COLS)
                {
                    System.out.print("\t");
                }
                else
                {
                    System.out.println();
                }
            }
        }
    }
}
