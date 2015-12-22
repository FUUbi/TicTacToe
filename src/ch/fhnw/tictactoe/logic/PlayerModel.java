package ch.fhnw.tictactoe.logic;

/**
 * Created by fabirizio on 21.12.15.
 */
public class PlayerModel {
    Player x;
    Player o;
    Player maximisingPlayer;

    public PlayerModel() {
        this.x = new Player(1);
        this.o = new Player(-1);
        maximisingPlayer = x;
    }

    public Player getOponed(Player player){
        return (x.getPlayerValue() == player.getPlayerValue()) ? o : x;
    }

    public Player getMaximisingPlayer() {
        return maximisingPlayer;
    }

    public void setMaximisingPlayer(Player maximisingPlayer) {
        this.maximisingPlayer = maximisingPlayer;
    }

    public Player getX() {
        return x;
    }

    public Player getO() {
        return o;
    }

}
