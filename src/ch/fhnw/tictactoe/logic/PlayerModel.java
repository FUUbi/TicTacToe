package ch.fhnw.tictactoe.logic;

/**
 * This class implements player x and player o,
 * the first and the next player to move.
 */
public class PlayerModel {
    private final Player x;
    private Player o;
    private Player nextPlayerToMove;
    private Player firstPlayerToMove;

    /**
     * Creates a new player model with the default
     * player value 1 for player x and -1 for player o.
     * The first and the next player by default is x.
     */
    public PlayerModel() {
        this.x = new Player(1);
        this.o = new Player(-1);
        nextPlayerToMove = x;
        firstPlayerToMove = x;
    }

    /**
     * Returns the opponent player
     * @param player the player to get the opponent
     * @return the opponent player of the past player
     */
    public Player getOpponent(Player player){
        return (x.getValue() == player.getValue()) ? o : x;
    }

    /**
     * Returns the next player to move
     * @return the next player to move
     */
    public Player getNextPlayerToMove() {
        return nextPlayerToMove;
    }

    /**
     * Sets the next player to move
     * @param nextPlayerToMove the player to move
     */
    public void setNextPlayerToMove(Player nextPlayerToMove) {
        this.nextPlayerToMove = nextPlayerToMove;
    }

    /**
     * Returns the first player to move
     * @return the first player to move
     */
    public Player getFirstPlayerToMove() {
        return firstPlayerToMove;
    }

    /**
     * Sets the first player to move
     * @param firstPlayerToMove the first player to move
     */
    public void setFirstPlayerToMove(Player firstPlayerToMove) {
        this.firstPlayerToMove = firstPlayerToMove;
    }

    /**
     * Returns player x
     * @return the player x
     */
    public Player getX() {
        return x;
    }

    /**
     * Returns player o
     * @return the player o
     */
    public Player getO() {
        return o;
    }

}
