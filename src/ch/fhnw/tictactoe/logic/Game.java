package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Game {
    private GameBoardModel gameBoard;

    public Game(){
        gameBoard = new GameBoardModel();

    }

    public void newRound(){
        gameBoard.resetBoard();
    }

    public int negmax(GameBoardModel gb, int depth, int player){
        if(depth == 0 || gb.getMoves().size() == 0){
            return gameOver(gb.getBoard(), player); // return Heuristik value
        }
        int bestValue = Integer.MIN_VALUE;
        List<Integer> moves = gb.getMoves();
        List<GameBoardModel> childBoards = new ArrayList<>();
        int nextPlayer = (player == -1) ? 1 : -1;
        int bestMove = -1;

        for(int m : moves){
            GameBoardModel newGb = gb.clone();
            newGb.setMove(m, player);

            int val = -negmax(newGb, depth, nextPlayer);
            int newbest = Math.max(bestValue, val);
            System.out.println(newGb.getLastMove() + " " +  player* newbest);

            bestMove = (bestValue < newbest) ? newGb.getLastMove() : bestMove;

        }


        return bestMove;

    }


    public int gameOver(int[] gb,  int player){
        //Sieg


        if( gb[0] == player && gb[3] == player && gb[6] == player || // row 0
            gb[1] == player && gb[4] == player && gb[7] == player || // row 1
            gb[2] == player && gb[5] == player && gb[8] == player || // row 2

            gb[0] == player && gb[1] == player && gb[2] == player || // column 0
            gb[3] == player && gb[4] == player && gb[5] == player || // column 1
            gb[6] == player && gb[7] == player && gb[8] == player || // column 2

            gb[0] == player && gb[4] == player && gb[8] == player || // diagonal NW - SE
            gb[2] == player && gb[4] == player && gb[6] == player )  // diagonal NE - SW
        {
           return 100;
        }
        return 0;
    }




    public GameBoardModel getGameBoard() {
        return gameBoard;
    }
}
