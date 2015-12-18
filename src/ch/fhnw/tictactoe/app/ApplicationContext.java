package ch.fhnw.tictactoe.app;

import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.GameBoardModel;
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
    private Game game;



    public ApplicationContext(final String version, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.version = version;
        game = new Game();
        // 012
        // 345
        // 678

        // -1 1 -1
        // -1 0 0
        //  0 0 1
        game.getGameBoard().setMove(0, -1);
        game.getGameBoard().setMove(1, 1);
        game.getGameBoard().setMove(2, -1);

        game.getGameBoard().setMove(3, -1);
        game.getGameBoard().setMove(4, 0);
        game.getGameBoard().setMove(5, 0);

        game.getGameBoard().setMove(6, 0);
        game.getGameBoard().setMove(7, 0);
        game.getGameBoard().setMove(8, 1);

        game.acct = 1;

        for(int moves : game.getGameBoard().getMoves()){
            GameBoardModel gb = game.getGameBoard().clone();
            gb.setMove(moves, 1);
            System.out.println(moves + " " + game.negmax(gb, 9, 1));

        }

        // -1 1 -1
        //  1 1 0
        //  0 0 0
        game.acct = -1;
        game.getGameBoard().setMove(0, -1);
        game.getGameBoard().setMove(1, 1);
        game.getGameBoard().setMove(2, -1);

        game.getGameBoard().setMove(3, 1);
        game.getGameBoard().setMove(4, 1);
        game.getGameBoard().setMove(5, 0);

        game.getGameBoard().setMove(6, 0);
        game.getGameBoard().setMove(7, 0);
        game.getGameBoard().setMove(8, 0);

        for(int moves : game.getGameBoard().getMoves()){
            GameBoardModel gb = game.getGameBoard().clone();
            gb.setMove(moves, -1);
            System.out.println(moves + " " + game.negmax(gb, 9, -1));

        }
/*
        game.getGameBoard().setMove(GameBoardModel.Pos.M10, Player.Type.COMPUTER);
        game.getGameBoard().setMove(GameBoardModel.Pos.M11, Player.Type.COMPUTER);
        game.getGameBoard().setMove(GameBoardModel.Pos.M12, Player.Type.COMPUTER);
*/
        System.out.println(game.negmax(game.getGameBoard(), 9, -1));

        sceneSwitchModel = new SceneSwitchModel(
                new InfoScene(this),
                new GameScene(this),
                new ScoreScene(this)
                );



    }

    public Game getGame() {
        return game;
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
