package com.chesstama.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class EmptyStackPane extends StackPane {

    public EmptyStackPane() {
        Label emptyLabel = new Label("EMPTY");
        getChildren().add(emptyLabel);
        getStyleClass().add(CSS.SQUARE.getName());
    }
}
