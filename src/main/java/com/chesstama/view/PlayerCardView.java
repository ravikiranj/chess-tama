package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * PlayerCardView
 */
public class PlayerCardView extends StackPane {

    private Player currentPlayer;
    private Card card;

    public PlayerCardView(Player player, Card card) {
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
