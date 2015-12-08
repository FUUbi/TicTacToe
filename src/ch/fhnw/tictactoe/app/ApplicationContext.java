package ch.fhnw.tictactoe.app;

import ch.fhnw.tictactoe.present.info.fx.scene.GameScene;
import ch.fhnw.tictactoe.present.info.fx.scene.InfoScene;
import ch.fhnw.tictactoe.present.info.fx.scene.SceneSwitchModel;
import ch.fhnw.tictactoe.present.info.fx.scene.ScoreScene;
import javafx.stage.Stage;

/**
 * This class is the central container for the main data structures and other information that is
 * used throughout the application. This way, only the application context needs to be passed
 * around the various classes instead of many individual variables.
 */
public class ApplicationContext {
    private Stage primaryStage;
    private String version;
    private SceneSwitchModel sceneSwitchModel;



    public ApplicationContext(final String version, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.version = version;
        sceneSwitchModel = new SceneSwitchModel(
                new InfoScene(this),
                new GameScene(this),
                new ScoreScene(this)
                );



    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public String getVersion() {
        return version;
    }

    public SceneSwitchModel getSceneSwitchModel() {
        return sceneSwitchModel;
    }
}
