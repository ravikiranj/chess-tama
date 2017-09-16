package com.chesstama.view;

import com.chesstama.model.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * BoardView
 *
 * @author ravikiranj
 * @since Sep 2017
 */
public class BoardView extends Application
{
    private static final String CHESSTAMA_FXML = "/chesstama.fxml";

    private final Game game;
    private Pane canvas;
    private HBox outerHBox;
    private VBox mainGameVBox;
    private ScrollPane movePane;
    private GridPane topRowGridPane;
    private GridPane middleRowGridPane;
    private GridPane boardGridPane;
    private GridPane bottomRowGridPane;

    public BoardView()
    {
        this.game = new Game();
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
        Button player1Card1 = new Button("Player1 Card1");
        GridPane.setConstraints(player1Card1, 1, 0);

        Button player1Card2 = new Button("Player1 Card2");
        GridPane.setConstraints(player1Card2, 2, 0);

        Label emptyLabel2 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel2, 3, 0);

        bottomRowGridPane.getChildren().addAll(emptyLabel1, player1Card1, player1Card2, emptyLabel2);
    }

    private void initMiddleRowGridPane()
    {
        // Middle row containing Board and Temporary cards
        middleRowGridPane = new GridPane();
        mainGameVBox.getChildren().add(middleRowGridPane);

        // Player 2 temp card
        Button player2NextCard = new Button("Player2 Next Card");
        GridPane.setConstraints(player2NextCard, 0, 0);

        boardGridPane = new GridPane();
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= 5; j++)
            {
                int pRow = 5-i+1;
                int pCol = 5-j+1;
                Button piece = new Button(pRow + "," + pCol);
                GridPane.setConstraints(piece, j-1, i-1);
                boardGridPane.getChildren().add(piece);
            }
        }
        GridPane.setConstraints(boardGridPane, 1, 0, 2, 1);

        Button player1NextCard = new Button("Player1 Next Card");
        GridPane.setConstraints(player1NextCard, 3, 0);

        middleRowGridPane.getChildren().addAll(player1NextCard, boardGridPane, player2NextCard);
    }

    private void initTopRowGridPane()
    {
        // Top row containing Player 2 cards
        topRowGridPane = new GridPane();
        mainGameVBox.getChildren().add(topRowGridPane);

        Label emptyLabel1 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel1, 0, 0);

        // Player 2 cards
        Button player2Card1 = new Button("Player2 Card1");
        GridPane.setConstraints(player2Card1, 1, 0);

        Button player2Card2 = new Button("Player2 Card2");
        GridPane.setConstraints(player2Card2, 2, 0);

        Label emptyLabel2 = new Label("EMPTY");
        GridPane.setConstraints(emptyLabel2, 3, 0);

        topRowGridPane.getChildren().addAll(emptyLabel1, player2Card1, player2Card2, emptyLabel2);
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
