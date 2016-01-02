package ch.fhnw.tictactoe.logic;

/**
 * This class implements the players value an his score.
 */
public class Player {
    private final int value;
    private int score;

    /**
     * Crates a new player with a value an his score.
     * @param value the players value
     */
    public Player(int value) {
        this.value = value;
        this.score = 0;
    }

    /**
     * Iterate the score by one.
     */
    public void iterateScore(){
        score += 1;
    }

    /**
     * Reset the score to zero.
     */
    public void resetScore(){
        score = 0;
    }

    /**
     * Returns the players value
     * @return the value of the player
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the players score
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

}
