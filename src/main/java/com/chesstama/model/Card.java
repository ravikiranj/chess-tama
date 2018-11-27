package com.chesstama.model;

import com.chesstama.model.Player.PlayerType;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
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
@Slf4j
public enum Card {
    /*
    (top, left)     = (1, 1)
    (top, right)    = (1, 5)
    (bottom, left)  = (5, 1)
    (bottom, right) = (5, 5)

               ↑
         .  .  .  .  .
         X  .  .  .  X
         .  .  M  .  .
         .  X  .  X  .
         .  .  .  .  .

     */
    DRAGON(RED, ImmutableSet.of(
        new Position(-1, -2),
        new Position(-1, 2),
        new Position(1, -1),
        new Position(1, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  X  .  .
         .  X  M  X  .
         .  .  .  .  .
         .  .  .  .  .
     */
    BOAR(RED, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(-1, 0)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  X  .
         .  .  M  .  .
         .  .  X  .  .
         .  .  .  .  .

     */
    MANTIS(RED, ImmutableSet.of(
        new Position(1, 0),
        new Position(-1, -1),
        new Position(-1, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  X  .
         .  X  M  X  .
         .  .  .  .  .
         .  .  .  .  .
     */
    ELEPHANT(RED, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(-1, -1),
        new Position(-1, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  .  X  .
         .  X  M  X  .
         .  X  .  .  .
         .  .  .  .  .
     */
    ROOSTER(RED, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(-1, 1),
        new Position(1, -1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  .  X  .
         .  X  M  .  .
         .  .  .  X  .
         .  .  .  .  .
     */
    COBRA(RED, ImmutableSet.of(
        new Position(1, 1),
        new Position(-1, 1),
        new Position(0, -1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  X  .  .
         .  .  M  X  .
         .  .  X  .  .
         .  .  .  .  .
     */
    OX(RED, ImmutableSet.of(
        new Position(1, 0),
        new Position(-1, 0),
        new Position(0, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  .  X  .
         .  .  M  .  X
         .  X  .  .  .
         .  .  .  .  .
     */
    RABBIT(RED, ImmutableSet.of(
        new Position(1, -1),
        new Position(0, 2),
        new Position(-1, 1)
    )),

    /*
               ↑
         .  .  X  .  .
         .  .  .  .  .
         .  .  M  .  .
         .  .  X  .  .
         .  .  .  .  .
     */
    TIGER(BLUE, ImmutableSet.of(
        new Position(-2, 0),
        new Position(1, 0)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  X  .  .
         X  .  M  .  X
         .  .  .  .  .
         .  .  .  .  .
     */
    CRAB(BLUE, ImmutableSet.of(
        new Position(-1, 0),
        new Position(0, -2),
        new Position(0, 2)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  .  .
         .  X  M  X  .
         .  .  .  X  .
         .  .  .  .  .
     */
    GOOSE(BLUE, ImmutableSet.of(
        new Position(0, -1),
        new Position(0, 1),
        new Position(1, 1),
        new Position(-1, -1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  X  .  .
         .  .  M  .  .
         .  X  .  X  .
         .  .  .  .  .
     */
    CRANE(BLUE, ImmutableSet.of(
        new Position(-1, 0),
        new Position(1, -1),
        new Position(1, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  .  X  .  .
         .  X  M  .  .
         .  .  X  .  .
         .  .  .  .  .
     */
    HORSE(BLUE, ImmutableSet.of(
        new Position(1, 0),
        new Position(0, -1),
        new Position(-1, 0)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  .  .
         .  .  M  X  .
         .  X  .  .  .
         .  .  .  .  .
     */
    EEL(BLUE, ImmutableSet.of(
        new Position(1, -1),
        new Position(0, 1),
        new Position(-1, -1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  .  .
         X  .  M  .  .
         .  .  .  X  .
         .  .  .  .  .
     */
    FROG(BLUE, ImmutableSet.of(
        new Position(-1, -1),
        new Position(0, -2),
        new Position(1, 1)
    )),

    /*
               ↑
         .  .  .  .  .
         .  X  .  X  .
         .  .  M  .  .
         .  X  .  X  .
         .  .  .  .  .
     */
    MONKEY(BLUE, ImmutableSet.of(
        new Position(1, -1),
        new Position(1, 1),
        new Position(-1, -1),
        new Position(-1, 1)
    ));

    public static final String UP_ARROW_STRING = "↑";
    public static final String DOWN_ARROW_STRING = "↓";
    public static final List<Card> ALL_CARDS = Arrays.asList(Card.values());
    private static final String EMPTY_SPACE_V1 = "  ";
    private static final String EMPTY_SPACE_V2 = "   ";
    private final CardColor cardColor;
    private final Set<Position> validPositions;

    Card(final CardColor cardColor, final Set<Position> validPositions) {
        this.cardColor = cardColor;
        this.validPositions = Collections.unmodifiableSet(validPositions);
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public Set<Position> getValidPositions() {
        return validPositions;
    }

    public Set<Position> getAbsoluteValidPositions(final PlayerType playerType) {
        return validPositions.stream()
                             .map(p -> {
                                 int modifier = playerType == PlayerType.P1 ? 1 : -1;
                                 int absoluteRow = p.getRow() * modifier + Board.MASTER_ROW;
                                 int absoluteCol = p.getCol() * modifier + Board.MASTER_COL;
                                 return new Position(absoluteRow, absoluteCol);
                             })
                             .collect(Collectors.toSet());
    }

    @SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidInstantiatingObjectsInLoops"})
    public void printForPlayer1() {
        log.info("Player 1");
        Set<Position> absolutePositions = getAbsoluteValidPositions(PlayerType.P1);
        for (int col = 1; col <= Board.MAX_COLS; col++) {
            if (col == Board.MASTER_COL) {
                System.out.print(EMPTY_SPACE_V2 + UP_ARROW_STRING + EMPTY_SPACE_V2);
            } else {
                System.out.print(EMPTY_SPACE_V1);
            }
        }
        System.out.println();
        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                Position currentPosition = new Position(row, col);
                if (currentPosition.equals(Board.MASTER_POSITION)) {
                    System.out.print(" M ");
                    continue;
                }
                if (absolutePositions.contains(currentPosition)) {
                    System.out.print(" X ");
                    continue;
                }
                System.out.print(" . ");
            }
            System.out.println();
        }

    }

    @SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidInstantiatingObjectsInLoops"})
    public void printForPlayer2() {
        log.info("Player 2");
        Set<Position> absolutePositions = getAbsoluteValidPositions(PlayerType.P2);
        String[][] values = new String[Board.MAX_ROWS+1][Board.MAX_COLS+1];
        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                int modRow = Board.MAX_ROWS - row + 1;
                int modCol = Board.MAX_COLS - col + 1;
                Position currentPosition = new Position(modRow, modCol);
                if (currentPosition.equals(Board.MASTER_POSITION)) {
                    values[modRow][modCol] = " M ";
                    continue;
                }
                if (absolutePositions.contains(currentPosition)) {
                    values[modRow][modCol] = " X ";
                    continue;
                }
                values[modRow][modCol] = " . ";
            }
        }

        for (int row = 1; row <= Board.MAX_ROWS; row++) {
            for (int col = 1; col <= Board.MAX_COLS; col++) {
                System.out.print(values[row][col]);
            }
            System.out.println();
        }
        for (int col = 1; col <= Board.MAX_COLS; col++) {
            if (col == Board.MASTER_COL) {
                System.out.print(EMPTY_SPACE_V2 + DOWN_ARROW_STRING + EMPTY_SPACE_V2);
            } else {
                System.out.print(EMPTY_SPACE_V1);
            }
        }
    }

    public enum CardColor {
        RED,
        BLUE
    }
}
