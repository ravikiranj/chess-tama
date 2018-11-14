package com.chesstama.view;

public enum CSS {
    GAMEROW("gamerow"),
    SQUARE("square"),
    HIGHLIGHTED("highlighted"),
    BLACK_SQUARE("black-square"),
    BLUE_SQUARE("blue-square"),
    RED_SQUARE("red-square"),
    PLAYER_CARD("player-card");

    private final String cssClassName;

    CSS(final String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getName() {
        return this.cssClassName;
    }
}
