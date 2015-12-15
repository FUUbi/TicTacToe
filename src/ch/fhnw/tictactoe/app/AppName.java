package ch.fhnw.tictactoe.app;

import ch.fhnw.tictactoe.present.info.fx.scene.SceneControler;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppName extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String version = "1.0";

        primaryStage.setTitle("Hello World");
        // Create the core data structures
        ApplicationContext applicationContext = new ApplicationContext(version, primaryStage);

        SceneControler sceneControler = new SceneControler(applicationContext);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
