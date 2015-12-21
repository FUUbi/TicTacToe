package ch.fhnw.tictactoe.logic;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Player {
    private Type type;
    private int playerValue;
    private int score;
    private int bestMove;
    
    public Player(Type type) {
        this.type = type;
        this.playerValue = (type == Type.O) ? 1 : -1;
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

    public int getPlayerValue() {
        return playerValue;
    }

    public void setBestMove(int bestMove) {
        this.bestMove = bestMove;
    }

    public int getBestMove() {
        return bestMove;
    }

    public int getScore() {
        return score;
    }

    public enum Type{
        X, O
    }
}
