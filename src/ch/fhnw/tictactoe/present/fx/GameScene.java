package ch.fhnw.tictactoe.present.fx;

import ch.fhnw.tictactoe.app.ApplicationContext;
import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.Player;
import ch.fhnw.tictactoe.logic.PlayerModel;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    List<Pane> boxes;
    ComboBox<Integer> depth;
    Game game;
    int[] board;
    PlayerModel playerModel;
    Player x;
    Player o;

    private Label labelVS;

    public GameScene(ApplicationContext applicationContext){
        super(new BorderPane());
        BorderPane borderPane = (BorderPane) getRoot();

        getStylesheets().add(getClass().getResource("/css/gameStyle.css").toString());



        this.game = applicationContext.getGame();
        this.board = game.getGameBoard().getBoard();
        this.playerModel = applicationContext.getGame().getPlayerModel();
        this.o = playerModel.getO();
        this.x = playerModel.getX();

        game.gameMode = Game.Mode.HUMANvsAI;

        GridPane gameArea = getGameArea();
        BorderPane infoPane = getInfoPane();


        borderPane.setCenter(gameArea);
        borderPane.setBottom(infoPane);
        borderPane.setMinSize(500, 500);
    }

    private GridPane getGameArea(){

        GridPane gridPane = new GridPane();
        boxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Pane p = new Pane();
            p.getStyleClass().add("blank-field");

            final int finalI = i;
            p.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                if(board[finalI] == 0 && !game.isGameOver() && event.getButton() == MouseButton.PRIMARY){

                    switch (game.gameMode){
                        case HUMANvsAI:
                            if(playerModel.getNexPlaxerToMove().getPlayerValue() == o.getPlayerValue()){
                                game.moveHuman(o, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("O-field");
                            }

                            if(!game.isGameOver()){
                                game.getPlayerModel().setNexPlaxerToMove(x);

                                game.moveAIPlayer(x, depth.getValue());
                                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().add("X-field");

                            }

                            if (game.isGameOver()){
                                labelVS.setText(x.getScore() + "   :   "  + o.getScore());
                            }
                            break;

                        case HUMANvsHUMAN:
                            if(playerModel.getNexPlaxerToMove().getPlayerValue() == x.getPlayerValue()){
                                game.moveHuman(x, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("X-field");

                            }else {
                                game.moveHuman(o, finalI);
                                p.getStyleClass().removeAll("O-field", "X-field", "blank-field");
                                p.getStyleClass().add("O-field");
                            }


                            if (game.isGameOver()){
                                labelVS.setText(x.getScore() + "   :   "  + o.getScore());
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



        return gridPane;
    }

    private BorderPane getInfoPane(){

        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add("background");

        Button btnReset = new Button("Reset Board");

        Button btnStartAI = new Button("Start PC");
        btnStartAI.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if( game.getGameBoard().getMoves().size() == 9){

                game.getPlayerModel().setNexPlaxerToMove(x);
                game.moveAIPlayer(x, depth.getValue());
                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().removeAll("O-field", "X-field", "blank-field");
                boxes.get(game.getGameBoard().getLastMove()).getStyleClass().add("X-field");

            }

            if(game.gameMode == Game.Mode.HUMANvsAI) btnStartAI.setDisable(true);
        });


        Label labelX = new Label();
        labelX.setPrefSize(50, 50);
        labelX.getStyleClass().add("label-x");

        Label lableAI = new Label();
        lableAI.setPrefSize(50, 50);
        lableAI.addEventHandler(MouseEvent.MOUSE_RELEASED, event1 -> {
            switch (game.gameMode){
                case HUMANvsHUMAN:
                    game.gameMode = Game.Mode.HUMANvsAI;
                    lableAI.getStyleClass().removeAll("label-human", "label-AI");
                    lableAI.getStyleClass().add("label-AI");
                    resetBoxes(btnStartAI);
                    break;
                case HUMANvsAI:
                    game.gameMode = Game.Mode.HUMANvsHUMAN;
                    lableAI.getStyleClass().removeAll("label-human", "label-AI");
                    lableAI.getStyleClass().add("label-human");
                    resetBoxes(btnStartAI);
                    break;
            }
        });
        lableAI.getStyleClass().add("label-AI");

        depth = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4,5, 6, 7, 8, 9, 10));
        depth.setValue(9);
        depth.setPrefSize(80, 50);

        HBox hBoxX = new HBox();
        hBoxX.getChildren().addAll(depth, lableAI, labelX);


        Label labelO = new Label();
        labelO.setPrefSize(50, 50);
        labelO.getStyleClass().add("label-o");

        Label labelHuman = new Label();
        labelHuman.setPrefSize(50, 50);
        labelHuman.getStyleClass().add("label-human");

        HBox hBoxO = new HBox();
        hBoxO.getChildren().addAll(labelO, labelHuman);

       labelVS = new Label("0    :   0");


        btnReset.setOnMouseReleased(e -> resetBoxes(btnStartAI));

        Button btnNewGame = new Button("New Game");
        btnNewGame.setOnMouseReleased(e -> {
            resetBoxes(btnStartAI);
            x.resetScore();
            o.resetScore();
            labelVS.setText(x.getScore() + "   :   "  + o.getScore());
        });


        BorderPane btnPane = new BorderPane();

        btnPane.setLeft(btnReset);
        btnPane.setCenter(btnStartAI);
        btnPane.setRight(btnNewGame);



        infoPane.setCenter(labelVS);
        infoPane.setLeft(hBoxX);
        infoPane.setRight(hBoxO);
        infoPane.setBottom(btnPane);

        return infoPane;
    }

    private void resetBoxes(Button btnStartAI){
        game.getGameBoard().resetBoard();
        game.getPlayerModel().setNexPlaxerToMove(x);
        boxes.forEach(b -> {
            b.getStyleClass().removeAll("O-field", "X-field");
            b.getStyleClass().add("blank-field");
        });

        if(game.gameMode == Game.Mode.HUMANvsAI) btnStartAI.setDisable(false);
        else btnStartAI.setDisable(true);
    }
}
