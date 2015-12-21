package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class GameBoard {
    public int[] board;
    private int lastMove;

    public GameBoard() {
        board = new int[9];
    }

    public void resetBoard() {
        board = new int[9];
    }

    public void setMove(int pos, int player) {
        lastMove = pos;
        board[pos] = player;
    }

    public void removeMove(int pos) {
        board[pos] = 0;
    }

    public List<Integer> getMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                moves.add(i);
            }
        }
        return moves;

    }


    public int[] getBoard() {
        return board;
    }

}
