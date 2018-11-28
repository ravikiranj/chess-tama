package com.chesstama.view;

import com.chesstama.handlers.NoOpClickHandler;
import com.chesstama.handlers.PlayerCardViewClickHandler;
import com.chesstama.model.Board;
import com.chesstama.model.Card;
import com.chesstama.model.Card.CardColor;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Set;

/**
 * PlayerCardView
 */
@Slf4j
public class PlayerCardView extends VBox {

    private PlayerType cardOfPlayer;
    private Card card;
    private CardSlot cardSlot;
    private final GameView gameView;
    private final BoardSlotView[][] boardSlotViews;
    private final Label cardLabel;

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public PlayerCardView(final PlayerType cardOfPlayer, final Card card, final CardSlot cardSlot, final GameView gameView) {
        super();

        this.gameView = gameView;
        this.cardOfPlayer = cardOfPlayer;
        this.cardSlot = cardSlot;
        this.card = card;
        this.boardSlotViews = new BoardSlotView[Board.MAX_ROWS+1][Board.MAX_COLS+1];

        Set<Position> validPositions = card != null ? card.getAbsoluteValidPositions(cardOfPlayer) :
            Collections.emptySet();
        GridPane cardGridPane = new GridPane();

        if (cardOfPlayer == PlayerType.P1) {
            Label upArrowLabel = new Label(Card.UP_ARROW_STRING);
            upArrowLabel.getStyleClass().add(CSS.ARROW_LABEL.getName());
            upArrowLabel.getStyleClass().add(CSS.CARD_SQUARE.getName());
            GridPane.setConstraints(upArrowLabel, Board.KING_COL, 0);
            cardGridPane.getChildren().add(upArrowLabel);
        }

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                int modRow = cardOfPlayer == PlayerType.P1 ? row : Board.MAX_ROWS - row + 1;
                int modCol = cardOfPlayer == PlayerType.P1 ? col : Board.MAX_COLS - col + 1;
                Position currentPosition = new Position(modRow, modCol);

                BoardSlotView boardSlotView = new BoardSlotView(new Slot(modRow, modCol), gameView,
                    BoardSlotView.BoardSlotType.PLAYER_CARD_SLOT);
                boardSlotView.getStyleClass().add(CSS.CARD_SQUARE.getName());
                boardSlotView.getStyleClass().add(getCssClass(card, currentPosition, validPositions));
                boardSlotViews[modRow][modCol] = boardSlotView;

                GridPane.setConstraints(boardSlotView, modCol, modRow);
                cardGridPane.getChildren().add(boardSlotView);
            }
        }

        if (cardOfPlayer == PlayerType.P1) {
            cardGridPane.getStyleClass().add(CSS.P1_PLAYER_CARD.getName());
        } else {
            cardGridPane.getStyleClass().add(CSS.P2_PLAYER_CARD.getName());
        }

        this.getChildren().add(cardGridPane);

        if (cardOfPlayer == PlayerType.P2) {
            Label downArrowLabel = new Label(Card.DOWN_ARROW_STRING);
            downArrowLabel.getStyleClass().add(CSS.ARROW_LABEL.getName());
            downArrowLabel.getStyleClass().add(CSS.CARD_SQUARE.getName());
            GridPane.setConstraints(downArrowLabel, Board.KING_COL, Board.MAX_ROWS + 1);
            cardGridPane.getChildren().add(downArrowLabel);
        }

        cardLabel = new Label(getCardLabelStr());
        GridPane.setConstraints(cardLabel, Board.KING_COL - 1, Board.MAX_ROWS);
        this.getChildren().add(cardLabel);

        this.getStyleClass().add(CSS.PLAYER_CARD_HOLDER.getName());
        setEventHandlers();

    }

    private String getCardLabelStr() {
        return card != null ? card.name() : "";
    }

    public PlayerType getCardOfPlayer() {
        return cardOfPlayer;
    }


    public CardSlot getCardSlot() {
        return cardSlot;
    }


    public GameView getGameView() {
        return gameView;
    }

    public void updateCard(final Card card) {
        this.card = card;
    }

    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public void updateView() {
        Set<Position> validPositions = card != null ? card.getAbsoluteValidPositions(cardOfPlayer) :
            Collections.emptySet();

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Position currentPosition = new Position(row, col);
                BoardSlotView boardSlotView = boardSlotViews[row][col];
                boardSlotView.getStyleClass().clear();
                boardSlotView.getStyleClass().add(CSS.CARD_SQUARE.getName());
                boardSlotView.getStyleClass().add(getCssClass(card, currentPosition, validPositions));
            }
        }

        cardLabel.setText(getCardLabelStr());
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(new PlayerCardViewClickHandler(this));
    }

    public void disableEventHandlers() {
        this.setOnMouseClicked(new NoOpClickHandler());
    }

    @Override
    public String toString() {
        return "PlayerCardView{" +
            "cardOfPlayer=" + cardOfPlayer +
            ", card=" + card +
            '}';
    }

    public void selectCard() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_CARD.getName());
    }

    public void unselectCard() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_CARD.getName());
    }

    private String getCssClass(final Card card, final Position currentPosition, final Set<Position> validPositions) {
        if (card == null) {
            return CSS.EMPTY_SQUARE.getName();
        }

        if (currentPosition.equals(Board.KING_POSITION)) {
            return CSS.GREY_SQUARE.getName();
        }

        if (validPositions.contains(currentPosition)) {
            return card.getCardColor() == CardColor.BLUE ? CSS.BLUE_SQUARE.getName() : CSS.RED_SQUARE.getName();
        }

        return CSS.EMPTY_SQUARE.getName();
    }

    public Card getCard() {
        return card;
    }

    public enum CardSlot {
        MAIN,
        NEXT
    }
}
