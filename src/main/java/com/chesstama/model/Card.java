package com.chesstama.model;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.HashSet;
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
public enum Card
{
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

    Card(CardColor cardColor, Set<Move> moves)
    {
        this.cardColor = cardColor;
        this.moves = moves;
    }

    public void printCard()
    {
        Set<Position> locations = getBoardLocations();
        System.out.println("==============");
        System.out.println(this);
        System.out.println("==============");
        for (int i = Board.MAX_ROWS; i >= 1; i--)
        {
            for (int j = 1; j <= Board.MAX_COLS; j++)
            {
                Position p = new Position(i, j);
                if (i == 3 && j == 3)
                {
                    System.out.print("C");
                }
                else if (locations.contains(p))
                {
                    System.out.print("M");
                }
                else
                {
                    System.out.print(".");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public Set<Position> getBoardLocations()
    {
        Position p = new Position(3, 3);
        Set<Position> result = new HashSet<>();

        for (Move m : this.moves)
        {
            result.add(new Position(p.getRow() + m.getRow(), p.getCol() + m.getCol()));
        }

        return result;
    }

    enum CardColor
    {
        RED,
        BLUE
    }
}
