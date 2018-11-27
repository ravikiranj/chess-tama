package com.chesstama;

import com.chesstama.model.Card;

public class CardTester {

    @SuppressWarnings("PMD.SystemPrintln")
    public static void main(final String[] args) {
        Card.COBRA.printForPlayer1();
        System.out.println();
        Card.COBRA.printForPlayer2();
    }

}
