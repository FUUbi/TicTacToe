package ch.fhnw.tictactoe.present.fx;

import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.Player;
import ch.fhnw.tictactoe.logic.PlayerModel;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a the main game scene.
 * Scene
 *  ->BorderPane
 *      .center
 *          -> GridPane (game area)
 *              -> 9 panes
 *      .bottom
 *          -> BorderPane (info pane)
 *              .left
 *                  -> HBox (hBoxX)
 *                      -> ComboBox (depth cb)
 *                      -> Labels (label x; label ai or human)
 *              .center
 *                  -> Label (score label)
 *              .right
 *                  -> HBox (hBoxO)
 *                      -> Labels (label o; label human)
 *              .bottom
 *                  -> BorderPane (game control pane)
 *                      .left   -> Button (reset board)
 *                      .center -> Button (start pc)
 *                      .right  -> Button (new game)
 *
 */
public class GameScene extends Scene {
    private final Game game;
    private final int[] board;
    private final PlayerModel playerModel;
    private final Player x;
    private final Player o;

    private List<Pane> cells;
    private ComboBox<Integer> depthComboBox;
    private Label scoreLabel;

    public GameScene(Game game) {
        super(new BorderPane());
        BorderPane borderPane = (BorderPane) getRoot();
        getStylesheets().add(getClass().getResource("/css/gameStyle.css").toString());

        this.game = game;
        this.board = game.getGameBoard().getValues();
        this.playerModel = game.getPlayerModel();

        this.o = playerModel.getO();
        this.x = playerModel.getX();

        game.gameMode = Game.Mode.HUMANvsAI;

        GridPane gameArea = getGameArea();
        BorderPane infoPane = getInfoPane();


        borderPane.setCenter(gameArea);
        borderPane.setBottom(infoPane);
        borderPane.setMinSize(500, 500);
    }

    private GridPane getGameArea() {
        GridPane gridPane = new GridPane();
        cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Pane p = new Pane();
            p.getStyleClass().add("blank-field");

            final int finalI = i;
            p.setOnMouseReleased(event -> {
                if (board[finalI] == 0 && !game.isGameOver() && event.getButton() == MouseButton.PRIMARY) {

                    switch (game.gameMode) {
                        case HUMANvsAI:
                            if (playerModel.getNextPlayerToMove().getValue() == o.getValue()) {
                                game.moveHuman(o, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("O-field");

                                if (!game.isGameOver()) {
                                    game.getPlayerModel().setNextPlayerToMove(x);

                                    game.moveAIPlayer(x, depthComboBox.getValue());
                                    cells.get(game.getGameBoard().getLastMove()).getStyleClass().
                                            removeAll("O-field", "X-field", "blank-field");
                                    cells.get(game.getGameBoard().getLastMove()).getStyleClass().add("X-field");
                                }
                                scoreLabel.setText(x.getScore() + "   :   " + o.getScore());
                            }
                            break;

                        case HUMANvsHUMAN:
                            if (playerModel.getNextPlayerToMove().getValue() == x.getValue()) {
                                game.moveHuman(x, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("X-field");

                            } else {
                                game.moveHuman(o, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("O-field");
                            }
                            scoreLabel.setText(x.getScore() + "   :   " + o.getScore());
                            break;
                    }
                }

            });
            cells.add(p);
            gridPane.getChildren().add(p);
            GridPane.setConstraints(p, cells.indexOf(p) % 3, cells.indexOf(p) / 3);
        }

        for (int i = 0; i < 3; i++) {
            ColumnConstraints col = new ColumnConstraints();
            RowConstraints row = new RowConstraints();

            col.setPercentWidth(33);
            row.setPercentHeight(33);

            gridPane.getColumnConstraints().add(col);
            gridPane.getRowConstraints().add(row);
        }

        return gridPane;
    }

    private BorderPane getInfoPane() {
        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add("background");


        //---------------- game control pane---------------------\\
        BorderPane gameControlPane = new BorderPane();

        Button btnStartAI = new Button("Start PC");
        btnStartAI.setOnMouseReleased(e -> {
            if (game.getGameBoard().getEmptyCells().size() == 9 && playerModel.getFirstPlayerToMove() == x) {
                game.moveAIPlayer(x, depthComboBox.getValue());
                // javaFX bug has to preform remove all with all possible names
                // see http://stackoverflow.com/questions/12132896/listview-removeall-doesnt-work
                cells.get(game.getGameBoard().getLastMove()).getStyleClass()
                        .removeAll("O-field", "X-field", "blank-field");
                cells.get(game.getGameBoard().getLastMove()).getStyleClass().add("X-field");
            }

            if (game.gameMode == Game.Mode.HUMANvsAI) btnStartAI.setDisable(true);
        });

        Button btnNewGame = new Button("New Game");
        btnNewGame.setOnMouseReleased(e -> {
            resetBoxes(btnStartAI);
            x.resetScore();
            o.resetScore();
            scoreLabel.setText(x.getScore() + "   :   " + o.getScore());
        });

        Button btnReset = new Button("Reset Board");
        btnReset.setOnMouseReleased(e -> resetBoxes(btnStartAI));

        gameControlPane.setLeft(btnReset);
        gameControlPane.setCenter(btnStartAI);
        gameControlPane.setRight(btnNewGame);

        //---------------------HBox player x---------------------\\

        depthComboBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        depthComboBox.setValue(9);
        depthComboBox.setPrefSize(80, 50);

        Label labelX = new Label();
        labelX.setPrefSize(50, 50);
        labelX.getStyleClass().add("label-x");

        Label labelAIorHuman = new Label();
        labelAIorHuman.setPrefSize(50, 50);
        labelAIorHuman.setOnMouseReleased(e -> {
            switch (game.gameMode) {
                case HUMANvsHUMAN:
                    game.gameMode = Game.Mode.HUMANvsAI;
                    labelAIorHuman.getStyleClass().removeAll("label-human", "label-AI");
                    labelAIorHuman.getStyleClass().add("label-AI");
                    resetBoxes(btnStartAI);
                    break;
                case HUMANvsAI:
                    game.gameMode = Game.Mode.HUMANvsHUMAN;
                    labelAIorHuman.getStyleClass().removeAll("label-human", "label-AI");
                    labelAIorHuman.getStyleClass().add("label-human");
                    resetBoxes(btnStartAI);
                    break;
            }
        });
        labelAIorHuman.getStyleClass().add("label-AI");


        HBox hBoxX = new HBox();
        hBoxX.getChildren().addAll(depthComboBox, labelAIorHuman, labelX);


        //---------------------HBox player o---------------------\\
        Label labelO = new Label();
        labelO.setPrefSize(50, 50);
        labelO.getStyleClass().add("label-o");

        Label labelHuman = new Label();
        labelHuman.setPrefSize(50, 50);
        labelHuman.getStyleClass().add("label-human");

        HBox hBoxO = new HBox();
        hBoxO.getChildren().addAll(labelO, labelHuman);

        //---------------------score label---------------------\\
        scoreLabel = new Label("0    :   0");

        infoPane.setCenter(scoreLabel);
        infoPane.setLeft(hBoxX);
        infoPane.setRight(hBoxO);
        infoPane.setBottom(gameControlPane);

        return infoPane;
    }

    private void resetBoxes(Button btnStartAI) {
        game.getGameBoard().resetBoard();
        Player firstPlayer = (x == playerModel.getFirstPlayerToMove()) ? o : x;
        playerModel.setNextPlayerToMove(firstPlayer);
        playerModel.setFirstPlayerToMove(firstPlayer);

        cells.forEach(b -> {
            b.getStyleClass().removeAll("O-field", "X-field");
            b.getStyleClass().add("blank-field");
        });

        if (game.gameMode == Game.Mode.HUMANvsAI) btnStartAI.setDisable(false);
        else btnStartAI.setDisable(true);
    }
}
