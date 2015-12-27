package ch.fhnw.tictactoe.app;

import ch.fhnw.tictactoe.logic.*;
import ch.fhnw.tictactoe.present.fx.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the central container for the main data structures and other information that is
 * used throughout the application. This way, only the application context needs to be passed
 * around the various classes instead of many individual variables.
 */
public class ApplicationContext {
    private Game game;
    private PlayerModel playerModel;

    public ApplicationContext(Stage primaryStage) {
        game = new Game();

        Scene gameScene = new GameScene(this);
        primaryStage.setScene(gameScene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public Game getGame() {
        return game;
    }

}
