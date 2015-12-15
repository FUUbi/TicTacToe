package ch.fhnw.tictactoe.logic;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Player {
    Type type;
    int score;
    
    public Player(Type type) {
        this.type = type;
        score = 0;
    }

    public void iterateScore(){
        score += 1;
    }

    public void resetScore(){
        score = 0;
    }

    public Type getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public enum Type{
        HUMAN, COMPUTER
    }
}
