package ch.fhnw.tictactoe.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 */
public class GameBoardModel{
    public Player.Type[][] board;
    private Pos[][] poses;
    private Pos lastMove;
    private List<GameBoardListner> listners;


    public GameBoardModel() {
        board = new Player.Type[3][3];
        poses = new Pos[][]{
                {Pos.M00, Pos.M01, Pos.M02},
                {Pos.M10, Pos.M11, Pos.M12},
                {Pos.M20, Pos.M21, Pos.M22}};
    }

    public void resetBoard(){
        board = new Player.Type[3][3];
    }

    public void setMove(Pos pos, Player.Type type){

        switch (pos){
            case M00:
                board[0][0] = type;
                break;
            case M01:
                board[0][1] = type;
                break;
            case M02:
                board[0][2] = type;
                break;

            case M10:
                board[1][0] = type;
                break;
            case M11:
                board[1][1] = type;
                break;
            case M12:
                board[1][2] = type;
                break;

            case M20:
                board[2][0] = type;
                break;
            case M21:
                board[2][1] = type;
                break;
            case M22:
                board[2][2] = type;
                break;
        }
    }

    public Player.Type[][] getBoard() {
        return board;
    }

    public void setBoard(Player.Type[][] board) {
        this.board = board;
    }

    public Pos getNextMove(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; i < 3; j++){
                if (board[i][j] == null){
                    return poses[i][j];
                }
            }
        }
        return null;
    }

    public List<Pos> getMoves(){
        List<Pos> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == null){
                    moves.add(poses[i][j]);
                }

            }

        }
        return moves;
    }
    public void fireModelChanged(){
        listners.forEach(listner -> listner.gameBoardModelChanged());
    }

    public void addListner(GameBoardListner listner){
        listners.add(listner);
    }

    public Pos getLastMove() {
        return lastMove;
    }



    public enum Pos{
        M00, M01, M02,
        M10, M11, M12,
        M20, M21, M22
    }

    public GameBoardModel clone(){
        GameBoardModel gbClone = new GameBoardModel();
        Player.Type[][] cloneBoard = new Player.Type[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                cloneBoard[i][j] = this.board[i][j];
                }
            }


        gbClone.setBoard(cloneBoard);
        return gbClone;
    }


}
