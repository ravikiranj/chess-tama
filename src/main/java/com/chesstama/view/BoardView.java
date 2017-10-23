package com.chesstama.view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * BoardView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
public class BoardView
{
    private GridPane boardGridPane;
    private Button[][] board;

    public BoardView()
    {
        boardGridPane = new GridPane();
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= 5; j++)
            {
                Button piece = new Button(i + "," + j);
                GridPane.setConstraints(piece, j-1, i-1);
                boardGridPane.getChildren().add(piece);
            }
        }

        GridPane.setConstraints(boardGridPane, 1, 0, 2, 1);
    }

    public GridPane getBoardGridPane()
    {
        return boardGridPane;
    }

    public Button[][] getBoard()
    {
        return board;
    }
}
