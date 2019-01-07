package com.chesstama.handlers;

import com.chesstama.model.Board;
import com.chesstama.model.Card;
import com.chesstama.model.GameMoveStatus;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.util.GameUtil;
import com.chesstama.view.BestMoveView;
import com.chesstama.view.BoardSlotView;
import com.chesstama.view.BoardView;
import com.chesstama.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BoardSlotViewClickHandler implements EventHandler<MouseEvent> {
    private final BoardSlotView boardSlotView;
    private final GameView gameView;

    public BoardSlotViewClickHandler(final BoardSlotView boardSlotView) {
        this.boardSlotView = boardSlotView;
        gameView = boardSlotView.getGameView();
    }

    @Override
    public void handle(final MouseEvent event) {
        log.info("BoardSlotView clicked = {}", boardSlotView);

        // Target square is empty, handle piece movement if there is a current piece selected
        if (!boardSlotView.getSlot().getPiece().isPresent()) {
            handlePieceMovement();
            return;
        }

        // Don't allow selection of opponent piece when there is no current player piece selected
        Optional<Piece> currentSelectedPieceOptional = gameView.getCurrentSelectedPiece();
        PlayerType proposedMovePlayerPiece = boardSlotView.getSlot().getPiece().get().getPlayer().getPlayerType();
        if (proposedMovePlayerPiece != gameView.getCurrentPlayerTurn() && !currentSelectedPieceOptional.isPresent()) {
            return;
        }

        // If current player piece is selected and target square has opponent piece, handle movement
        if (currentSelectedPieceOptional.isPresent() &&
            currentSelectedPieceOptional.get().getPlayer().getPlayerType() != proposedMovePlayerPiece) {
            handlePieceMovement();
            return;
        }

        BoardView boardView = gameView.getBoardView();
        boardView.clearAllBoardSlotViews();
        boardSlotView.highlightSlot();
        boardSlotView.highlightPieceSlot();
        gameView.setCurrentSelectedPiece(boardSlotView.getSlot().getPiece().get());
        GameUtil.highlightValidMoves(boardSlotView, gameView);
    }

    private void handlePieceMovement() {
        Optional<Piece> currentSelectedPieceOptional = gameView.getCurrentSelectedPiece();
        if (!currentSelectedPieceOptional.isPresent()) {
            return;
        }

        BoardView boardView = gameView.getBoardView();

        // Piece currentSelectedPiece = currentSelectedPieceOptional.get();
        Piece currentSelectedPiece = currentSelectedPieceOptional.get();
        Card currentSelectedCard = gameView.getCurrentSelectedCard();
        Position proposedMovePosition = boardSlotView.getSlot().getPosition();
        PlayerType currentPlayerTurn = gameView.getCurrentPlayerTurn();
        Position currentSelectedPiecePosition = currentSelectedPiece.getPosition();

        if (!GameUtil.isValidMove(currentSelectedPiecePosition, proposedMovePosition,
            currentSelectedCard, currentPlayerTurn, boardView)) {
            log.info("Invalid move, not possible to move");
            return;
        }

        // Capture Logic
        GameMoveStatus gameMoveStatus = runCaptureLogic(currentSelectedPiece, currentPlayerTurn, proposedMovePosition);
        log.info("Player Turn = {}, GameMoveStatus = {}", currentPlayerTurn, gameMoveStatus);

        // Change piece position
        currentSelectedPiece.setPosition(proposedMovePosition);

        // Change board slots
        boardView.setBoardSlotView(currentSelectedPiecePosition, null);
        boardView.setBoardSlotView(proposedMovePosition, currentSelectedPiece);

        // Update board view
        Board board = gameView.getGame().getBoard();
        board.setSelectedPiece(Optional.ofNullable(null));
        boardView.updateView();
        boardView.clearAllBoardSlotViews();

        if (GameMoveStatus.GAME_END_STATUSES.contains(gameMoveStatus)) {
            triggerGameEnd(gameMoveStatus);
            return;
        }

        // Update player turn
        PlayerType nextPlayerTurn = board.togglePlayerTurn();
        gameView.updateCurrentPlayerLabel();

        // Update main and upcoming cards
        updateCards(nextPlayerTurn, currentSelectedCard);

        if (nextPlayerTurn == PlayerType.P2) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> new BestMoveView(gameView).run()));
            timeline.play();
        }
    }

    private void triggerGameEnd(final GameMoveStatus gameMoveStatus) {
        gameView.disableAllHandlers();
        gameView.updateCurrentPlayerLabel(gameMoveStatus.getStatusString());

        showGameEndAlert(gameMoveStatus);
    }

    private void showGameEndAlert(final GameMoveStatus gameMoveStatus) {
        Alert gameEndAlert = new Alert(AlertType.INFORMATION);
        gameEndAlert.setTitle(GameView.CHESS_TAMA);
        gameEndAlert.setHeaderText(null);
        gameEndAlert.setContentText(gameMoveStatus.getStatusString());
        gameEndAlert.show();
    }

    private GameMoveStatus runCaptureLogic(final Piece currentPlayerPiece,
                                           final PlayerType currentPlayerTurn,
                                           final Position proposedMovePosition) {
        BoardView boardView = gameView.getBoardView();

        BoardSlotView boardSlotView = boardView.getBoardSlotView(proposedMovePosition);
        Optional<Piece> proposedMoveSlotPieceOptional = boardSlotView.getSlot().getPiece();
        Position opponentKingHome = currentPlayerTurn == PlayerType.P1 ?
            new Position(Board.MIN_ROWS, Board.KING_COL) : new Position(Board.MAX_ROWS, Board.KING_COL);

        if (!proposedMoveSlotPieceOptional.isPresent()) {
            if (currentPlayerPiece.isKing() && proposedMovePosition.equals(opponentKingHome)) {
                return currentPlayerTurn == PlayerType.P1 ?
                    GameMoveStatus.PLAYER1_WINS_BY_OPPONENT_KING_SQUARE_CAPTURE :
                    GameMoveStatus.PLAYER2_WINS_BY_OPPONENT_KING_SQUARE_CAPTURE;
            }
            return currentPlayerTurn == PlayerType.P1 ? GameMoveStatus.PLAYER1_MOVED : GameMoveStatus.PLAYER2_MOVED;
        }

        Board board = gameView.getGame().getBoard();
        Piece proposedMoveSlotPiece = proposedMoveSlotPieceOptional.get();
        Player pieceWinner = currentPlayerTurn == PlayerType.P1 ? board.getPlayer1() : board.getPlayer2();
        Player pieceLoser = currentPlayerTurn == PlayerType.P1 ? board.getPlayer2() : board.getPlayer1();
        log.info("{} captured {}'s piece = {}", pieceWinner.getPlayerType(), pieceLoser.getPlayerType(),
            proposedMoveSlotPiece.getShortName());

        pieceWinner.getCapturedPieces().add(proposedMoveSlotPiece);
        pieceLoser.getRemovedPieces().add(proposedMoveSlotPiece);

        if (proposedMoveSlotPiece.isKing()) {
            return currentPlayerTurn == PlayerType.P1 ?
                GameMoveStatus.PLAYER1_WINS_BY_OPPONENT_KING_PIECE_CAPTURE :
                GameMoveStatus.PLAYER2_WINS_BY_OPPONENT_KING_PIECE_CAPTURE;
        } else {
            if (currentPlayerPiece.isKing() && proposedMovePosition.equals(opponentKingHome)) {
                return currentPlayerTurn == PlayerType.P1 ?
                    GameMoveStatus.PLAYER1_WINS_BY_OPPONENT_KING_SQUARE_CAPTURE :
                    GameMoveStatus.PLAYER2_WINS_BY_OPPONENT_KING_SQUARE_CAPTURE;
            }
            return currentPlayerTurn == PlayerType.P1 ? GameMoveStatus.PLAYER1_CAPTURED :
                GameMoveStatus.PLAYER2_CAPTURED;
        }
    }

    private void updateCards(final PlayerType nextTurnPlayer, final Card prevTurnPlayerCard) {
        Board board = gameView.getGame().getBoard();

        Player p1 = board.getPlayer1();
        Player p2 = board.getPlayer2();

        if (nextTurnPlayer == PlayerType.P2) {
            updatePlayerCards(p2, p1, prevTurnPlayerCard);
        } else {
            updatePlayerCards(p1, p2, prevTurnPlayerCard);
        }
        gameView.updatePlayerCardViews();
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
