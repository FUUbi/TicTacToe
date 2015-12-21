package ch.fhnw.tictactoe.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppName extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String version = "v1.0";

        primaryStage.setTitle("Tic Tac Toe " + version);
        // Create the core data structures
        new ApplicationContext(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
