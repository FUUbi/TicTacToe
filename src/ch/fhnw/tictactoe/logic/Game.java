package ch.fhnw.tictactoe.logic;

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
        List<Integer> moves = gb.getMoves();

        if(depth == 0 || moves.isEmpty()){

            return heuristicValue(gb.getBoard(), player);
        }

        int bestValue = Integer.MIN_VALUE;

        for(int m : moves){
            GameBoardModel childNode = gb.clone();
            childNode.setMove(m, player);
            int val = -negmax(childNode, depth -1, -player);
            bestValue = Math.max(bestValue, val);
        }
        return bestValue;

    }


    public int heuristicValue(int[] gb, int p) {
        //Sieg
        for (int player : new int[]{-1, 1}) {


            if (gb[0] == player && gb[3] == player && gb[6] == player || // row 0
                    gb[1] == player && gb[4] == player && gb[7] == player || // row 1
                    gb[2] == player && gb[5] == player && gb[8] == player || // row 2

                    gb[0] == player && gb[1] == player && gb[2] == player || // column 0
                    gb[3] == player && gb[4] == player && gb[5] == player || // column 1
                    gb[6] == player && gb[7] == player && gb[8] == player || // column 2

                    gb[0] == player && gb[4] == player && gb[8] == player || // diagonal NW - SE
                    gb[2] == player && gb[4] == player && gb[6] == player)  // diagonal NE - SW
            {
                return (p == -1) ? 1 : -1;
            }

        }
        return 0;
    }




    public GameBoardModel getGameBoard() {
        return gameBoard;
    }
}
