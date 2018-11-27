package com.chesstama.handlers;

import com.chesstama.model.Card;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.util.GameUtil;
import com.chesstama.view.BoardSlotView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BoardSlotViewClickHandler implements EventHandler<MouseEvent> {
    private final BoardSlotView boardSlotView;

    public BoardSlotViewClickHandler(final BoardSlotView boardSlotView) {
        this.boardSlotView = boardSlotView;
    }

    @Override
    public void handle(final MouseEvent event) {
        log.info("BoardSlotView clicked = {}", this.boardSlotView);
        if (!this.boardSlotView.getSlot().getPiece().isPresent()) {
            handlePieceMovement();
            return;
        }

        // Only allow selection of current player pieces
        PlayerType pieceOfPlayer = boardSlotView.getSlot().getPiece().get().getPlayer().getPlayerType();
        if (pieceOfPlayer != boardSlotView.getGameView().getCurrentPlayerTurn()) {
            return;
        }

        this.boardSlotView.getGameView().getBoardView().clearAllBoardSlotViews();
        this.boardSlotView.highlightSlot();
        this.boardSlotView.highlightPieceSlot();
        this.boardSlotView.getGameView().setCurrentSelectedPiece(this.boardSlotView.getSlot().getPiece().get());
        GameUtil.highlightValidMoves(this.boardSlotView, this.boardSlotView.getGameView());
    }

    private void handlePieceMovement() {
        Optional<Piece> currentSelectedPieceOptional = this.boardSlotView.getGameView().getCurrentSelectedPiece();
        if (!currentSelectedPieceOptional.isPresent()) {
            return;
        }

        // Piece currentSelectedPiece = currentSelectedPieceOptional.get();
        Piece currentSelectedPiece = currentSelectedPieceOptional.get();
        Card currentSelectedCard = this.boardSlotView.getGameView().getCurrentSelectedCard();
        Position proposedMovePosition = this.boardSlotView.getSlot().getPosition();
        PlayerType currentPlayerTurn = this.boardSlotView.getGameView().getCurrentPlayerTurn();
        Position currentSelectedPiecePosition = currentSelectedPiece.getPosition();

        if (!GameUtil.isValidMove(currentSelectedPiecePosition, proposedMovePosition,
            currentSelectedCard, currentPlayerTurn, boardSlotView.getGameView().getBoardView())) {
            log.info("Invalid move, not possible to move");
            return;
        }

        // Change piece position
        currentSelectedPiece.setPosition(proposedMovePosition);

        // Change board slots
        boardSlotView.getGameView().getBoardView().setBoardSlotView(currentSelectedPiecePosition, null);
        boardSlotView.getGameView().getBoardView().setBoardSlotView(proposedMovePosition, currentSelectedPiece);

        // Update board view
        this.boardSlotView.getGameView().getGame().getBoard().setSelectedPiece(Optional.ofNullable(null));
        boardSlotView.getGameView().getBoardView().updateView();
        boardSlotView.getGameView().getBoardView().clearAllBoardSlotViews();

        // TODO: capture logic
        // check if new space has opponent pawns - run capture logic
        // capture logic
        //  (a) Check  opponent master, if yes, declare winner
        //  (b) Add student to removed pieces list

        // Update player turn
        PlayerType nextPlayerTurn = boardSlotView.getGameView().getGame().getBoard().togglePlayerTurn();
        boardSlotView.getGameView().updateCurrentPlayerLabel();

        // Update main and upcoming cards
        updateCards(nextPlayerTurn, currentSelectedCard);
    }

    private void updateCards(final PlayerType nextTurnPlayer, final Card prevTurnPlayerCard) {
        Player p1 = boardSlotView.getGameView().getGame().getBoard().getPlayer1();
        Player p2 = boardSlotView.getGameView().getGame().getBoard().getPlayer2();

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
