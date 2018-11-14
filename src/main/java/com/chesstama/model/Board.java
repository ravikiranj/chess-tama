package com.chesstama.model;

import lombok.extern.slf4j.Slf4j;

/**
 * Board
 *
 * @author rjanardhana
 * @since Aug 2017
 */
@Slf4j
public class Board {

    public static final int MIN_ROWS = 1;
    public static final int MAX_ROWS = 5;
    public static final int MIN_COLS = 1;
    public static final int MAX_COLS = 5;
    public static final int MASTER_COL = 3;
    public static final int MASTER_ROW = 3;

    public static final Position MASTER_POSITION = new Position(MASTER_ROW, MASTER_COL);

    private final Slot[][] gameBoard;
    private final Player p1;
    private final Player p2;

    public Board(final Player p1, final Player p2) {
        // We will make the gameBoard a 1-index based grid in order to simplify checks
        this.gameBoard = new Slot[MAX_ROWS + 1][MAX_COLS + 1];
        this.p1 = p1;
        this.p2 = p2;

        initBoard();

    }

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    private void initBoard() {
        for (int i = 1; i <= MAX_ROWS; i++) {
            for (int j = 1; j <= MAX_COLS; j++) {
                gameBoard[i][j] = new Slot(i, j);
            }
        }

        // Position p1 and p2 pieces
        placePlayerPieces(p1);
        placePlayerPieces(p2);
    }

    private void placePlayerPieces(final Player p) {
        for (Piece piece : p.getPieces()) {
            Position pos = piece.getPosition();
            gameBoard[pos.getRow()][pos.getCol()].setPiece(piece);
        }
    }

    public Slot getSlot(final int row, final int col) {
        if (row < 1 || row > MAX_ROWS || col < 1 || col > MAX_COLS) {
            throw new IllegalArgumentException("row/col out of bounds, row = " + row + ", col = " + col);

        }
        return gameBoard[row][col];
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }
}
