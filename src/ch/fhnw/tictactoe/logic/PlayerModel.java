package ch.fhnw.tictactoe.logic;

/**
 * Created by fabirizio on 21.12.15.
 */
public class PlayerModel {
    Player x;
    Player o;
    Player turn;

    public PlayerModel() {
        this.x = new Player(Player.Type.X);
        this.o = new Player(Player.Type.O);
    }

    public Player getOponed(Player player){
        return (player.getType() == Player.Type.X) ? o : x;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public Player getX() {
        return x;
    }

    public void setX(Player x) {
        this.x = x;
    }

    public Player getO() {
        return o;
    }

    public void setO(Player o) {
        this.o = o;
    }
}
