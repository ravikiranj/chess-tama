package com.chesstama.view;

public enum CSS {
    GAMEROW("gamerow"),
    SQUARE("square"),
    HIGHLIGHTED("highlighted");

    private final String cssClassName;

    CSS(final String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getName() {
        return this.cssClassName;
    }
}
