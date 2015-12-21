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

        if(isgameOver() || depth == 0 || moves.size() == 0){
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
        int[] gb = getBoard();

        for (int player : new int[]{-1,1 }) {
            if (    gb[0] == player && gb[3] == player && gb[6] == player || // row 0
                    gb[1] == player && gb[4] == player && gb[7] == player || // row 1
                    gb[2] == player && gb[5] == player && gb[8] == player || // row 2

                    gb[0] == player && gb[1] == player && gb[2] == player || // column 0
                    gb[3] == player && gb[4] == player && gb[5] == player || // column 1
                    gb[6] == player && gb[7] == player && gb[8] == player || // column 2

                    gb[0] == player && gb[4] == player && gb[8] == player || // diagonal NW - SE
                    gb[2] == player && gb[4] == player && gb[6] == player)  // diagonal NE - SW
            {
                return (player == actualPlayer.getPlayerValue()) ? 1 : -1;
            }
        }

        return 0;
    }

    public boolean isgameOver(){
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

    public PlayerModel getPlayerModel() {
        return playerModel;
    }
}
