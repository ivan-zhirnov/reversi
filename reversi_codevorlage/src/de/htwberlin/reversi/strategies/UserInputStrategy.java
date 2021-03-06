package de.htwberlin.reversi.strategies;

import de.htwberlin.reversi.Board;

import java.util.Scanner;

/**
 * Strategy to determine move by prompting the user for input.
 */
public class UserInputStrategy implements ReversiStrategy {

    /**
     * Ask the user to enter a target field. Validate input and re-prompt until valid.
     */
    @Override
    public int[] getTarget(Board board, char tokenColor) {

        // TODO

        return new int[]{0,0};
    }


}
