package ch.fhnw.tictactoe.logic;

import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Game extends GameBoard {
    public int acct = 0 ;
    public int bestMove;
    public Game(){

    }

    public int minimax(int depth, int player) {
        List<Integer> moves = getMoves();

        int bestScore = (acct == player) ?  Integer.MIN_VALUE : Integer.MAX_VALUE;

        if(gameOver() || depth == 0 || moves.size() == 0){
            return heuristicValue();

        }

        for (int m : moves) {
            setMove(m, player);

            if (acct == player) {
                int currentScore = minimax( depth - 1, -player);
                if(currentScore > bestScore){
                    bestScore = currentScore;
                    bestMove = m;
                }

            } else {
                int currentScore = minimax( depth - 1, -player);
                if(currentScore < bestScore){
                    bestScore = currentScore;
                    bestMove = m;
                }
            }
            removeMove(m);

        }
        return bestScore;
    }

    public int heuristicValue() {
        int[] gb = getBoard();

        for (int player : new int[]{1, -1}) {
            if (    gb[0] == player && gb[3] == player && gb[6] == player || // row 0
                    gb[1] == player && gb[4] == player && gb[7] == player || // row 1
                    gb[2] == player && gb[5] == player && gb[8] == player || // row 2

                    gb[0] == player && gb[1] == player && gb[2] == player || // column 0
                    gb[3] == player && gb[4] == player && gb[5] == player || // column 1
                    gb[6] == player && gb[7] == player && gb[8] == player || // column 2

                    gb[0] == player && gb[4] == player && gb[8] == player || // diagonal NW - SE
                    gb[2] == player && gb[4] == player && gb[6] == player)  // diagonal NE - SW
            {
                return (player == acct) ? 1 : -1;
            }
        }


        return 0;
    }

    public boolean gameOver(){
        int[] gb = getBoard();

        for (int player : new int[]{1,-1}) {
            if (gb[0] == player && gb[3] == player && gb[6] == player || // row 0
                    gb[1] == player && gb[4] == player && gb[7] == player || // row 1
                    gb[2] == player && gb[5] == player && gb[8] == player || // row 2

                    gb[0] == player && gb[1] == player && gb[2] == player || // column 0
                    gb[3] == player && gb[4] == player && gb[5] == player || // column 1
                    gb[6] == player && gb[7] == player && gb[8] == player || // column 2

                    gb[0] == player && gb[4] == player && gb[8] == player || // diagonal NW - SE
                    gb[2] == player && gb[4] == player && gb[6] == player)  // diagonal NE - SW
            {
                return true;
            }

        }
        return false;
        }

}
