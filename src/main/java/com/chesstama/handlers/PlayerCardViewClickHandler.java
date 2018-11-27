package com.chesstama.handlers;

import com.chesstama.model.Piece;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.util.GameUtil;
import com.chesstama.view.BoardSlotView;
import com.chesstama.view.BoardView;
import com.chesstama.view.GameView;
import com.chesstama.view.PlayerCardView;
import com.chesstama.view.PlayerCardView.CardSlot;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerCardViewClickHandler implements EventHandler<MouseEvent> {
    private final PlayerCardView playerCardView;
    private final GameView gameView;

    public PlayerCardViewClickHandler(final PlayerCardView playerCardView) {
        this.playerCardView = playerCardView;
        gameView = playerCardView.getGameView();
    }

    @Override
    public void handle(final MouseEvent event) {
        if (this.playerCardView.getCard() == null) {
            return;
        }

        PlayerType currPlayerTurn = gameView.getCurrentPlayerTurn();

        if (currPlayerTurn != this.playerCardView.getCardOfPlayer() ||
            this.playerCardView.getCardSlot() != CardSlot.MAIN) {
            log.info("Player Card isn't main or not of current player type, playerCardView = {} ", playerCardView);
            return;
        }

        BoardView boardView = gameView.getBoardView();
        gameView.unselectAllPlayerCards();
        boardView.clearAllBoardSlotViews();
        gameView.updateSelectedCard(this.playerCardView.getCard());
        this.playerCardView.selectCard();
        if (this.playerCardView.getGameView().getCurrentSelectedPiece().isPresent()) {
            updateValidPositions();
        }
        log.info("PlayerCardView clicked = {}", this.playerCardView);
    }

    private void updateValidPositions() {
        Piece currentSelectedPiece = gameView.getCurrentSelectedPiece().get();
        BoardSlotView boardSlotView = gameView.getBoardView().getBoardSlotView(currentSelectedPiece.getPosition());
        boardSlotView.highlightSlot();
        boardSlotView.highlightPieceSlot();
        GameUtil.highlightValidMoves(boardSlotView, boardSlotView.getGameView());
    }
}
