package com.chesstama.util;

import com.chesstama.model.Card;
import com.chesstama.model.Piece;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import com.chesstama.view.BoardSlotView;
import com.chesstama.view.BoardView;
import com.chesstama.view.GameView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * GameUtil
 *
 * @author ravikiranj
 * @since Aug 2017
 */
@Slf4j
public class GameUtil {

    public static <T> List<T> getRandomSubList(final List<T> input, final int subsetSize) {
        Random r = new Random();

        // Copy list and operate on that
        List<T> copyList = input.stream().collect(Collectors.toList());
        int inputSize = copyList.size();
        for (int i = 0; i < subsetSize; i++) {
            int indexToSwap = i + r.nextInt(inputSize - i);
            T temp = copyList.get(i);
            copyList.set(i, copyList.get(indexToSwap));
            copyList.set(indexToSwap, temp);
        }

        return copyList.subList(0, subsetSize);
    }

    public static void highlightValidMoves(final BoardSlotView boardSlotView, final GameView gameView) {
        Card card = gameView.getCurrentSelectedCard();
        if (card == null) {
            return;
        }

        BoardView boardView = gameView.getBoardView();
        PlayerType currentPlayerTurn = gameView.getCurrentPlayerTurn();
        Slot slot = boardSlotView.getSlot();
        Piece piece = slot.getPiece().get();
        boolean isPlayer2Piece = piece.getPlayer().getPlayerType() == PlayerType.P2;
        Position currentPosition = slot.getPosition();
        for (Position p : card.getValidPositions()) {
            Position newPosition;
            if (isPlayer2Piece) {
                newPosition = currentPosition.add(p.negate());
            } else {
                newPosition = currentPosition.add(p);
            }

            if (!newPosition.isValid()) {
                log.info("For pos = {}, NewPos = {} is invalid", p, newPosition);
                continue;
            }


            boolean hasSamePlayerPiece = doesPositionHaveCurrentPlayerPiece(currentPlayerTurn, boardView, newPosition);
            if (hasSamePlayerPiece) {
                log.info("For pos = {}, NewPos = {} is invalid as it has same player piece", p, newPosition);
                continue;
            }

            log.info("For pos = {}, NewPos = {} is valid", p, newPosition);

            BoardSlotView validBoardSlotView = boardView.getBoardSlotViews()[newPosition.getRow()][newPosition.getCol()];
            validBoardSlotView.highlightSlot();
        }
    }

    public static boolean isValidMove(final Position currentSelectedPiecePosition,
                                      final Position proposedMovePosition,
                                      final Card currentSelectedCard,
                                      final PlayerType currentPlayerTurn,
                                      final BoardView boardView) {

        boolean isPlayer2Piece = currentPlayerTurn == PlayerType.P2;
        for (Position p : currentSelectedCard.getValidPositions()) {
            Position newPosition;
            if (isPlayer2Piece) {
                newPosition = currentSelectedPiecePosition.add(p.negate());
            } else {
                newPosition = currentSelectedPiecePosition.add(p);
            }

            if (!newPosition.isValid()) {
                log.info("CurrentPos = {}, cardMove = {}, newPosition = {} is invalid",
                    currentSelectedPiecePosition, p, newPosition);
                continue;
            }

            boolean hasSamePlayerPiece = doesPositionHaveCurrentPlayerPiece(currentPlayerTurn, boardView, newPosition);
            log.info("CurrentPos = {}, cardMove = {}, newPosition = {}, hasSamePlayerPiece = {}",
                currentSelectedPiecePosition, p, newPosition, hasSamePlayerPiece);

            if (newPosition.isValid() && newPosition.equals(proposedMovePosition) && !hasSamePlayerPiece) {
                return true;
            }
        }

        return false;
    }

    private static boolean doesPositionHaveCurrentPlayerPiece(final PlayerType currentPlayerTurn,
                                                              final BoardView boardView,
                                                              final Position position) {
        BoardSlotView boardSlotForNewPos = boardView.getBoardSlotView(position);
        Optional<Piece> pieceOptional = boardSlotForNewPos.getSlot().getPiece();
        return pieceOptional.isPresent() &&
            pieceOptional.get().getPlayer().getPlayerType() == currentPlayerTurn;
    }
}
