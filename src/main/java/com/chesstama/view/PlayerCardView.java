package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Player;
import javafx.scene.control.Button;

/**
 * PlayerCardView
 */
public class PlayerCardView {

    private Button playerCardButton;
    private Player currentPlayer;
    private Card card;

    public PlayerCardView(Player player, Card card) {
        this.currentPlayer = player;
        this.card = card;
        this.playerCardButton = new Button(getPlayerCardText());
    }

    private String getPlayerCardText() {
        return new StringBuilder()
            .append(currentPlayer.getPlayerType().name())
            .append(" card")
            .append(", type = ")
            .append(card != null ? card.name() : "NULL")
            .toString();
    }

    public Button getPlayerCardButton() {
        return playerCardButton;
    }

    public void setPlayerCardButton(Button playerCardButton) {
        this.playerCardButton = playerCardButton;
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
