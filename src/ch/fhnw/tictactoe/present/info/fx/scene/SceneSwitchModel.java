package ch.fhnw.tictactoe.present.info.fx.scene;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 06.12.2015.
 */
public class SceneSwitchModel {
    private Scene acctualScene;
    private InfoScene infoScene;
    private GameScene gameScene;
    private ScoreScene scoreScene;

    List<SceneSwichListner> sceneListners;

    public SceneSwitchModel(InfoScene infoScene, GameScene gameScene, ScoreScene scoreScene){
        this.infoScene = infoScene;
        this.gameScene = gameScene;
        this.scoreScene = scoreScene;
        this.sceneListners = new ArrayList<>();
        acctualScene = infoScene;
    }

    public Scene getAcctualScene() {
        return acctualScene;
    }


    public void setAcctualScene(Scene acctualScene) {
        this.acctualScene = acctualScene;
        fireModelChanged();
    }

    public void addListner(SceneSwichListner sL){
        sceneListners.add(sL);
    }

    public InfoScene getInfoScene() {
        return infoScene;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public ScoreScene getScoreScene() {
        return scoreScene;
    }

    private void fireModelChanged(){
        sceneListners.forEach(listner -> listner.sceneModelChanged());
    }
}
