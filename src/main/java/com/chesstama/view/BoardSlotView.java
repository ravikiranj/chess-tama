package com.chesstama.view;

import com.chesstama.handlers.BoardSlotViewClickHandler;
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

    private final Slot slot;
    private final GameView gameView;
    private final Label slotLabel;
    private boolean isHighlighted;

    public BoardSlotView(final Slot slot, final GameView gameView) {
        this(slot, gameView, EventHandlerConfig.ENABLED);
    }

    public BoardSlotView(final Slot slot, final GameView gameView, final EventHandlerConfig eventHandlerConfig) {
        super();

        this.slot = slot;
        this.gameView = gameView;
        this.slotLabel = new Label(slot.toString());
        this.isHighlighted = false;

        getChildren().addAll(slotLabel);
        getStyleClass().add(CSS.BOARD_SQUARE.getName());

        if (eventHandlerConfig == EventHandlerConfig.ENABLED) {
            setEventHandlers();
        }
    }

    public void updateView() {
        this.slotLabel.setText(slot.toString());
    }

    public Slot getSlot() {
        return slot;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void highlightSlot() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_SQUARE.getName());
        isHighlighted = true;
    }

    public void unhighlightSlot() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_SQUARE.getName());
        isHighlighted = false;
    }

    public void highlightPieceSlot() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_PIECE_SQUARE.getName());
    }

    public void unhighlightPieceSlot() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_PIECE_SQUARE.getName());
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(new BoardSlotViewClickHandler(this));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Slot = %s", this.slot.toString()));
        sb.append(String.format(", isHighlighted = %s", this.isHighlighted));
        return sb.toString();
    }

    public enum EventHandlerConfig {
        ENABLED,
        DISABLED
    }
}
