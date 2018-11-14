package com.chesstama.model;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        new Position(1, -2),
        new Position(1, 2),
        new Position(-1, -1),
        new Position(-1, 1)
    )),
    BOAR(RED, ImmutableSet.of(
        new Position(1, 0),
        new Position(0, -1),
        new Position(0, 1)
    )),
    MANTIS(RED, ImmutableSet.of(
        new Position(1, -1),
        new Position(1, 1),
        new Position(-1, 0)
    )),
    ELEPHANT(RED, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(1, -1),
        new Position(1, 1)
    )),
    ROOSTER(RED, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(-1, -1),
        new Position(1, 1)
    )),
    COBRA(RED, ImmutableSet.of(
        new Position(1, 1),
        new Position(-1, 1),
        new Position(0, -1)
    )),
    OX(RED, ImmutableSet.of(
        new Position(1, 0),
        new Position(-1, 0),
        new Position(0, 1)
    )),
    RABBIT(RED, ImmutableSet.of(
        new Position(1, 1),
        new Position(0, 2),
        new Position(-1, -1)
    )),
    TIGER(BLUE, ImmutableSet.of(
        new Position(2, 0),
        new Position(-1, 0)
    )),
    CRAB(BLUE, ImmutableSet.of(
        new Position(1, 0),
        new Position(0, -2),
        new Position(0, 2)
    )),
    GOOSE(BLUE, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(1, -1),
        new Position(-1, 1)
    )),
    CRANE(BLUE, ImmutableSet.of(
        new Position(1, 0),
        new Position(-1, -1),
        new Position(-1, 1)
    )),
    HORSE(BLUE, ImmutableSet.of(
        new Position(1, 0),
        new Position(0, -1),
        new Position(-1, 0)
    )),
    EEL(BLUE, ImmutableSet.of(
        new Position(1, -1),
        new Position(0, 1),
        new Position(-1, -1)
    )),
    FROG(BLUE, ImmutableSet.of(
        new Position(1, -1),
        new Position(0, -2),
        new Position(-1, 1)
    )),
    MONKEY(BLUE, ImmutableSet.of(
        new Position(1, -1),
        new Position(1, 1),
        new Position(-1, -1),
        new Position(-1, 1)
    ));

    public static final List<Card> ALL_CARDS = Arrays.asList(Card.values());
    private final CardColor cardColor;
    private final Set<Position> validPositions;

    Card(final CardColor cardColor, final Set<Position> validPositions) {
        this.cardColor = cardColor;
        this.validPositions = validPositions;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public Set<Position> getValidPositions() {
        return validPositions;
    }

    public Set<Position> getAbsoluteValidPositions() {
        return validPositions.stream()
                             .map(p -> {
                                 int absoluteRow = p.getRow() + Board.MASTER_ROW;
                                 int absoluteCol = p.getCol() + Board.MASTER_COL;
                                 return new Position(absoluteRow, absoluteCol);
                             })
                             .collect(Collectors.toSet());
    }

    public enum CardColor {
        RED,
        BLUE
    }
}
