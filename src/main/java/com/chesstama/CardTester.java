package com.chesstama;

import com.chesstama.model.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardTester {

    @SuppressWarnings("PMD.SystemPrintln")
    public static void main(final String[] args) {
        for (Card c : Card.values()) {
            log.info("Card = {}", c);
            c.printForPlayer1();
            System.out.println();
        }
    }

}
