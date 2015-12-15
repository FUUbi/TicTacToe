package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class GameBoardModel{
    public int[] board;
    private int lastMove;

    public GameBoardModel() {
        board = new int[9];
    }

    public void resetBoard(){
        board = new int[9];
    }

    public void setMove(int pos, int player){
        lastMove = pos;
        board[pos] = player;
    }

    public List<Integer> getMoves(){
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            if (board[i] == 0){
                moves.add(i);
            }
        }
        return moves;

    }

    public void setBoard(int[] board){
        this.board = board;
    }

    public int[] getBoard() {
        return board;
    }

    public int getLastMove() {
        return lastMove;
    }

    public GameBoardModel clone(){
        GameBoardModel gbClone = new GameBoardModel();
       int[] cloneBoard = new int[9];

        for (int i = 0; i < 9; i++){
                cloneBoard[i]= this.board[i];
        }

        gbClone.setBoard(cloneBoard);
        return gbClone;
    }


}
