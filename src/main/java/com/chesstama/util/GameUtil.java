package com.chesstama.util;

import com.chesstama.model.Card;
import com.chesstama.model.Piece;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.model.Position;
import com.chesstama.model.Slot;
import com.chesstama.view.BoardSlotView;
import com.chesstama.view.BoardView;
import com.chesstama.view.GameView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * GameUtil
 *
 * @author ravikiranj
 * @since Aug 2017
 */
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
        Slot slot = boardSlotView.getSlot();
        Piece piece = slot.getPiece().get();
        boolean isPlayer2Piece = piece.getPlayer().getPlayerType() == PlayerType.P2;
        Position currentPosition = new Position(slot.getRow(), slot.getCol());
        for (Position p : card.getValidPositions()) {
            Position newPosition;
            if (isPlayer2Piece) {
                newPosition = currentPosition.add(p);
            } else {
                newPosition = currentPosition.add(p.negate());
            }
            if (!newPosition.isValid()) {
                continue;
            }
            BoardSlotView validBoardSlotView = boardView.getBoardSlotViews()[newPosition.getRow()][newPosition.getCol()];
            validBoardSlotView.highlightSlot();
        }
    }
}
