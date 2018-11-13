package com.chesstama.model;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.chesstama.model.Card.CardColor.BLUE;
import static com.chesstama.model.Card.CardColor.RED;

/**
 * Card
 *
 * @author ravikiranj
 * @since Aug 2017
 */
public enum Card {
    DRAGON(RED, ImmutableSet.of(
        new Move(1, -2),
        new Move(1, 2),
        new Move(-1, -1),
        new Move(-1, 1)
    )),
    BOAR(RED, ImmutableSet.of(
        new Move(1, 0),
        new Move(0, -1),
        new Move(0, 1)
    )),
    MANTIS(RED, ImmutableSet.of(
        new Move(1, -1),
        new Move(1, 1),
        new Move(-1, 0)
    )),
    ELEPHANT(RED, ImmutableSet.of(
        new Move(0, -1),
        new Move(0, 1),
        new Move(1, -1),
        new Move(1, 1)
    )),
    ROOSTER(RED, ImmutableSet.of(
        new Move(0, -1),
        new Move(0, 1),
        new Move(-1, -1),
        new Move(1, 1)
    )),
    COBRA(RED, ImmutableSet.of(
        new Move(1, 1),
        new Move(-1, 1),
        new Move(0, -1)
    )),
    OX(RED, ImmutableSet.of(
        new Move(1, 0),
        new Move(-1, 0),
        new Move(0, 1)
    )),
    RABBIT(RED, ImmutableSet.of(
        new Move(1, 1),
        new Move(0, 2),
        new Move(-1, -1)
    )),
    TIGER(BLUE, ImmutableSet.of(
        new Move(2, 0),
        new Move(-1, 0)
    )),
    CRAB(BLUE, ImmutableSet.of(
        new Move(1, 0),
        new Move(0, -2),
        new Move(0, 2)
    )),
    GOOSE(BLUE, ImmutableSet.of(
        new Move(0, -1),
        new Move(0, 1),
        new Move(1, -1),
        new Move(-1, 1)
    )),
    CRANE(BLUE, ImmutableSet.of(
        new Move(1, 0),
        new Move(-1, -1),
        new Move(-1, 1)
    )),
    HORSE(BLUE, ImmutableSet.of(
        new Move(1, 0),
        new Move(0, -1),
        new Move(-1, 0)
    )),
    EEL(BLUE, ImmutableSet.of(
        new Move(1, -1),
        new Move(0, 1),
        new Move(-1, -1)
    )),
    FROG(BLUE, ImmutableSet.of(
        new Move(1, -1),
        new Move(0, -2),
        new Move(-1, 1)
    )),
    MONKEY(BLUE, ImmutableSet.of(
        new Move(1, -1),
        new Move(1, 1),
        new Move(-1, -1),
        new Move(-1, 1)
    ));

    public static final List<Card> ALL_CARDS = Arrays.asList(Card.values());
    private final CardColor cardColor;
    private final Set<Move> moves;

    Card(final CardColor cardColor, final Set<Move> moves) {
        this.cardColor = cardColor;
        this.moves = moves;
    }

    enum CardColor {
        RED,
        BLUE
    }
}
