package com.chesstama.view;

import com.chesstama.model.Board;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import javafx.scene.layout.GridPane;

/**
 * BoardView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardView extends GridPane {

    private final BoardSlotView[][] boardSlotViews;

    private final GameView gameView;

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public BoardView(final GameView gameView) {
        super();

        this.gameView = gameView;
        this.boardSlotViews = new BoardSlotView[Board.MAX_ROWS + 1][Board.MAX_COLS + 1];
        Board board = gameView.getGame().getBoard();

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Slot slot = board.getSlot(row, col);
                BoardSlotView boardSlotView = new BoardSlotView(slot, gameView);
                boardSlotViews[row][col] = boardSlotView;
                GridPane.setConstraints(boardSlotView, col - 1, row - 1);
                this.getChildren().add(boardSlotView);
            }
        }

        GridPane.setConstraints(this, 1, 0, 2, 1);
        this.getStyleClass().add(CSS.GAMEBOARD.getName());
    }

    @SuppressWarnings("PMD.MethodReturnsInternalArray")
    public BoardSlotView[][] getBoardSlotViews() {
        return boardSlotViews;
    }

    public void clearAllBoardSlotViews() {
        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                boardSlotViews[row][col].unhighlightSlot();
                boardSlotViews[row][col].unhighlightPieceSlot();
            }
        }
    }

    public BoardSlotView getBoardSlotView(final Position p) {
        return boardSlotViews[p.getRow()][p.getCol()];
    }

    public GameView getGameView() {
        return gameView;
    }
}
