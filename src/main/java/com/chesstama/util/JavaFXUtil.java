package com.chesstama.util;

import javafx.scene.Node;

import java.util.List;

public class JavaFXUtil {
    public static void toggleCSSClass(final Node node, final String className) {
        List<String> styleClasses = node.getStyleClass();
        if (styleClasses.contains(className)) {
            styleClasses.remove(className);
        } else {
            styleClasses.add(className);
        }
    }
}
