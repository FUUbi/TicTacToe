package ch.fhnw.tictactoe.present.fx;

import ch.fhnw.tictactoe.app.ApplicationContext;
import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.Player;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class GameScene extends Scene {
    public GameScene(ApplicationContext applicationContext){
        super(new BorderPane());
        BorderPane borderPane = (BorderPane) getRoot();

        GridPane gameArea = new GridPane();
        gameArea.setStyle("-fx-background-color:red");


        Game game = applicationContext.getGame();
        Player o = applicationContext.getGame().getPlayerModel().getO();
        Player x = applicationContext.getGame().getPlayerModel().getX();

        List<Pane> boxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Pane p = new Pane();
            p.setStyle(
                    "-fx-background-image: url('/blank.jpg');" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: stretch");

            final int finalI = i;
            p.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                if(event.getButton() == MouseButton.PRIMARY && game.getBoard()[finalI] == 0 && !game.isgameOver()){
                    System.out.println(finalI);
                    applicationContext.getGame().setMove(finalI, applicationContext.getGame().getPlayerModel().getO().getPlayerValue());
                    p.setStyle(
                            "-fx-background-image: url('/o.jpg');" +
                            "-fx-background-repeat: no-repeat;" +
                            "-fx-background-size: stretch");

                    if(!game.isgameOver()){
                        game.getPlayerModel().setTurn(x);
                        game.minimax(110, x);
                        int move = x.getBestMove();
                        game.setMove(move, x.getPlayerValue());
                        System.out.println("move " + move);
                        boxes.get(move).setStyle(
                                "-fx-background-image: url('/x.jpg');" +
                                        "-fx-background-repeat: no-repeat;" +
                                        "-fx-background-size: stretch");
                    }

                }

            });
            boxes.add(p);
        }

        for(Pane p : boxes){
            gameArea.getChildren().add(p);
            GridPane.setConstraints(p, boxes.indexOf(p)%3, boxes.indexOf(p)/3);

        }

        for (int i = 0; i < 3; i++) {
            ColumnConstraints col = new ColumnConstraints();
            RowConstraints row = new RowConstraints();

            col.setPercentWidth(33);
            row.setPercentHeight(33);

            gameArea.getColumnConstraints().add(col);
            gameArea.getRowConstraints().add(row);
        }

        borderPane.setCenter(gameArea);
        borderPane.setMinSize(500, 500);
    }
}
