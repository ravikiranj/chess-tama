package com.chesstama;

import com.chesstama.view.GameView;
import javafx.application.Application;

/**
 * GameController - ChessTama launcher class
 *
 * TODO List
 * ==========
 * 1) Change pawn notation to icon
 * 2) Refactor to ensure X-View contains X-Model only (example: GameView has Game model, BoardView has Board model)
 * 3) Add captured piece list
 * 4) Add game AI
 *
 */
public class GameController {

    public static void main(final String[] args) {
        Application.launch(GameView.class, args);
    }
}
