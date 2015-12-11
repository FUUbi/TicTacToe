package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class Game {
    private GameBoardModel gameBoard;
    private Player player0;
    private Player player1;

    public Game(){
        gameBoard = new GameBoardModel();
        player0 = new Player(Player.Type.COMPUTER);
        player1 = new Player(Player.Type.HUMAN);
    }

    public void newRound(){
        gameBoard.resetBoard();
    }

    public int negmax(GameBoardModel gb, int depth, Player player){
        if(depth == 0 || gb.getMoves().size() == 0){
            return (int)(Math.random()*100); // return Heuristik value
        }
        int bestValue = -1000;
        List<GameBoardModel.Pos> moves = gb.getMoves();
        List<GameBoardModel> childBoards = new ArrayList<>();

        System.out.println(moves);
        for(GameBoardModel.Pos m : moves){
            System.out.println(m);
            GameBoardModel newGb = gb.clone();
            newGb.setMove(m, player.getType());
            childBoards.add(newGb);
        }
        System.out.println(childBoards);

        Player p = (Player.Type.COMPUTER == player.getType()) ? player0 : player1;

        for (GameBoardModel cB : childBoards){
            int val = -negmax(cB, depth, p);

            bestValue = Math.max(bestValue, val);

        }
        return bestValue;



    }

    public boolean gameOver(Player.Type type){
        //Sieg
        Player.Type[][] gb = gameBoard.board;
        if( gb[0][0] == type && gb[0][1] == type && gb[0][2] == type || // row 0
            gb[1][0] == type && gb[1][1] == type && gb[1][2] == type || // row 1
            gb[2][0] == type && gb[2][1] == type && gb[2][2] == type || // row 2

            gb[0][0] == type && gb[1][0] == type && gb[2][0] == type || // column 0
            gb[0][1] == type && gb[1][1] == type && gb[1][2] == type || // column 1
            gb[0][2] == type && gb[1][2] == type && gb[2][2] == type || // column 2

            gb[0][0] == type && gb[1][1] == type && gb[2][2] == type || // diagonal NW - SE
            gb[0][2] == type && gb[1][1] == type && gb[2][1] == type )  // diagonal NE - SW
        {
           return true;
        }
        return false;
    }




    public GameBoardModel getGameBoard() {
        return gameBoard;
    }
}
