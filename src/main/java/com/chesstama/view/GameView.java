package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Game;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.model.Player.PlayerType;
import com.chesstama.view.PlayerCardView.CardSlot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * GameView
 *
 * @author ravikiranj
 * @since Sep 2017
 */
@Slf4j
public class GameView extends Application {

    private static final String STYLES_CSS = "styles.css";

    public static final String PLAYER_1_STR = "Player 1";
    public static final String CHESS_TAMA = "Chess-Tama";
    public static final String PLAYER_2_STR = "Player 2";
    public static final String CURRENT_PLAYER_STR = "Current Player: ";
    private static final String THINKING = " (Thinking...)";

    private final Game game;

    private PlayerCardView player1Card1;
    private PlayerCardView player1Card2;

    private PlayerCardView player2Card1;
    private PlayerCardView player2Card2;

    private PlayerCardView player1NextCard;
    private PlayerCardView player2NextCard;

    private final List<PlayerCardView> allCards = new ArrayList<>();

    private BoardView boardView;

    private Pane canvas;
    private HBox outerHBox;
    private VBox mainGameVBox;
    private GridPane topRowGridPane;
    private GridPane middleRowGridPane;
    private GridPane bottomRowGridPane;
    private GridPane currentPlayerTurnGridPane;

    private Label currentPlayerTurnLabel;

    public GameView() {
        super();

        game = new Game();
    }

    public void initCanvas() {
        // Init canvas
        canvas = new Pane();
        canvas.setPrefSize(1200, 800);

        // Outer HBox containing main game and move window
        outerHBox = new HBox();
        outerHBox.getStyleClass().add(CSS.GAME_CONTAINER.getName());
        canvas.getChildren().add(outerHBox);

        initMainGameVBox();

        allCards.add(player1Card1);
        allCards.add(player1Card2);
        allCards.add(player1NextCard);
        allCards.add(player2Card1);
        allCards.add(player2Card2);
        allCards.add(player2NextCard);
    }

    private void initMainGameVBox() {
        // Main Game VBox containing player cards and board
        mainGameVBox = new VBox();
        outerHBox.getChildren().add(mainGameVBox);

        initCurrentPlayerTurnGridPane();
        initPlayerLabel(PlayerType.P2);
        initTopRowGridPane();
        initMiddleRowGridPane();
        initBottomRowGridPane();
        initPlayerLabel(PlayerType.P1);
    }

    private void initPlayerLabel(final PlayerType playerType) {
        GridPane gridPane = new GridPane();
        mainGameVBox.getChildren().add(gridPane);

        Label playerLabel = new Label(playerType == PlayerType.P1 ? PLAYER_1_STR : PLAYER_2_STR);
        playerLabel.getStyleClass().add(CSS.PLAYER_LABEL.getName());

        gridPane.getStyleClass().add(CSS.GAMEROW.getName());
        GridPane.setConstraints(playerLabel, 0, 0);
        gridPane.getChildren().add(playerLabel);
    }

    private void initCurrentPlayerTurnGridPane() {
        currentPlayerTurnGridPane = new GridPane();
        currentPlayerTurnGridPane.getStyleClass().add(CSS.GAMEROW.getName());
        mainGameVBox.getChildren().add(currentPlayerTurnGridPane);

        currentPlayerTurnLabel = new Label(getCurrentPlayerTurnString());
        currentPlayerTurnLabel.getStyleClass().add(CSS.CURRENT_PLAYER_TURN.getName());
        GridPane.setConstraints(currentPlayerTurnLabel, 0, 0);
        currentPlayerTurnGridPane.getChildren().add(currentPlayerTurnLabel);
    }

    private String getCurrentPlayerTurnString() {
        StringBuilder stringBuilder = new StringBuilder(CURRENT_PLAYER_STR);
        stringBuilder.append(game.getBoard().getCurrentPlayerTurn() == PlayerType.P1 ? PLAYER_1_STR : PLAYER_2_STR +
            THINKING);

        return stringBuilder.toString();
    }

    private void initBottomRowGridPane() {
        // Bottom row containing Player 1 cards
        bottomRowGridPane = new GridPane();
        bottomRowGridPane.getStyleClass().add(CSS.GAMEROW.getName());
        mainGameVBox.getChildren().add(bottomRowGridPane);

        // Player 1 cards
        Player p1 = game.getP1();
        player1Card1 = new PlayerCardView(p1.getPlayerType(), p1.getCards().get(0), CardSlot.MAIN, this);
        player1Card1.selectCard();
        GridPane.setConstraints(player1Card1, 1, 0);

        player1Card2 = new PlayerCardView(p1.getPlayerType(), p1.getCards().get(1), CardSlot.MAIN, this);
        GridPane.setConstraints(player1Card2, 2, 0);

        bottomRowGridPane.getChildren()
                         .addAll(player1Card1, player1Card2);
    }

    private void initMiddleRowGridPane() {
        // Middle row containing Board and Temporary cards
        middleRowGridPane = new GridPane();
        middleRowGridPane.getStyleClass().add(CSS.GAMEROW.getName());
        mainGameVBox.getChildren().add(middleRowGridPane);

        // Player 2 temp card
        Player p2 = game.getP2();
        player2NextCard = new PlayerCardView(p2.getPlayerType(), p2.getUpcomingCard(), CardSlot.NEXT, this);
        GridPane.setConstraints(player2NextCard, 0, 0);

        boardView = new BoardView(this);
        GridPane.setConstraints(boardView, 1, 0, 2, 1);

        Player p1 = game.getP1();
        player1NextCard = new PlayerCardView(p1.getPlayerType(), p1.getUpcomingCard(), CardSlot.NEXT, this);
        GridPane.setConstraints(player1NextCard, 3, 0);

        middleRowGridPane.getChildren().addAll(player1NextCard, boardView, player2NextCard);
    }

    private void initTopRowGridPane() {
        // Top row containing Player 2 cards
        topRowGridPane = new GridPane();
        topRowGridPane.getStyleClass().add(CSS.GAMEROW.getName());
        mainGameVBox.getChildren().add(topRowGridPane);

        // Player 2 cards
        Player p2 = game.getP2();
        player2Card1 = new PlayerCardView(p2.getPlayerType(), p2.getCards().get(0), CardSlot.MAIN, this);
        GridPane.setConstraints(player2Card1, 1, 0);

        player2Card2 = new PlayerCardView(p2.getPlayerType(), p2.getCards().get(1), CardSlot.MAIN, this);
        GridPane.setConstraints(player2Card2, 2, 0);

        topRowGridPane.getChildren()
                      .addAll(player2Card1, player2Card2);
    }

    public void unselectAllPlayerCards() {
        for (PlayerCardView playerCardView : allCards) {
            playerCardView.unselectCard();
        }
    }

    public List<com.chesstama.backend.engine.Card> getPlayerCards(final PlayerType playerType) {
        List<Card> playerCards = playerType == PlayerType.P1 ? game.getP1().getCards() : game.getP2().getCards();

        return playerCards.stream()
                          .map(card -> com.chesstama.backend.engine.Card.valueOf(card.name()))
                          .collect(Collectors.toList());
    }

    public com.chesstama.backend.engine.Card getPlayerUpcomingCard(final PlayerType playerType) {
        Card upcomingPlayerCard = playerType == PlayerType.P1 ? game.getP1().getUpcomingCard() : game.getP2().getUpcomingCard();

        return Optional.ofNullable(upcomingPlayerCard)
                       .map(card -> com.chesstama.backend.engine.Card.valueOf(card.name()))
                       .orElse(com.chesstama.backend.engine.Card.EMPTY);
    }

    public PlayerCardView getPlayerCardView(final com.chesstama.backend.engine.Card backendCard) {
        Card card = Card.valueOf(backendCard.name());
        for (PlayerCardView playerCardView : allCards) {
            if (playerCardView.getCard() == card) {
                return playerCardView;
            }
        }

        throw new IllegalArgumentException("Unable to find backendCard = " + backendCard);

    }

    public void updatePlayerCardViews() {
        unselectAllPlayerCards();

        List<Card> p1Cards = game.getP1().getCards();
        player1Card1.updateCard(p1Cards.get(0));
        player1Card2.updateCard(p1Cards.get(1));

        player1NextCard.updateCard(game.getP1().getUpcomingCard());

        List<Card> p2Cards = game.getP2().getCards();
        Card c1 = p2Cards.get(0);
        player2Card1.updateCard(c1);
        player2Card2.updateCard(p2Cards.get(1));

        player2NextCard.updateCard(game.getP2().getUpcomingCard());

        for (PlayerCardView playerCardView : allCards) {
            playerCardView.updateView();
        }

        if (getCurrentPlayerTurn() == PlayerType.P1) {
            player1Card1.selectCard();
            game.getBoard().setSelectedCard(player1Card1.getCard());
        } else {
            player2Card1.selectCard();
            game.getBoard().setSelectedCard(player2Card1.getCard());
        }
    }

    public void updateSelectedCard(final Card card) {
        this.game.getBoard().setSelectedCard(card);
    }

    @Nonnull
    public Card getCurrentSelectedCard() {
        return game.getBoard().getSelectedCard();
    }

    public Game getGame() {
        return game;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setCurrentSelectedPiece(final Piece p) {
        game.getBoard().setSelectedPiece(Optional.ofNullable(p));
    }

    public Optional<Piece> getCurrentSelectedPiece() {
         return game.getBoard().getSelectedPiece();
    }

    public PlayerType getCurrentPlayerTurn() {
        return game.getBoard().getCurrentPlayerTurn();
    }

    public void updateCurrentPlayerLabel() {
        currentPlayerTurnLabel.setText(getCurrentPlayerTurnString());
    }

    public void updateCurrentPlayerLabel(final String s) {
        currentPlayerTurnLabel.setText(s);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        initCanvas();

        Scene scene = new Scene(canvas);

        int stageWidth = 900;
        int stageHeight = 1000;

        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);

        stage.setTitle(CHESS_TAMA);
        stage.setScene(scene);
        scene.getStylesheets().add(STYLES_CSS);
        stage.show();

        log.info("Stage Width = {}, Height = {}", stage.getWidth(), stage.getHeight());
    }

    public void disableAllHandlers() {
        boardView.disableEventHandlers();

        for (PlayerCardView playerCardView : allCards) {
            playerCardView.disableEventHandlers();
        }
    }
}
