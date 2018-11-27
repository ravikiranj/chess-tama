package com.chesstama.model;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public enum GameMoveStatus {
    PLAYER1_MOVED("Player 1 Moved"),
    PLAYER2_MOVED("Player 2 Moved"),
    PLAYER1_CAPTURED("Player 1 Captured"),
    PLAYER2_CAPTURED("Player 2 Captured"),
    PLAYER1_WINS_BY_OPPONENT_MASTER_PIECE_CAPTURE("Player 1 Wins by capturing opponent Master piece"),
    PLAYER2_WINS_BY_OPPONENT_MASTER_PIECE_CAPTURE("Player 2 Wins by capturing opponent Master piece"),
    PLAYER1_WINS_BY_OPPONENT_MASTER_SQUARE_CAPTURE("Player 1 Wins by capturing opponent Master square"),
    PLAYER2_WINS_BY_OPPONENT_MASTER_SQUARE_CAPTURE("Player 2 Wins by capturing opponent Master square");

    public static final Set<GameMoveStatus> GAME_END_STATUSES = new ImmutableSet.Builder()
        .add(PLAYER1_WINS_BY_OPPONENT_MASTER_PIECE_CAPTURE)
        .add(PLAYER2_WINS_BY_OPPONENT_MASTER_PIECE_CAPTURE)
        .add(PLAYER1_WINS_BY_OPPONENT_MASTER_SQUARE_CAPTURE)
        .add(PLAYER2_WINS_BY_OPPONENT_MASTER_SQUARE_CAPTURE)
        .build();

    private final String statusString;

    GameMoveStatus(final String s) {
        this.statusString = s;
    }

    public String getStatusString() {
        return statusString;
    }
}
