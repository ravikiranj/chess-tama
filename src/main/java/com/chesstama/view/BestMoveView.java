package com.chesstama.view;

import com.chesstama.backend.eval.MiniMaxWithAlphaBeta;
import com.chesstama.backend.eval.Move;
import com.chesstama.backend.eval.Score;
import com.chesstama.backend.eval.ScoreMoves;
import com.chesstama.model.Position;
import com.chesstama.util.GameUtil;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BestMoveView implements Runnable {
    private static final int MAX_DEPTH = 1;
    private final GameView gameView;

    public BestMoveView(final GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        List<Move> movePath = new ArrayList<>();
        ScoreMoves alpha = new ScoreMoves(Score.MIN_SCORE, new ArrayList<>());
        ScoreMoves beta = new ScoreMoves(Score.MAX_SCORE, new ArrayList<>());

        ScoreMoves bestScoreMoves = MiniMaxWithAlphaBeta.getBestMove(GameUtil.getBoard(gameView), alpha, beta,
            MAX_DEPTH, true, movePath);

        log.info("Leaf nodes evaluated = {}", MiniMaxWithAlphaBeta.LEAF_NODES_EVALUATED);
        log.info("BestScoreMoves = {}", bestScoreMoves);
        if (bestScoreMoves.getMoves().isEmpty()) {
            throw new RuntimeException("BestMoves is empty");
        }

        Move move = bestScoreMoves.getMoves().get(0);

        Position from = transform(move.getFrom());
        Position to = transform(move.getTo());

        log.info("Best Move for P2 = {}", bestScoreMoves.getMoves().get(0));

        BoardSlotView fromBoardSlotView = gameView.getBoardView().getBoardSlotView(from);
        BoardSlotView toBoardSlotView = gameView.getBoardView().getBoardSlotView(to);
        PlayerCardView playerCardView = gameView.getPlayerCardView(move.getCard());

        log.info("Clicking playerCardView = {}", playerCardView);
        playerCardView.fireEvent(getMouseClickEvent());

        log.info("Clicking fromBoardSlotView = {}", fromBoardSlotView);
        fromBoardSlotView.fireEvent(getMouseClickEvent());

        log.info("Clicking toBoardSlotView = {}", toBoardSlotView);
        toBoardSlotView.fireEvent(getMouseClickEvent());
    }

    private MouseEvent getMouseClickEvent() {
        return new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1,
            true, true, true, true, true, true, true, true, true, true, null);
    }

    private static Position transform(final com.chesstama.backend.engine.Position position) {
        return new Position(position.getRow() + 1, position.getCol() + 1);
    }
}
