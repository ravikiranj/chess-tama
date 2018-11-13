package com.chesstama.view;

import com.chesstama.model.Board;
import com.chesstama.model.Game;
import com.chesstama.model.Slot;
import javafx.scene.layout.GridPane;

/**
 * BoardView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardView {

    private final GridPane boardGridPane;

    @SuppressWarnings("PMD.UnusedPrivateField")
    private final BoardSlotView[][] boardSlotViews;

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public BoardView(final Game game) {
        boardSlotViews = new BoardSlotView[Board.MAX_ROWS + 1][Board.MAX_COLS + 1];
        Board board = game.getBoard();

        boardGridPane = new GridPane();
        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Slot slot = board.getSlot(row, col);
                BoardSlotView boardSlotView = new BoardSlotView(slot);
                boardSlotViews[row][col] = boardSlotView;
                GridPane.setConstraints(boardSlotView, col - 1, row - 1);
                boardGridPane.getChildren().add(boardSlotView);
            }
        }

        GridPane.setConstraints(boardGridPane, 1, 0, 2, 1);
    }

    public GridPane getBoardGridPane() {
        return boardGridPane;
    }
}
