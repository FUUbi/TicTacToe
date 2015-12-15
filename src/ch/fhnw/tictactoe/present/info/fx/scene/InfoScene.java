package ch.fhnw.tictactoe.present.info.fx.scene;

import ch.fhnw.tictactoe.app.ApplicationContext;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class InfoScene extends Scene {
    public InfoScene(ApplicationContext applicationContext){
        super(new GridPane());
        GridPane pane = (GridPane) getRoot();
        Button btn = new Button("infoScene");
        btn.setOnAction(event -> applicationContext.getSceneSwitchModel().setAcctualScene(
                applicationContext.getSceneSwitchModel().getGameScene()
        ));




        Button btn00 = new Button("00");
        btn00.setOnAction(event -> btn00.setStyle("-fx-base: #3f50e7;"));

        Button btn01 = new Button("01");
        btn01.setOnAction(event -> btn01.setStyle("-fx-base: #3f50e7;"));
        Button btn02 = new Button("02");
        btn02.setOnAction(event -> btn02.setStyle("-fx-base: #3f50e7;"));
        Button btn10 = new Button("10");
        btn10.setOnAction(event -> btn10.setStyle("-fx-base: #3f50e7;"));
        Button btn11 = new Button("11");
        btn11.setOnAction(event -> btn11.setStyle("-fx-base: #3f50e7;"));
        Button btn12 = new Button("12");
        btn12.setOnAction(event -> btn12.setStyle("-fx-base: #3f50e7;"));
        Button btn20 = new Button("20");
        btn20.setOnAction(event -> btn20.setStyle("-fx-base: #3f50e7;"));
        Button btn21 = new Button("21");
        btn21.setOnAction(event -> btn21.setStyle("-fx-base: #3f50e7;"));
        Button btn22 = new Button("22");
        btn22.setOnAction(event -> btn22.setStyle("-fx-base: #3f50e7;"));
        pane.add(btn00, 0, 0); // column=1 row=0
        pane.add(btn01, 1, 0);  // column=2 row=0
        pane.add(btn02, 2, 0);  // column=2 row=0


        pane.add(btn10, 0, 1); // column=1 row=0
        pane.add(btn11, 1, 1);  // column=2 row=0
        pane.add(btn12, 2, 1);  // column=2 row=0


        pane.add(btn20, 0, 2); // column=1 row=0
        pane.add(btn21, 1, 2);  // column=2 row=0
        pane.add(btn22, 2, 2);  // column=2 row=0



    }
}
