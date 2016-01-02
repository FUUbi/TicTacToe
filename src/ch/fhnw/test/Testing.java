package ch.fhnw.test;

import ch.fhnw.tictactoe.logic.Game;
import ch.fhnw.tictactoe.logic.Player;

/**
 * This class preforms a test of game tic tac toe.
 * Rules to test:
 *  Rule 1: If AI has a winning move, take it.
 *  Rule 2: If the opponent has a winning move, block it.
 *  Rule 3: If AI can create a fork (two winning ways)
 *          after this move, do it.
 */
public class Testing {
    public static void main(String[] args) {
        Game game = new Game();
        Player x = game.getPlayerModel().getX();
        System.out.println("Test if player x value is 1:");
        printTestStatus(x.getValue() == 1);

        // Rule 1: If AI has a winning move, take it.
        System.out.println("Testing Rule 1:\n " +
                "If AI has a winning move, take it.");
        game.getGameBoard().setValues(
                new int[]{
                         1,  0, -1,
                         1, -1,  0,
                         0,  0,  0});

        game.getPlayerModel().setNextPlayerToMove(x);
        game.moveAIPlayer(x, 9);
        printTestStatus(game.getGameBoard().getLastMove() == 6);

        //Rule 2: If the opponent has a winning move, block it.
        System.out.println("Testing Rule 2:\n " +
                "If the opponent has a winning move, block it.");
        game.getGameBoard().setValues(
                new int[]{
                        1,  1, -1,
                        0, -1,  0,
                        0,  0,  0});

        game.getPlayerModel().setNextPlayerToMove(x);
        game.moveAIPlayer(x, 9);
        printTestStatus(game.getGameBoard().getLastMove() == 6);

        //Rule 3: If AI can create a fork (two winning ways)
        //        after this move, do it.
        System.out.println("Testing Rule 3:\n " +
                "If AI can create a fork (two winning ways)" +
                "after this move, do it.");
        game.getGameBoard().setValues(
                new int[]{
                        1, -1, -1,
                        0,  1,  0,
                        0,  0,  0});

        game.getPlayerModel().setNextPlayerToMove(x);
        game.moveAIPlayer(x, 9);
        printTestStatus(game.getGameBoard().getLastMove() == 3 ||
                        game.getGameBoard().getLastMove() == 5 ||
                        game.getGameBoard().getLastMove() == 6
        );

    }

    private static void printTestStatus(Boolean state) {
        if(state){
            System.out.println("passed successfully\n");
        }else {
            System.out.println("failed test\n");
        }
    }

}
