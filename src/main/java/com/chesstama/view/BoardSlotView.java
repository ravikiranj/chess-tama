package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import com.chesstama.util.GameUtil;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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

    @Slf4j
    private static class BoardSlotViewClickHandler implements javafx.event.EventHandler<MouseEvent> {
        private final BoardSlotView boardSlotView;

        public BoardSlotViewClickHandler(final BoardSlotView boardSlotView) {
            this.boardSlotView = boardSlotView;
        }

        @Override
        public void handle(final MouseEvent event) {
            log.info("BoardSlotView clicked = {}", this.boardSlotView);
            if (!this.boardSlotView.slot.getPiece().isPresent()) {
                handlePieceMovement();
                return;
            }

            // Only allow selection of current player pieces
            PlayerType pieceOfPlayer = boardSlotView.slot.getPiece().get().getPlayer().getPlayerType();
            if (pieceOfPlayer != boardSlotView.gameView.getCurrentPlayerTurn()) {
                return;
            }

            this.boardSlotView.gameView.getBoardView().clearAllBoardSlotViews();
            this.boardSlotView.highlightSlot();
            this.boardSlotView.highlightPieceSlot();
            this.boardSlotView.gameView.setCurrentSelectedPiece(this.boardSlotView.slot.getPiece().get());
            GameUtil.highlightValidMoves(this.boardSlotView, this.boardSlotView.gameView);
        }

        private void handlePieceMovement() {
            Optional<Piece> currentSelectedPieceOptional = this.boardSlotView.gameView.getCurrentSelectedPiece();
            if (!currentSelectedPieceOptional.isPresent()) {
                return;
            }

            // Piece currentSelectedPiece = currentSelectedPieceOptional.get();
            Piece currentSelectedPiece = currentSelectedPieceOptional.get();
            Card currentSelectedCard = this.boardSlotView.gameView.getCurrentSelectedCard();
            Position proposedMovePosition = this.boardSlotView.slot.getPosition();
            PlayerType currentPlayerTurn = this.boardSlotView.gameView.getCurrentPlayerTurn();
            Position currentSelectedPiecePosition = currentSelectedPiece.getPosition();

            if (!GameUtil.isValidMove(currentSelectedPiecePosition, proposedMovePosition,
                currentSelectedCard, currentPlayerTurn, boardSlotView.getGameView().getBoardView())) {
                log.info("Invalid move, not possible to move");
                return;
            }

            // Change piece position
            currentSelectedPiece.setPosition(proposedMovePosition);

            // Change board slots
            boardSlotView.gameView.getBoardView().setBoardSlotView(currentSelectedPiecePosition, null);
            boardSlotView.gameView.getBoardView().setBoardSlotView(proposedMovePosition, currentSelectedPiece);

            // Update board view
            this.boardSlotView.gameView.getGame().getBoard().setSelectedPiece(Optional.ofNullable(null));
            boardSlotView.gameView.getBoardView().updateView();
            boardSlotView.gameView.getBoardView().clearAllBoardSlotViews();

            // TODO: capture logic
            // check if new space has opponent pawns - run capture logic
                // capture logic
                //  (a) Check  opponent master, if yes, declare winner
                //  (b) Add student to removed pieces list

            // Update player turn
            PlayerType nextPlayerTurn = boardSlotView.gameView.getGame().getBoard().togglePlayerTurn();
            boardSlotView.gameView.updateCurrentPlayerLabel();

            // Update main and upcoming cards
            updateCards(nextPlayerTurn, currentSelectedCard);
        }

        private void updateCards(final PlayerType nextTurnPlayer, final Card prevTurnPlayerCard) {
            Player p1 = boardSlotView.gameView.getGame().getBoard().getPlayer1();
            Player p2 = boardSlotView.gameView.getGame().getBoard().getPlayer2();

            if (nextTurnPlayer == PlayerType.P2) {
                updatePlayerCards(p2, p1, prevTurnPlayerCard);
            } else {
                updatePlayerCards(p1, p2, prevTurnPlayerCard);
            }
            boardSlotView.getGameView().updatePlayerCardViews();
        }

        private void updatePlayerCards(final Player nextTurnPlayer,
                                       final Player previousTurnPlayer,
                                       final Card prevTurnPlayedCard) {
            log.info("nextTurnPlayer = {}, previousTurnPlayer = {}, prevTurnPlayerCard = {}",
                nextTurnPlayer, previousTurnPlayer, prevTurnPlayedCard);
            List<Card> previousTurnPlayerCards = previousTurnPlayer.getCards();
            log.info("Previous Player cards = {}", previousTurnPlayerCards);
            previousTurnPlayerCards.remove(prevTurnPlayedCard);
            previousTurnPlayerCards.add(previousTurnPlayer.getUpcomingCard());
            previousTurnPlayer.setUpcomingCard(null);

            nextTurnPlayer.setUpcomingCard(prevTurnPlayedCard);
        }
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
