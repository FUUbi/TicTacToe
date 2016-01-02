package ch.fhnw.tictactoe.logic;

import java.util.List;

/**
 * This class implements the game board, the player model,
 * the game mode and the evaluation pattern.
 */
public class Game {
    private final GameBoard gameBoard;
    private final PlayerModel playerModel;
    private final int[][] evaluationPattern;
    public Mode gameMode;

    /**
     * Creates a new game with a game board, a player model, game mode
     * and an array containing the evaluation pattern.
     */
    public Game() {
        gameBoard = new GameBoard();
        this.playerModel = new PlayerModel();
        evaluationPattern = new int[][]{
                {0,1,2}, {3,4,5}, {6,7,8},         //column 0  column 1  column 2
                {0,3,6},{1,4,7},{2,5,8},          //row 0 row 1 row 2
                {0,4,8},{2,4,6} };               // diagonal NW - SE  diagonal NE - SW

    }

    /**
     * Generates the best move for an artificial intelligent player and
     * sets the players value to the corresponding cell.
     * The opponent player is set as next player to move.
     * If player won after move, the players score will be iterated.
     * @param p the player to move
     * @param depth the steps to lookahead
     */
    public void moveAIPlayer(Player p, int depth){
        playerModel.setNextPlayerToMove(p);
        int pos = minimax(depth, p)[1];
        gameBoard.setMove(pos, p.getValue());
        playerModel.setNextPlayerToMove(playerModel.getOpponent(p));

        if(isPlayerWinner(p)){
            p.iterateScore();
        }
    }

    /**
     * Sets the players value on the board position
     * The opponent player is set as next player to move.
     * If player won after move, the players score will be iterated.
     * @param p the player to move
     * @param pos the position to set
     */
    public void moveHuman(Player p, int pos){
        playerModel.setNextPlayerToMove(p);
        gameBoard.setMove(pos, p.getValue());
        playerModel.setNextPlayerToMove(playerModel.getOpponent(p));

        if(isPlayerWinner(p)){
            p.iterateScore();
        }
    }

    /**
     * Recursive method calculates the best move for an AI player.
     * The method calls itself always with the opponent
     * @param depth the steps to lookahead
     * @param player the
     * @return an array containing the best score and the best move
     */
    private int[] minimax(int depth, Player player) {
        List<Integer> moves = gameBoard.getEmptyCells();
        Player maximisingPlayer = playerModel.getNextPlayerToMove();

        int bestScore = (maximisingPlayer.getValue() == player.getValue()) ?  Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestMove = -1;
        if( depth == 0 || moves.size() == 0 || isGameOver()){
            bestScore = heuristicValue();
        }else {
            for (int m : moves) {
                gameBoard.setMove(m, player.getValue());

                if (maximisingPlayer.getValue() == player.getValue()) {
                    int currentScore = minimax(depth - 1, playerModel.getOpponent(player))[0];

                    if(currentScore > bestScore){
                        bestScore = currentScore;
                        bestMove = m;
                    }

                } else {
                    int currentScore = minimax( depth - 1, playerModel.getOpponent(player))[0];

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

    /**
     * Evaluates the temporary game bord.
     * For all nine lines in the evaluation pattern and both players
     * the score is incremented or decremented by
     * + 100, + 10 , +1, 0 for 3, 2, 1, 0 in a line for the player to move
     * - 100, + 10 , -1, 0 for 3, 2, 1, 0 in a line for the opponent.
     * @return score for temporary game board
     */
    private int heuristicValue() {
        int score = 0;
        int maximising = playerModel.getNextPlayerToMove().getValue();
        int minimising = playerModel.getOpponent(playerModel.getNextPlayerToMove()).getValue();

        for (int[] pattern : evaluationPattern){
            int countInLineMax = 0;
            int countInLineMin = 0;

            for (int pos : pattern){
                if(gameBoard.getValues()[pos] == maximising) countInLineMax++;
                if(gameBoard.getValues()[pos] == minimising) countInLineMin++;
            }

            score += (countInLineMax != 0) ? Math.pow(10, countInLineMax-1) : 0;
            score -= (countInLineMin != 0) ? Math.pow(10, countInLineMin-1) : 0;

        }

        return score;
    }

    /**
     * Returns true if all cells are occupied or a player won.
     * @return true if all cells are occupied or a player won
     */
    public boolean isGameOver() {
        return gameBoard.getEmptyCells().size() == 0 ||
                isPlayerWinner(playerModel.getO()) || isPlayerWinner(playerModel.getX());
    }

    /**
     * Returns true if player won.
     * @param p the player to check
     * @return true if player won.
     */
    private boolean isPlayerWinner(Player p){
        int player = p.getValue();
        for(int[] pos : evaluationPattern){
            if (    gameBoard.getValues()[pos[0]] == player &&
                    gameBoard.getValues()[pos[1]] == player &&
                    gameBoard.getValues()[pos[2]] == player )
                return true;
        }
        return false;
    }

    /**
     * Returns the actual game board
     * @return the actual game board
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Returns the actual game player model
     * @return the actual game player model
     */
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    /**
     * Game modes that can be used.
     *  human versus artificial intelligent player
     *  human versus human
     */
    public enum Mode{
        HUMANvsAI, HUMANvsHUMAN
    }
}
