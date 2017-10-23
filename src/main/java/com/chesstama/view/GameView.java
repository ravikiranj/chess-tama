package com.chesstama.view;

import com.chesstama.model.Game;
import com.chesstama.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GameView
 *
 * @author ravikiranj
 * @since Sep 2017
 */
public class GameView extends Application
{
    private static final String CHESSTAMA_FXML = "/chesstama.fxml";

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
    private ScrollPane movePane;
    private GridPane topRowGridPane;
    private GridPane middleRowGridPane;
    private GridPane bottomRowGridPane;

    public GameView()
    {
        game = new Game();
    }

    public void initCanvas()
    {
        // Init canvas
        canvas = new Pane();
        canvas.setPrefSize(800, 600);

        // Outer HBox containing main game and move window
        outerHBox = new HBox();
        canvas.getChildren().add(outerHBox);

        initMainGameVBox();
        initMovesWindow();
    }

    private void initMainGameVBox()
    {
        // Main Game VBox containing player cards and board
        mainGameVBox = new VBox();
        outerHBox.getChildren().add(mainGameVBox);

        initTopRowGridPane();
        initMiddleRowGridPane();
        initBottomRowGridPane();
    }

    private void initMovesWindow()
    {
        // Moves window
        movePane = new ScrollPane();
        movePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        outerHBox.getChildren().add(movePane);
    }

    private void initBottomRowGridPane()
    {
        // Bottom row containing Player 1 cards
        bottomRowGridPane = new GridPane();
        mainGameVBox.getChildren().add(bottomRowGridPane);

        Label emptyLabel1 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel1, 0, 0);

        // Player 1 cards
        Player p1 = game.getP1();
        player1Card1 = new PlayerCardView(p1, p1.getCards().get(0));
        GridPane.setConstraints(player1Card1.getPlayerCardButton(), 1, 0);

        player1Card2 = new PlayerCardView(p1, p1.getCards().get(1));
        GridPane.setConstraints(player1Card2.getPlayerCardButton(), 2, 0);

        Label emptyLabel2 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel2, 3, 0);

        bottomRowGridPane.getChildren().addAll(emptyLabel1, player1Card1.getPlayerCardButton(), player1Card2.getPlayerCardButton(), emptyLabel2);
    }

    private void initMiddleRowGridPane()
    {
        // Middle row containing Board and Temporary cards
        middleRowGridPane = new GridPane();
        mainGameVBox.getChildren().add(middleRowGridPane);

        // Player 2 temp card
        Player p2 = game.getP2();
        player2NextCard = new PlayerCardView(p2, p2.getUpcomingCard());
        GridPane.setConstraints(player2NextCard.getPlayerCardButton(), 0, 0);

        boardView = new BoardView();
        GridPane boardGridPane = boardView.getBoardGridPane();
        GridPane.setConstraints(boardGridPane, 1, 0, 2, 1);

        Player p1 = game.getP1();
        player1NextCard = new PlayerCardView(p1, p1.getUpcomingCard());
        GridPane.setConstraints(player1NextCard.getPlayerCardButton(), 3, 0);

        middleRowGridPane.getChildren().addAll(player1NextCard.getPlayerCardButton(), boardGridPane, player2NextCard.getPlayerCardButton());
    }

    private void initTopRowGridPane()
    {
        // Top row containing Player 2 cards
        topRowGridPane = new GridPane();
        mainGameVBox.getChildren().add(topRowGridPane);

        Label emptyLabel1 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel1, 0, 0);

        // Player 2 cards
        Player p2 = game.getP2();
        player2Card1 = new PlayerCardView(p2, p2.getCards().get(0));
        GridPane.setConstraints(player2Card1.getPlayerCardButton(), 1, 0);

        player2Card2 = new PlayerCardView(p2, p2.getCards().get(1));
        GridPane.setConstraints(player2Card2.getPlayerCardButton(), 2, 0);

        Label emptyLabel2 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel2, 3, 0);

        topRowGridPane.getChildren().addAll(emptyLabel1, player2Card1.getPlayerCardButton(), player2Card2.getPlayerCardButton(), emptyLabel2);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        initCanvas();

        Scene scene = new Scene(canvas, 800, 600);

        stage.setTitle("Chess Tama");
        stage.setScene(scene);
        stage.show();
    }
}
