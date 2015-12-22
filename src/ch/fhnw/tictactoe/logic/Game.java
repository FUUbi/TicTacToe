package ch.fhnw.tictactoe.logic;

import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Game extends GameBoard {
    private PlayerModel playerModel;
    Player actualPlayer;

    public Game() {
        this.playerModel = new PlayerModel();
    }

    public int minimax(int depth, Player player) {
        List<Integer> moves = getMoves();
        actualPlayer = playerModel.getTurn();

        int bestScore = (actualPlayer.getType() == player.getType()) ?  Integer.MIN_VALUE : Integer.MAX_VALUE;

        if( depth == 0 || moves.size() == 0){
            return heuristicValue();

        }

        for (int m : moves) {
            setMove(m, player.getPlayerValue());

            if (actualPlayer.getType() == player.getType()) {
                int currentScore = minimax( depth - 1, playerModel.getOponed(player));

                if(currentScore > bestScore){
                    bestScore = currentScore;
                    actualPlayer.setBestMove(m);
                }

            } else {
                int currentScore = minimax( depth - 1, playerModel.getOponed(player));

                if(currentScore < bestScore){
                    bestScore = currentScore;
                    actualPlayer.setBestMove(m);
                }
            }
            removeMove(m);



        }
        return bestScore;
    }

    public int heuristicValue() {
        int[][] evaluationPatern = new int[][]{
                {0,1,2}, {3,4,5}, {6,7,8},         //column 0  column 1  column 2
                {0,3,6},{1,4,7},{2,5,8},          //row 0 row 1 row 2
                {0,4,8},{2,4,6} };               // diagonal NW - SE  diagonal NE - SW


        int score = 0;
        int maximising = playerModel.getTurn().getPlayerValue();
        int minimising = playerModel.getOponed(playerModel.getTurn()).getPlayerValue();

        for (int[] pattern : evaluationPatern){
            int countInLineMax = 0;
            int countInLineMin = 0;

            for (int pos : pattern){
                if(getBoard()[pos] == maximising) countInLineMax++;
                if(getBoard()[pos] == minimising) countInLineMin++;
            }

            score += (countInLineMax != 0) ? Math.pow(10, countInLineMax-1) : 0;
            score -= (countInLineMin != 0) ? Math.pow(10, countInLineMin-1) : 0;


        }

        return score;
    }

    public boolean isgameOver(){
        int[] gb = getBoard();

        int[][] evaluationPatern = new int[][]{
                {0,1,2}, {3,4,5}, {6,7,8},      //column 0  column 1  column 2
                {0,3,6},{1,4,7},{2,5,8},          //row 0 row 1 row 2
                {0,4,8},{2,4,6} };      // diagonal NW - SE  diagonal NE - SW

        for (int player : new int[]{1,-1}) {
            for(int[] pos : evaluationPatern){
                if (    gb[pos[0]] == player && gb[pos[1]] == player && gb[pos[2]] == player ) return true;
            }
        }
        return false;
        }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }
}
