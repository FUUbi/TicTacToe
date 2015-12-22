package ch.fhnw.tictactoe.present.fx;

import ch.fhnw.tictactoe.app.ApplicationContext;
import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.Player;
import ch.fhnw.tictactoe.logic.PlayerModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class GameScene extends Scene {
    Game game;
    int[] board;
    PlayerModel playerModel;
    Player x;
    Player o;

    public GameScene(ApplicationContext applicationContext){
        super(new BorderPane());
        BorderPane borderPane = (BorderPane) getRoot();

        getStylesheets().add(getClass().getResource("/css/gameStyle.css").toString());

        GridPane gameArea = new GridPane();


        this.game = applicationContext.getGame();
        this.board = game.getGameBoard().getBoard();
        this.playerModel = applicationContext.getGame().getPlayerModel();
        this.o = playerModel.getO();
        this.x = playerModel.getX();

        game.gameMode = Game.Mode.HUMANvsHUMAN;

        createCells(gameArea);

        BorderPane infoPane = new BorderPane();


        Label btnX = new Label();
        btnX.setPrefSize(50, 50);
        btnX.getStyleClass().add("button-x");

        Label btnAI = new Label();
        btnAI.setPrefSize(50, 50);
        btnAI.getStyleClass().add("button-AI");

        HBox hBoxX = new HBox();
        hBoxX.getChildren().addAll(btnAI, btnX);


        Label buttonProjected = new Label();
        buttonProjected.setPrefSize(50, 50);
        buttonProjected.getStyleClass().add("button-o");

        Label buttonProjected2 = new Label();
        buttonProjected2.setPrefSize(50, 50);
        buttonProjected2.getStyleClass().add("button-human");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(buttonProjected, buttonProjected2);

        Label labelVS = new Label();
        labelVS.setPrefSize(50, 50);
        labelVS.getStyleClass().add("button-o");

        Button btnReset = new Button("Reset");
        btnReset.setPrefSize(100, 50);

        Button btnNewGame = new Button("New Game");
        btnNewGame.setPrefSize(100, 50);

        BorderPane hBox1 = new BorderPane();

        hBox1.setLeft(btnReset);
        hBox1.setRight(btnNewGame);



        infoPane.setCenter(labelVS);
        infoPane.setLeft(hBoxX);
        infoPane.setRight(hBox);
        infoPane.setBottom(hBox1);



        borderPane.setCenter(gameArea);
        borderPane.setBottom(infoPane);
        borderPane.setMinSize(500, 500);
    }

    private void createCells(GridPane gridPane){
        List<Pane> boxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Pane p = new Pane();
            p.getStyleClass().add("blank-field");

            final int finalI = i;
            p.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                if(board[finalI] == 0 && !game.isGameOver() && event.getButton() == MouseButton.PRIMARY){

                    switch (game.gameMode){
                        case HUMANvsAI:
                            game.moveHuman(o, finalI);
                            p.getStyleClass().remove("blank-field");
                            p.getStyleClass().add("O-field");

                            if(!game.isGameOver()){
                                game.getPlayerModel().setMaximisingPlayer(x);

                                game.moveAIPlayer(x, 11);
                                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().remove("blank-field");
                                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().add("X-field");

                            }
                            break;

                        case HUMANvsHUMAN:
                            if(playerModel.getMaximisingPlayer().getPlayerValue() == x.getPlayerValue()){
                                game.moveHuman(x, finalI);
                                p.getStyleClass().remove("blank-field");
                                p.getStyleClass().add("X-field");

                            }else {
                                game.moveHuman(o, finalI);
                                p.getStyleClass().remove("blank-field");
                                p.getStyleClass().add("O-field");
                            }
                            break;

                    }
                }

            });
            boxes.add(p);
        }

        for(Pane p : boxes){

            gridPane.getChildren().add(p);
            GridPane.setConstraints(p, boxes.indexOf(p)%3, boxes.indexOf(p)/3);

        }

        for (int i = 0; i < 3; i++) {
            ColumnConstraints col = new ColumnConstraints();
            RowConstraints row = new RowConstraints();

            col.setPercentWidth(33);
            row.setPercentHeight(33);

            gridPane.getColumnConstraints().add(col);
            gridPane.getRowConstraints().add(row);
        }


    }
}
