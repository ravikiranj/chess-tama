package com.chesstama.model;

/**
 * Board
 *
 * @author rjanardhana
 * @since Aug 2017
 */
public class Board {

    public static final int MIN_ROWS = 1;
    public static final int MAX_ROWS = 5;
    public static final int MAX_COLS = 5;
    public static final int MASTER_COL = 3;

    private final Slot[][] board;
    private final Player p1;
    private final Player p2;

    public Board(Player p1, Player p2) {
        // We will make the board a 1-index based grid in order to simplify checks
        this.board = new Slot[MAX_ROWS + 1][MAX_COLS + 1];
        this.p1 = p1;
        this.p2 = p2;

        initBoard();

    }

    private void initBoard() {
        for (int i = 1; i <= MAX_ROWS; i++) {
            for (int j = 1; j <= MAX_COLS; j++) {
                board[i][j] = new Slot();
            }
        }

        // Position p1 and p2 pieces
        placePlayerPieces(p1);
        placePlayerPieces(p2);
    }

    private void placePlayerPieces(Player p) {
        for (Piece piece : p.getPieces()) {
            Position pos = piece.getPosition();
            board[pos.getRow()][pos.getCol()].setPiece(piece);
        }
    }

    public Slot getSlot(int row, int col) {
        if (row < 1 || row > MAX_ROWS || col < 1 || col > MAX_COLS) {
            throw new IllegalArgumentException("row/col out of bounds, row = " + row + ", col = " + col);

        }
        return board[row][col];
    }

    private void printBoard() {
        for (int i = MAX_ROWS; i >= MIN_ROWS; i--) {
            for (int j = 1; j <= MAX_COLS; j++) {
                System.out.print(board[i][j]);
                if (j != MAX_COLS) {
                    System.out.print("\t");
                } else {
                    System.out.println();
                }
            }
        }
    }

    public void printBoardState() {
        System.out.println("==============");
        System.out.println("P2");
        System.out.println("==============");

        printPlayerCards(this.p2, Player.PlayerType.P2);

        System.out.println("==============");
        System.out.println("Board");
        System.out.println("==============");
        printBoard();

        System.out.println("==============");
        printPlayerCards(this.p1, Player.PlayerType.P1);
        System.out.println("==============");
        System.out.println("P1");
        System.out.println("==============");
    }

    private void printPlayerCards(Player p, Player.PlayerType playerType) {
        System.out.println(playerType + " Cards");
        System.out.println("==============");
        for (Card c : p.getCards()) {
            c.printCard();
        }

        System.out.println(playerType + " Upcoming Card");
        System.out.println("==============");
        if (p.getUpcomingCard() != null) {
            p.getUpcomingCard().printCard();
        } else {
            System.out.println("EMPTY UPCOMING CARD");
        }
        System.out.println("==============");
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }
}
