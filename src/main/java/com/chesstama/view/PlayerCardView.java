package com.chesstama.view;

import com.chesstama.model.Board;
import com.chesstama.model.Card;
import com.chesstama.model.Card.CardColor;
import com.chesstama.model.Player;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import com.chesstama.view.BoardSlotView.EventHandlerConfig;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Set;

/**
 * PlayerCardView
 */
public class PlayerCardView extends GridPane {

    private final Player currentPlayer;
    private final Card card;

    public PlayerCardView(final Player player, final Card card) {
        super();

        this.currentPlayer = player;
        this.card = card;
        Set<Position> validPositions = card != null ? card.getAbsoluteValidPositions() : Collections.emptySet();

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Position currentPosition = new Position(row, col);

                BoardSlotView boardSlotView = new BoardSlotView(new Slot(row, col), EventHandlerConfig.DISABLED);
                boardSlotView.getStyleClass().add(getCssClass(card, currentPosition, validPositions));
                GridPane.setConstraints(boardSlotView, col - 1, row - 1);
                this.getChildren().add(boardSlotView);
            }
            if (card != null) {
                Label cardLabel = new Label(card.name());
                GridPane.setConstraints(cardLabel, Board.MASTER_COL - 1, Board.MAX_ROWS);
                this.getChildren().add(cardLabel);
            }
        }
        this.getStyleClass().add(CSS.PLAYER_CARD.getName());
    }

    private String getCssClass(Card card, Position currentPosition, Set<Position> validPositions) {
        if (card == null) {
            return StringUtils.EMPTY;
        }

        if (currentPosition.equals(Board.MASTER_POSITION)) {
            return CSS.BLACK_SQUARE.getName();
        }

        if (validPositions.contains(currentPosition)) {
            return card.getCardColor() == CardColor.BLUE ? CSS.BLUE_SQUARE.getName() : CSS.RED_SQUARE.getName();
        }

        return StringUtils.EMPTY;
    }

    private String getPlayerCardText() {
        return new StringBuilder()
            .append(currentPlayer.getPlayerType().name())
            .append(" card")
            .append(", type = ")
            .append(card != null ? card.name() : "NULL")
            .toString();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getCard() {
        return card;
    }
}
