package com.chesstama.view;

public enum CSS {
    GAMEROW("gamerow"),
    SQUARE("square");

    private final String cssClassName;

    CSS(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getName() {
        return this.cssClassName;
    }
}
