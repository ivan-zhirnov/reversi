package de.htwberlin.reversi.strategies;

import de.htwberlin.reversi.Board;

import java.util.concurrent.TimeUnit;

/**
 * Strategy to determine move by checking all valid moves and calculating the maximum number of flipped opponent tokens.
 */
public class MaxTokenStrategy implements ReversiStrategy {

    @Override
    public int[] getTarget(Board board, char tokenColor) {
       
    	int[] result = {0,0};
    	int maxCount = 0;

        int[][] validMoves = board.getValidMoves(tokenColor);
        for (int[] move: validMoves) {
            if (move == null)
                continue;
            int tmpCount = board.countFlipTokens(tokenColor, move);
            if (tmpCount > maxCount) {
                maxCount = tmpCount;
                result = move;
            }
        }

        System.out.println(tokenColor + " ist am Zug.");
       
        pause();

        return result;
    }

    private void pause() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
