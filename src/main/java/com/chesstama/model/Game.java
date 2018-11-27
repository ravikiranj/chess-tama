package com.chesstama.model;

import com.chesstama.util.GameUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Game
 *
 * @author rjanardhana
 * @since Aug 2017
 */
@Slf4j
public class Game {

    private static final int NUM_GAME_CARDS = 5;

    private final Board board;

    public Game() {
        log.info("Initalizing Game");
        List<Card> cardsForGame = GameUtil.getRandomSubList(Card.ALL_CARDS, NUM_GAME_CARDS);

        List<Card> p1Cards = new ArrayList<>();
        p1Cards.add(cardsForGame.get(0));
        p1Cards.add(cardsForGame.get(1));

        List<Card> p2Cards = new ArrayList<>();
        p2Cards.add(cardsForGame.get(3));
        p2Cards.add(cardsForGame.get(4));

        Player p1 = new Player(p1Cards, cardsForGame.get(2), Player.PlayerType.P1);
        Player p2 = new Player(p2Cards, null, Player.PlayerType.P2);

        this.board = new Board(p1, p2);
        log.info("Initalizing Game Complete");
    }

    public Board getBoard() {
        return board;
    }

    public Player getP1() {
        return board.getPlayer1();
    }

    public Player getP2() {
        return board.getPlayer2();
    }
}
