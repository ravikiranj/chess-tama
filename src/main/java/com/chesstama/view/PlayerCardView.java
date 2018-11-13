package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * PlayerCardView
 */
public class PlayerCardView extends StackPane {

    private final Player currentPlayer;
    private final Card card;

    public PlayerCardView(final Player player, final Card card) {
        super();

        this.currentPlayer = player;
        this.card = card;

        getChildren().addAll(new Label(getPlayerCardText()));
        getStyleClass().add("square");
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
