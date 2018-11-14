package com.chesstama.view;

import com.chesstama.model.Card;
import com.chesstama.model.Game;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.view.PlayerCardView.CardSlot;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * GameView
 *
 * @author ravikiranj
 * @since Sep 2017
 */
public class GameView extends Application {

    private static final String CHESS_TAMA = "Chess-Tama";
    private static final String STYLES_CSS = "styles.css";

    private final Game game;

    private PlayerCardView player1Card1;
    private PlayerCardView player1Card2;

    private PlayerCardView player2Card1;
    private PlayerCardView player2Card2;

    private PlayerCardView player1NextCard;
    private PlayerCardView player2NextCard;

    private BoardView boardView;

    private Pane canvas;
    private HBox outerHBox;
    private VBox mainGameVBox;
    private GridPane topRowGridPane;
    private GridPane middleRowGridPane;
    private GridPane bottomRowGridPane;

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
        canvas.getChildren().add(outerHBox);

        initMainGameVBox();
    }

    private void initMainGameVBox() {
        // Main Game VBox containing player cards and board
        mainGameVBox = new VBox();
        outerHBox.getChildren().add(mainGameVBox);

        initTopRowGridPane();
        initMiddleRowGridPane();
        initBottomRowGridPane();
    }

    private void initBottomRowGridPane() {
        // Bottom row containing Player 1 cards
        bottomRowGridPane = new GridPane();
        bottomRowGridPane.getStyleClass().add(CSS.GAMEROW.getName());
        mainGameVBox.getChildren().add(bottomRowGridPane);

        // Player 1 cards
        Player p1 = game.getP1();
        player1Card1 = new PlayerCardView(p1, p1.getCards().get(0), CardSlot.MAIN, this);
        player1Card1.selectCard();
        GridPane.setConstraints(player1Card1, 1, 0);

        player1Card2 = new PlayerCardView(p1, p1.getCards().get(1), CardSlot.MAIN, this);
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
        player2NextCard = new PlayerCardView(p2, p2.getUpcomingCard(), CardSlot.NEXT, this);
        GridPane.setConstraints(player2NextCard, 0, 0);

        boardView = new BoardView(this);
        GridPane.setConstraints(boardView, 1, 0, 2, 1);

        Player p1 = game.getP1();
        player1NextCard = new PlayerCardView(p1, p1.getUpcomingCard(), CardSlot.NEXT, this);
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
        player2Card1 = new PlayerCardView(p2, p2.getCards().get(0), CardSlot.MAIN, this);
        GridPane.setConstraints(player2Card1, 1, 0);

        player2Card2 = new PlayerCardView(p2, p2.getCards().get(1), CardSlot.MAIN, this);
        GridPane.setConstraints(player2Card2, 2, 0);

        topRowGridPane.getChildren()
                      .addAll(player2Card1, player2Card2);
    }

    public void unselectAllPlayerCards() {
        player1Card1.unselectCard();
        player1Card2.unselectCard();
        player1NextCard.unselectCard();

        player2Card1.unselectCard();
        player2Card2.unselectCard();
        player2NextCard.unselectCard();
    }

    public void updateSelectedCard(final Card card) {
        this.game.getBoard().setSelectedCard(card);
    }

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

    @Override
    public void start(final Stage stage) throws Exception {
        initCanvas();

        Scene scene = new Scene(canvas);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(primaryScreenBounds.getWidth() * 0.75);
        stage.setHeight(primaryScreenBounds.getHeight() * 0.75);

        stage.setTitle(CHESS_TAMA);
        stage.setScene(scene);
        scene.getStylesheets().add(STYLES_CSS);
        stage.sizeToScene();
        stage.show();
    }
}
