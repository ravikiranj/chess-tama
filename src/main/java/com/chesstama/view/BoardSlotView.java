package com.chesstama.view;

import com.chesstama.model.Piece;
import com.chesstama.model.Slot;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * BoardSlotView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardSlotView extends StackPane {

    private int row;
    private int col;
    private Slot slot;

    public BoardSlotView(int row, int col, Slot slot) {
        this.row = row;
        this.col = col;
        this.slot = slot;

        String labelString = "(" + row + ", " + col + ") - " + slot.getPiece().map(Piece::getShortName).orElse("EMP");
        getChildren().addAll(new Label(labelString));
        getStyleClass().add(CSS.SQUARE.getName());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Slot getSlot() {
        return slot;
    }
}
