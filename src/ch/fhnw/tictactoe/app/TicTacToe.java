package ch.fhnw.tictactoe.app;

import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.present.fx.GameScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String version = "v1.0";

        primaryStage.setTitle("Tic Tac Toe " + version);
        // Create the core data structures
        Scene gameScene = new GameScene(new Game());

        primaryStage.setScene(gameScene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
