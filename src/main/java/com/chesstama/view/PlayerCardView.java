package com.chesstama.view;

import com.chesstama.model.Board;
import com.chesstama.model.Card;
import com.chesstama.model.Card.CardColor;
import com.chesstama.model.Piece;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import com.chesstama.util.GameUtil;
import com.chesstama.view.BoardSlotView.EventHandlerConfig;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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

        Set<Position> validPositions = card != null ? card.getAbsoluteValidPositions() : Collections.emptySet();

        GridPane cardGridPane = new GridPane();
        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Position currentPosition = new Position(row, col);

                BoardSlotView boardSlotView = new BoardSlotView(new Slot(row, col), gameView,
                    EventHandlerConfig.DISABLED);
                boardSlotView.getStyleClass().add(CSS.CARD_SQUARE.getName());
                boardSlotView.getStyleClass().add(getCssClass(card, currentPosition, validPositions));
                boardSlotViews[row][col] = boardSlotView;

                GridPane.setConstraints(boardSlotView, col - 1, row - 1);
                cardGridPane.getChildren().add(boardSlotView);
            }
        }

        cardGridPane.getStyleClass().add(CSS.PLAYER_CARD.getName());
        this.getChildren().add(cardGridPane);

        cardLabel = new Label(getCardLabelStr());
        GridPane.setConstraints(cardLabel, Board.MASTER_COL - 1, Board.MAX_ROWS);
        this.getChildren().add(cardLabel);

        this.getStyleClass().add(CSS.PLAYER_CARD_HOLDER.getName());
        setEventHandlers();

    }

    private String getCardLabelStr() {
        return card != null ? card.name() : "";
    }

    public void updateCard(Card card) {
        this.card = card;
    }

    public void updateView() {
        Set<Position> validPositions = card != null ? card.getAbsoluteValidPositions() : Collections.emptySet();

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Position currentPosition = new Position(row, col);
                BoardSlotView boardSlotView = boardSlotViews[row][col];
                boardSlotView.getStyleClass().clear();
                boardSlotView.getStyleClass().add(CSS.SQUARE.getName());
                boardSlotView.getStyleClass().add(CSS.CARD_SQUARE.getName());
                boardSlotView.getStyleClass().add(getCssClass(card, currentPosition, validPositions));
            }
        }

        cardLabel.setText(getCardLabelStr());
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(new PlayerCardViewClickHandler(this));
    }

    @Override
    public String toString() {
        return "PlayerCardView{" +
            "cardOfPlayer=" + cardOfPlayer +
            ", card=" + card +
            '}';
    }

    @Slf4j
    private static class PlayerCardViewClickHandler implements javafx.event.EventHandler<MouseEvent> {
        private final PlayerCardView playerCardView;

        public PlayerCardViewClickHandler(final PlayerCardView playerCardView) {
            this.playerCardView = playerCardView;
        }

        @Override
        public void handle(final MouseEvent event) {
            if (this.playerCardView.card == null) {
                return;
            }

            GameView gameView = this.playerCardView.gameView;
            PlayerType currPlayerTurn = gameView.getCurrentPlayerTurn();

            if (currPlayerTurn != this.playerCardView.cardOfPlayer ||
                this.playerCardView.cardSlot != CardSlot.MAIN) {
                log.info("Player Card isn't main or not of current player type, playerCardView = {} ", playerCardView);
                return;
            }

            gameView.unselectAllPlayerCards();
            gameView.getBoardView().clearAllBoardSlotViews();
            gameView.updateSelectedCard(this.playerCardView.card);
            this.playerCardView.selectCard();
            if (this.playerCardView.gameView.getCurrentSelectedPiece().isPresent()) {
                updateValidPositions();
            }
            log.info("PlayerCardView clicked = {}", this.playerCardView);
        }

        private void updateValidPositions() {
            Piece currentSelectedPiece = this.playerCardView.gameView.getCurrentSelectedPiece().get();
            BoardSlotView boardSlotView = this.playerCardView.gameView
                .getBoardView().getBoardSlotView(currentSelectedPiece.getPosition());
            boardSlotView.highlightSlot();
            boardSlotView.highlightPieceSlot();
            GameUtil.highlightValidMoves(boardSlotView, boardSlotView.getGameView());
        }
    }

    public void selectCard() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_CARD.getName());
    }

    public void unselectCard() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_CARD.getName());
    }

    private String getCssClass(final Card card, final Position currentPosition, final Set<Position> validPositions) {
        if (card == null) {
            return StringUtils.EMPTY;
        }

        if (currentPosition.equals(Board.MASTER_POSITION)) {
            return CSS.GREY_SQUARE.getName();
        }

        if (validPositions.contains(currentPosition)) {
            return card.getCardColor() == CardColor.BLUE ? CSS.BLUE_SQUARE.getName() : CSS.RED_SQUARE.getName();
        }

        return StringUtils.EMPTY;
    }

    public Card getCard() {
        return card;
    }

    public enum CardSlot {
        MAIN,
        NEXT
    }
}
