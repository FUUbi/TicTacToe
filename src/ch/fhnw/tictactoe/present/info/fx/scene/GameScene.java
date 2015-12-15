package ch.fhnw.tictactoe.present.info.fx.scene;

import ch.fhnw.tictactoe.app.ApplicationContext;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class GameScene extends Scene {
    public GameScene(ApplicationContext applicationContext){
        super(new StackPane());
        StackPane pane = (StackPane) getRoot();
        Button btn = new Button("GameScene");
        btn.setOnAction(event -> applicationContext.getSceneSwitchModel().setAcctualScene(
                applicationContext.getSceneSwitchModel().getScoreScene()
        ));
        pane.getChildren().add(btn);
    }
}
