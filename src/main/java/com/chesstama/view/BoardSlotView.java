package com.chesstama.view;

import com.chesstama.handlers.BoardSlotViewClickHandler;
import com.chesstama.handlers.NoOpClickHandler;
import com.chesstama.model.Piece;
import com.chesstama.model.Player;
import com.chesstama.model.Slot;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * BoardSlotView
 *
 * @author rjanardhana
 * @since Oct 2017
 */
@Slf4j
public class BoardSlotView extends StackPane {

    private static final String IMAGES_P1_KING_PNG = "images/P1-King.png";
    private static final String IMAGES_P2_KING_PNG = "images/P2-King.png";
    private static final String IMAGES_P2_PAWN_PNG = "images/P2-Pawn.png";
    private static final String IMAGES_P1_PAWN_PNG = "images/P1-Pawn.png";
    private static final String IMAGES_EMPTY_PNG = "images/Empty.png";

    private final Slot slot;
    private final GameView gameView;
    private final Label slotLabel;
    private final boolean isEvenSlotPosition;
    private boolean isHighlighted;

    public BoardSlotView(final Slot slot, final GameView gameView) {
        this(slot, gameView, BoardSlotType.GAME_BOARD_SLOT);
    }

    public BoardSlotView(final Slot slot,
                         final GameView gameView,
                         final BoardSlotType boardSlotType) {
        super();

        this.slot = slot;
        this.isEvenSlotPosition = slot.getPosition().isEvenPosition();
        this.gameView = gameView;
        this.slotLabel = new Label();
        this.isHighlighted = false;

        getChildren().addAll(slotLabel);
        getStyleClass().add(CSS.BOARD_SQUARE.getName());

        if (boardSlotType == BoardSlotType.GAME_BOARD_SLOT) {
            setSlotLabelGraphic();
            setEventHandlers();
        }
    }

    private ImageView getBackgroundImage() {
        Optional<Piece> pieceOptional = slot.getPiece();

        if (!pieceOptional.isPresent()) {
            return new ImageView(new Image(IMAGES_EMPTY_PNG));
        }

        Piece piece = pieceOptional.get();
        String imagePath;
        if (piece.getPlayer().getPlayerType() == Player.PlayerType.P1) {
            imagePath = piece.isKing() ? IMAGES_P1_KING_PNG : IMAGES_P1_PAWN_PNG;
        } else {
            imagePath = piece.isKing() ? IMAGES_P2_KING_PNG: IMAGES_P2_PAWN_PNG;
        }

        return new ImageView(new Image(imagePath));
    }

    public void updateView() {
        setSlotLabelGraphic();
    }

    private void setSlotLabelGraphic() {
        slotLabel.getStyleClass().clear();

        slotLabel.getStyleClass().add(CSS.BOARD_SLOT.getName());
        slotLabel.setGraphic(getBackgroundImage());
        if (isEvenSlotPosition) {
            slotLabel.getStyleClass().add(CSS.EVEN_BOARD_SLOT.getName());
        } else {
            slotLabel.getStyleClass().add(CSS.ODD_BOARD_SLOT.getName());
        }

    }

    public Slot getSlot() {
        return slot;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void highlightSlot() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_SQUARE.getName());
        log.info("Slot Label CSS = {}, isEvenSlotPosition = {}", this.slotLabel.getStyleClass(), isEvenSlotPosition);

        slotLabel.getStyleClass().clear();
        slotLabel.getStyleClass().add(CSS.BOARD_SLOT.getName());
        if (isEvenSlotPosition) {
            this.slotLabel.getStyleClass().remove(CSS.EVEN_BOARD_SLOT.getName());
        } else {
            this.slotLabel.getStyleClass().remove(CSS.ODD_BOARD_SLOT.getName());
        }
        isHighlighted = true;
    }

    public void unhighlightSlot() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_SQUARE.getName());

        slotLabel.getStyleClass().clear();
        slotLabel.getStyleClass().add(CSS.BOARD_SLOT.getName());
        if (isEvenSlotPosition) {
            this.slotLabel.getStyleClass().add(CSS.EVEN_BOARD_SLOT.getName());
        } else {
            this.slotLabel.getStyleClass().add(CSS.ODD_BOARD_SLOT.getName());
        }
        isHighlighted = false;
    }

    public void highlightPieceSlot() {
        this.getStyleClass().add(CSS.HIGHLIGHTED_PIECE_SQUARE.getName());
    }

    public void unhighlightPieceSlot() {
        this.getStyleClass().remove(CSS.HIGHLIGHTED_PIECE_SQUARE.getName());
    }

    private void setEventHandlers() {
        this.setOnMouseClicked(new BoardSlotViewClickHandler(this));
    }

    public void disableEventHandlers() {
        this.setOnMouseClicked(new NoOpClickHandler());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Slot = %s", this.slot.toString()));
        sb.append(String.format(", isHighlighted = %s", this.isHighlighted));
        return sb.toString();
    }

    public enum BoardSlotType {
        GAME_BOARD_SLOT,
        PLAYER_CARD_SLOT
    }
}
