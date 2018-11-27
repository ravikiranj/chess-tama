package com.chesstama.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("PMD.AtLeastOneConstructor")
public class NoOpClickHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(final MouseEvent event) {
        // NO-OP
    }
}
