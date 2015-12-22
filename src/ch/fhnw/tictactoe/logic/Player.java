package ch.fhnw.tictactoe.logic;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Player {
    private int playerValue;
    private int score;

    public Player(int playerValue) {
        this.playerValue = playerValue;
        this.score = 0;
    }

    public void iterateScore(){
        score += 1;
    }

    public void resetScore(){
        score = 0;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public int getScore() {
        return score;
    }

}
