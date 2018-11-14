package com.chesstama.view;

import com.chesstama.model.Slot;
import com.chesstama.util.JavaFXUtil;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardSlotView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardSlotView extends StackPane {

    private final Slot slot;
    private final Label slotLabel;
    private boolean isHighlighted;

    public BoardSlotView(final Slot slot) {
        this(slot, EventHandlerConfig.ENABLED);
    }

    public BoardSlotView(final Slot slot, final EventHandlerConfig eventHandlerConfig) {
        super();

        this.slot = slot;
        this.slotLabel = new Label(slot.toString());
        this.isHighlighted = false;

        getChildren().addAll(slotLabel);
        getStyleClass().add(CSS.SQUARE.getName());

        if (eventHandlerConfig == EventHandlerConfig.ENABLED) {
            setEventHandlers();
        }
    }

    public void toggleHighlighted() {
        if (isHighlighted) {
            isHighlighted = false;
        } else {
            isHighlighted = true;
        }
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(new BoardSlotViewClickHandler(this));
    }

    @Slf4j
    private static class BoardSlotViewClickHandler implements javafx.event.EventHandler<MouseEvent> {
        private final BoardSlotView boardSlotView;

        public BoardSlotViewClickHandler(final BoardSlotView boardSlotView) {
            this.boardSlotView = boardSlotView;
        }

        @Override
        public void handle(final MouseEvent event) {
            this.boardSlotView.toggleHighlighted();
            JavaFXUtil.toggleCSSClass(this.boardSlotView, CSS.HIGHLIGHTED.getName());
            log.info("BoardSlotView clicked = {}", this.boardSlotView);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Slot = %s", this.slot.toString()));
        sb.append(String.format(", isHighlighted = %s", this.isHighlighted));
        return sb.toString();
    }

    public static enum EventHandlerConfig {
        ENABLED,
        DISABLED;
    }
}
