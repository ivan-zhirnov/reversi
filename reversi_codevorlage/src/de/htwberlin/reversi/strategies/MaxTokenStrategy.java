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

        // TODO (kann beliebig geändert werden)
       

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
