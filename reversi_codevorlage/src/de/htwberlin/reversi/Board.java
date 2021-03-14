package de.htwberlin.reversi;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.Objects;

public class Board {

    public static final char EMPTY = '.';
    public static final char RED = 'O';
    public static final char YELLOW = 'X';


    public static final int BOARD_SIZE = 8;
    private final char[][] fields = new char[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                fields[i][j] = EMPTY;
            }
        }

        fields[3][3] = RED;
        fields[4][4] = RED;
        fields[3][4] = YELLOW;
        fields[4][3] = YELLOW;
    }

    /**
     * Prints the board to System.out
     */
    public void print() {
        System.out.println();
        /* print column headers A - H */
        System.out.print("# ");
        for (int x = 0; x < fields[0].length; x++) {
            char column = (char) (x + 65);
            System.out.print(" " + column);
        }
        System.out.println();

        for (int y = 0; y < fields.length; y++) {
            /* print row number */
            int rowNumber = y + 1;
            System.out.print(rowNumber + " ");
            if (rowNumber < 10) System.out.print(" ");

            /* print row */
            for (int x = 0; x < fields[y].length; x++) {
                char output = fields[x][y];
                System.out.print(output + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Place a token in the given color on the given target field.
     * Flip adjacent opponent's tokens according to the rules.
     *
     * @throws RuntimeException if the move is invalid
     */
    public void placeToken(int[] target, char tokenColor) {
        int x = target[0];
        int y = target[1];

        if (fields[x][y] != EMPTY) {
            throw new RuntimeException("Field occupied: " + Arrays.toString(target));
        }
        if (!isValidMove(target, tokenColor)) {
            throw new RuntimeException("Invalid move: " + Arrays.toString(target));
        }

        int tokensFlipedCount = 0;

        // place token
        fields[x][y] = tokenColor;
        boolean isFlipAvailable = false;
        // flip tokens horizontal if applicable
        // flip left
        if (x > 1 && fields[x-1][y] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = x - 2; i >= 0; i--) {
                if (fields[i][y] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempX = x - 1;
                while (fields[tempX][y] == opponent(tokenColor)) {
                    fields[tempX][y] = tokenColor;
                    tokensFlipedCount++;
                    tempX--;
                }
            }
        }
        //right
        if (x < 6 && fields[x+1][y] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = x + 2; i < BOARD_SIZE; i++) {
                if (fields[i][y] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempX = x + 1;
                while (fields[tempX][y] == opponent(tokenColor)) {
                    fields[tempX][y] = tokenColor;
                    tokensFlipedCount++;
                    tempX++;
                }
            }
        }
        // flip tokens vertical if applicable
        //up
        if (y > 1 && fields[x][y-1] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = y - 2; i >= 0; i--) {
                if (fields[x][i] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempY = y - 1;
                while (fields[x][tempY] == opponent(tokenColor)) {
                    fields[x][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempY--;
                }
            }
        }
        //down
        if (y < 6 && fields[x][y+1] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = y + 2; i < BOARD_SIZE; i++) {
                if (fields[x][i] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempY = y + 1;
                while (fields[x][tempY] == opponent(tokenColor)) {
                    fields[x][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempY++;
                }
            }
        }
        // flip tokens diagonal if applicable
        //left up
        if (x > 1 && y > 1 && fields[x-1][y-1] == opponent(tokenColor)) {
            int tempX = x - 2;
            int tempY = y - 2;
            isFlipAvailable = false;
            while (tempX >= 0 && tempY >= 0) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX--;
                tempY--;
            }
            if (isFlipAvailable) {
                tempX = x - 1;
                tempY = y - 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    fields[tempX][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempX--;
                    tempY--;
                }
            }
        }
        //left down
        if (x > 1 && y < 6 && fields[x-1][y+1] == opponent(tokenColor)) {
            int tempX = x - 2;
            int tempY = y + 2;
            isFlipAvailable = false;
            while (tempX >= 0 && tempY < BOARD_SIZE) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX--;
                tempY++;
            }
            if (isFlipAvailable) {
                tempX = x - 1;
                tempY = y + 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    fields[tempX][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempX--;
                    tempY++;
                }
            }
        }
        //right up
        if (x < 6 && y > 1 && fields[x+1][y-1] == opponent(tokenColor)) {
            int tempX = x + 2;
            int tempY = y - 2;
            isFlipAvailable = false;
            while (tempX < BOARD_SIZE && tempY >= 0) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX++;
                tempY--;
            }
            if (isFlipAvailable) {
                tempX = x + 1;
                tempY = y - 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    fields[tempX][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempX++;
                    tempY--;
                }
            }
        }
        //right down
        if (x < 6 && y < 6 && fields[x+1][y+1] == opponent(tokenColor)) {
            int tempX = x + 2;
            int tempY = y + 2;
            isFlipAvailable = false;
            while (tempX < BOARD_SIZE && tempY < BOARD_SIZE) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX++;
                tempY++;
            }
            if (isFlipAvailable) {
                tempX = x + 1;
                tempY = y + 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    fields[tempX][tempY] = tokenColor;
                    tokensFlipedCount++;
                    tempX++;
                    tempY++;
                }
            }
        }

        System.out.println(tokensFlipedCount + " Steine umgedreht");
        int[] score = getCurrentScore();
        System.out.println("X - " + score[0] + "    O - " + score[1]);
    }

    /**
     * Get the color of the opponent's token
     *
     * @param tokenColor the players token color
     * @return YELLOW, if opponent is RED. RED otherwise.
     */
    private char opponent(char tokenColor) {
        return tokenColor == RED ? YELLOW : RED;
    }

    /**
     * Validate that the given target is a valid move for the given color
     *
     * @return TRUE if the target is unoccupied, within the bounds of the field and enabled flipping of opponent tokens
     */
    public boolean isValidMove(int[] target, char tokenColor) {
        int x = target[0];
        int y = target[1];

        if (fields[x][y] == EMPTY) {
            //left
            if (x > 1 && fields[x-1][y] == opponent(tokenColor)) {
                for (int i = x - 2; i >= 0; i--) {
                    if (fields[i][y] == tokenColor)
                        return true;
                }
            }

            //right
            if (x < 6 && fields[x+1][y] == opponent(tokenColor)) {
                for (int i = x + 2; i < BOARD_SIZE; i++) {
                    if (fields[i][y] == tokenColor)
                        return true;
                }
            }

            //up
            if (y > 1 && fields[x][y-1] == opponent(tokenColor)) {
                for (int i = y - 2; i >= 0; i--) {
                    if (fields[x][i] == tokenColor)
                        return true;
                }
            }

            //down
            if (y < 6 && fields[x][y+1] == opponent(tokenColor)) {
                for (int i = y + 2; i < BOARD_SIZE; i++) {
                    if (fields[x][i] == tokenColor)
                        return true;
                }
            }

            //left up
            if (x > 1 && y > 1 && fields[x-1][y-1] == opponent(tokenColor)) {
                int i = x - 2;
                int j = y - 2;
                while (i >= 0 && j >= 0) {
                    if (fields[i][j] == tokenColor)
                        return true;
                    i--;
                    j--;
                }
            }

            //left down
            if (x > 1 && y < 6 && fields[x-1][y+1] == opponent(tokenColor)) {
                int i = x - 2;
                int j = y + 2;
                while (i >= 0 && j < BOARD_SIZE) {
                    if (fields[i][j] == tokenColor)
                        return true;
                    i--;
                    j++;
                }
            }

            //right up
            if (x < 6 && y > 1 && fields[x+1][y-1] == opponent(tokenColor)) {
                int i = x + 2;
                int j = y - 2;
                while (i < BOARD_SIZE && j >= 0) {
                    if (fields[i][j] == tokenColor)
                        return true;
                    i++;
                    j--;
                }
            }

            //right down
            if (x < 6 && y < 6 && fields[x+1][y+1] != tokenColor) {
                int i = x + 2;
                int j = y + 2;
                while (i < BOARD_SIZE && j < BOARD_SIZE) {
                    if (fields[i][j] == tokenColor)
                        return true;
                    i++;
                    j++;
                }
            }
        }
        return false;
    }

    /**
     * Get all valid moves for the player with the given color
     * @return an array of moves. each move is an int[] of length 2 with x and y value. the array of moves may have trailing empty fields.
     */
    public int[][] getValidMoves(char tokenColor) {
        int[][] validMoves = new int[64][];
        int counter = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int[] field = {i, j};
                if (isValidMove(field, tokenColor)) {
                    validMoves[counter] = field;
                    counter++;
                }
            }
        }

        return validMoves;
    }


    /**
     * Converts alphanumeric board coordinates to array indexes, e.g. A1 to [0,0]
     */
    public static int[] convertCoordinatesToInt(String input) {
        int x = input.toUpperCase().charAt(0) - 65;
        int y = Integer.parseInt(input.substring(1)) - 1;
        return new int[]{x, y};
    }

    /**
     * Converts array indexes to ahlphanumeric board coordinates, e.g. [0,0] to A1
     */
    public static String convertCoordinatesToString(int[] input) {
        char x = (char) (input[0] + 65);
        String y = Integer.toString(input[1] + 1);
        return x + y;
    }

    public boolean hasValidMoves(char tokenColor) {
        int[][] validMoves = getValidMoves(tokenColor);
        for (int [] move: validMoves) {
            if (move != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Counts tokens that will be flipped in this move
     */
    public int countFlipTokens(char tokenColor, int[] target) {
        int x = target[0];
        int y = target[1];
        int result = 0;
        boolean isFlipAvailable = false;
        //left
        if (x > 1 && fields[x-1][y] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = x - 2; i >= 0; i--) {
                if (fields[i][y] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempX = x - 1;
                while (fields[tempX][y] == opponent(tokenColor)) {
                    result++;
                    tempX--;
                }
            }
        }
        //right
        if (x < 6 && fields[x+1][y] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = x + 2; i < 8; i++) {
                if (fields[i][y] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempX = x + 1;
                while (fields[tempX][y] == opponent(tokenColor)) {
                    result++;
                    tempX++;
                }
            }
        }
        //up
        if (y > 1 && fields[x][y-1] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = y - 2; i >= 0; i--) {
                if (fields[x][i] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempY = y - 1;
                while (fields[x][tempY] == opponent(tokenColor)) {
                    result++;
                    tempY--;
                }
            }
        }
        //down
        if (y < 6 && fields[x][y+1] == opponent(tokenColor)) {
            isFlipAvailable = false;
            for (int i = y + 2; i < 8; i++) {
                if (fields[x][i] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
            }
            if (isFlipAvailable) {
                int tempY = y + 1;
                while (fields[x][tempY] == opponent(tokenColor)) {
                    result++;
                    tempY++;
                }
            }
        }
        //left up
        if (x > 1 && y > 1 && fields[x-1][y-1] == opponent(tokenColor)) {
            int tempX = x - 2;
            int tempY = y - 2;
            isFlipAvailable = false;
            while (tempX >= 0 && tempY >= 0) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX--;
                tempY--;
            }
            if (isFlipAvailable) {
                tempX = x - 1;
                tempY = y - 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    result++;
                    tempX--;
                    tempY--;
                }
            }
        }
        //left down
        if (x > 1 && y < 6 && fields[x-1][y+1] == opponent(tokenColor)) {
            int tempX = x - 2;
            int tempY = y + 2;
            isFlipAvailable = false;
            while (tempX >= 0 && tempY < 8) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX--;
                tempY++;
            }
            if (isFlipAvailable) {
                tempX = x - 1;
                tempY = y + 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    result++;
                    tempX--;
                    tempY++;
                }
            }
        }
        //right up
        if (x < 6 && y > 1 && fields[x+1][y-1] == opponent(tokenColor)) {
            int tempX = x + 2;
            int tempY = y - 2;
            isFlipAvailable = false;
            while (tempX < 8 && tempY >= 0) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX++;
                tempY--;
            }
            if (isFlipAvailable) {
                tempX = x + 1;
                tempY = y - 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    result++;
                    tempX++;
                    tempY--;
                }
            }
        }
        //right down
        if (x < 6 && y < 6 && fields[x+1][y+1] == opponent(tokenColor)) {
            int tempX = x + 2;
            int tempY = y + 2;
            isFlipAvailable = false;
            while (tempX < 8 && tempY < 8) {
                if (fields[tempX][tempY] == tokenColor) {
                    isFlipAvailable = true;
                    break;
                }
                tempX++;
                tempY++;
            }
            if (isFlipAvailable) {
                tempX = x + 1;
                tempY = y + 1;
                while (fields[tempX][tempY] == opponent(tokenColor)) {
                    result++;
                    tempX++;
                    tempY++;
                }
            }
        }

        return result;
    }

    int[] getCurrentScore() {
        int yellowCount = 0;
        int redCount = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (fields[i][j] == YELLOW)
                    yellowCount++;
                else if (fields[i][j] == RED)
                    redCount++;
            }
        }
        return new int[] {yellowCount, redCount};
    }
}
