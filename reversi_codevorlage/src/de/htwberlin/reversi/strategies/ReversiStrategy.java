package de.htwberlin.reversi.strategies;

import de.htwberlin.reversi.Board;

/**
 * Interface for de.htwberlin.reversi.strategies to determine moves.
 */
public interface ReversiStrategy {

    /**
     * Determine the target move according to the implemented strategy
     * @param board on which the move is made
     * @param tokenColor of the player who moves
     * @return the target field as int[] with x and y coordinate
     */
    int[] getTarget(Board board, char tokenColor);
}
