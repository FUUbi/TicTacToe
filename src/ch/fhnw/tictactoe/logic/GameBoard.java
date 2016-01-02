package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the game board values and the last move,
 * all nine game cells are stored linearly in an array.
 */
public class GameBoard {
    private int[] values;
    private int lastMove;

    /**
     * Creates a new game board with an array of length nine.
     * Default array values are null.
     */
    public GameBoard() {
        values = new int[9];
    }

    /**
     * Resets all nine array values to 0
     */
    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            values[i] = 0;
        }
    }

    /**
     * Sets the board value with the player value.
     * @param pos the position to set, an int 1-9
     * @param player the player to set, an int  -1 or 1
     */
    public void setMove(int pos, int player) {
        values[pos] = player;
        lastMove = pos;
    }

    /**
     * Reset the game board value of one position.
     * @param pos the position to reset
     */
    public void removeMove(int pos) {
        values[pos] = 0;
    }

    /**
     * Returns a list of all empty game cells.
     * @return the empty cells
     */
    public List<Integer> getEmptyCells() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (values[i] == 0) {
                emptyCells.add(i);
            }
        }
        return emptyCells;

    }

    /**
     * Returns the last preformed move.
     * @return the last preformed move
     */
    public int getLastMove() {
        return lastMove;
    }

    /**
     * Returns an array of length nine, containing all
     * cell values.
     * @return the values of the game cells
     */
    public int[] getValues() {
        return values;
    }

    /**
     * Sets an array of length nine, containing all
     * cell values.
     * @param values the value of the game cells
     */
    public void setValues(int[] values){
        this.values = values;
    }

}
