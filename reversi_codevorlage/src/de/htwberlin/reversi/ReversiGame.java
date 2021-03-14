package de.htwberlin.reversi;

import de.htwberlin.reversi.strategies.ReversiStrategy;

/**
 * An instance of this class holds the state of a running match and the game logic.
 */
public class ReversiGame {

    private final Board board;
    private final ReversiStrategy playerRedStrategy;
    private final ReversiStrategy playerYellowStrategy;

    /**
     * Set to TRUE to keep the game loop running. Set to FALSE to exit.
     */
    boolean running;

    public ReversiGame(ReversiStrategy playerRedStrategy, ReversiStrategy playerYellowStrategy) {
        this.board = new Board();
        this.playerRedStrategy = playerRedStrategy;
        this.playerYellowStrategy = playerYellowStrategy;
    }

    /**
     * Main game loop. Keep running to play.
     * Interrupt the loop to get back to main menu.
     */
    public void run() {
        System.out.println("Spiel gestartet. Drücke ENTER während eines Zuges, im zum Hauptmenü zurückzukehren.");

        running = true;

        while (!isFinished()) {
            if (board.hasValidMoves(Board.RED)) {
                board.print();
                int[] targetRed = playerRedStrategy.getTarget(board, Board.RED);
                board.placeToken(targetRed, Board.RED);
            }

            if (board.hasValidMoves(Board.YELLOW)) {
                board.print();
                int[] targetYellow = playerYellowStrategy.getTarget(board, Board.YELLOW);
                board.placeToken(targetYellow, Board.YELLOW);
            }
        }

        int[] score = board.getCurrentScore();
        if (score[0] > score[1]) {
            System.out.println("X hat gewonnen.");
        } else if (score[0] < score[1]) {
            System.out.println("O hat gewonnen.");
        } else {
            System.out.println("Niemand hat gewonnen.");
        }
        System.out.println("X - " + score[0] + "    O - " + score[1]);
    }


    public boolean isFinished() {
        return !board.hasValidMoves(Board.RED) && !board.hasValidMoves(Board.YELLOW);
    }
}
