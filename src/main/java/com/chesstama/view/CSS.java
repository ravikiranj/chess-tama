package com.chesstama.view;

public enum CSS {
    GAMEROW("gamerow"),
    SQUARE("square"),
    CARD_SQUARE("card-square"),
    HIGHLIGHTED_CARD("highlighted-card"),
    HIGHLIGHTED_SQUARE("highlighted-square"),
    HIGHLIGHTED_PIECE_SQUARE("highlighted-piece-square"),
    GREY_SQUARE("grey-square"),
    BLUE_SQUARE("blue-square"),
    RED_SQUARE("red-square"),
    PLAYER_CARD("player-card"),
    PLAYER_CARD_HOLDER("player-card-holder"),
    GAMEBOARD("gameboard"),
    CURRENT_PLAYER_TURN("current-player-turn"),
    PLAYER_LABEL("player-label"),
    UP_ARROW_LABEL("up-arrow-label");

    private final String cssClassName;

    CSS(final String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getName() {
        return this.cssClassName;
    }
}
