package ch.fhnw.tictactoe.logic;

import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Game {
    private GameBoard gameBoard;
    private PlayerModel playerModel;
    public Mode gameMode;
    private int[][] evaluationPatern;

    public Game() {
        gameBoard = new GameBoard();
        this.playerModel = new PlayerModel();
        evaluationPatern = new int[][]{
                {0,1,2}, {3,4,5}, {6,7,8},         //column 0  column 1  column 2
                {0,3,6},{1,4,7},{2,5,8},          //row 0 row 1 row 2
                {0,4,8},{2,4,6} };               // diagonal NW - SE  diagonal NE - SW
    }

    public void moveAIPlayer(Player p, int depth){
        playerModel.setMaximisingPlayer(p);
        int pos = minimax(depth, p)[1];
        gameBoard.setMove(pos, p.getPlayerValue());
    }
    public void moveHuman(Player p, int pos){
        playerModel.setMaximisingPlayer(p);
        gameBoard.setMove(pos, p.getPlayerValue());
        playerModel.setMaximisingPlayer(playerModel.getOponed(p));
    }

    private int[] minimax(int depth, Player player) {
        List<Integer> moves = gameBoard.getMoves();
        Player maximisingPlayer = playerModel.getMaximisingPlayer();

        int bestScore = (maximisingPlayer.getPlayerValue() == player.getPlayerValue()) ?  Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestMove = -1;
        if( depth == 0 || moves.size() == 0 || isGameOver()){
            bestScore = heuristicValue();
        }else {
            for (int m : moves) {
                gameBoard.setMove(m, player.getPlayerValue());

                if (maximisingPlayer.getPlayerValue() == player.getPlayerValue()) {
                    int currentScore = minimax(depth - 1, playerModel.getOponed(player))[0];

                    if(currentScore > bestScore){
                        bestScore = currentScore;
                        bestMove = m;
                    }

                } else {
                    int currentScore = minimax( depth - 1, playerModel.getOponed(player))[0];

                    if(currentScore < bestScore){
                        bestScore = currentScore;
                        bestMove = m;
                    }
                }
                gameBoard.removeMove(m);
            }
        }
        return new int[] {bestScore, bestMove};
    }

    private int heuristicValue() {

        int score = 0;
        int maximising = playerModel.getMaximisingPlayer().getPlayerValue();
        int minimising = playerModel.getOponed(playerModel.getMaximisingPlayer()).getPlayerValue();

        for (int[] pattern : evaluationPatern){
            int countInLineMax = 0;
            int countInLineMin = 0;

            for (int pos : pattern){
                if(gameBoard.getBoard()[pos] == maximising) countInLineMax++;
                if(gameBoard.getBoard()[pos] == minimising) countInLineMin++;
            }

            score += (countInLineMax != 0) ? Math.pow(10, countInLineMax-1) : 0;
            score -= (countInLineMin != 0) ? Math.pow(10, countInLineMin-1) : 0;

        }

        return score;
    }

    public boolean isGameOver(){
        if (gameBoard.getMoves().size() == 0) return true;

        for (int player : new int[]{1,-1}) {
            for(int[] pos : evaluationPatern){
                if (    gameBoard.getBoard()[pos[0]] == player &&
                        gameBoard.getBoard()[pos[1]] == player &&
                        gameBoard.getBoard()[pos[2]] == player )
                    return true;
            }
        }
        return false;
        }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }


    public enum Mode{
        HUMANvsAI, HUMANvsHUMAN
    }
}
