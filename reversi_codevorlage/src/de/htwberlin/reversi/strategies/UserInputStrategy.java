package de.htwberlin.reversi.strategies;

import de.htwberlin.reversi.Board;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Strategy to determine move by prompting the user for input.
 */
public class UserInputStrategy implements ReversiStrategy {

    /**
     * Ask the user to enter a target field. Validate input and re-prompt until valid.
     */
    @Override
    public int[] getTarget(Board board, char tokenColor) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String regex = "^([a-hA-H][1-8])$";
        int[] result = null;
        while (input.equals("")) {
            System.out.print("Geben Sie das Feld ein: ");
            input = scanner.nextLine();
            if (Pattern.matches(regex, input)) {
                result = Board.convertCoordinatesToInt(input);
            } else {
                input = "";
                System.out.println("Ungültiger Wert. Wiederholen Sie die Eingabe.\n");
            }
        }

        return result;
    }


}
