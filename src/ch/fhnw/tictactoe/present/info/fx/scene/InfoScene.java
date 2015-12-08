package ch.fhnw.tictactoe.present.info.fx.scene;

import ch.fhnw.tictactoe.app.ApplicationContext;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class InfoScene extends Scene {
    public InfoScene(ApplicationContext applicationContext){
        super(new StackPane());
        StackPane pane = (StackPane) getRoot();
        Button btn = new Button("infoScene");
        btn.setOnAction(event -> applicationContext.getSceneSwitchModel().setAcctualScene(
                applicationContext.getSceneSwitchModel().getGameScene()
        ));
        pane.getChildren().add(btn);
    }
}
