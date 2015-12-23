package ch.fhnw.tictactoe.logic;

/**
 * Created by fabirizio on 21.12.15.
 */
public class PlayerModel {
    Player x;
    Player o;
    Player nexPlaxerToMove;

    public PlayerModel() {
        this.x = new Player(1);
        this.o = new Player(-1);
        nexPlaxerToMove = x;
    }

    public Player getOponed(Player player){
        return (x.getPlayerValue() == player.getPlayerValue()) ? o : x;
    }

    public Player getNexPlaxerToMove() {
        return nexPlaxerToMove;
    }

    public void setNexPlaxerToMove(Player nexPlaxerToMove) {
        this.nexPlaxerToMove = nexPlaxerToMove;
    }

    public Player getX() {
        return x;
    }

    public Player getO() {
        return o;
    }

}
