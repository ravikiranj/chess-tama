package com.chesstama;

import com.chesstama.view.GameView;
import javafx.application.Application;

/**
 * GameController - ChessTama launcher class
 */
public class GameController {

    public static void main(final String[] args) {
        Application.launch(GameView.class, args);
    }
}
