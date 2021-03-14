package de.htwberlin.reversi;


import de.htwberlin.reversi.strategies.MaxTokenStrategy;
import de.htwberlin.reversi.strategies.UserInputStrategy;

import java.util.Locale;
import java.util.Scanner;

public class ReversiApplication {

    private ReversiGame game;

    public static void main(String[] args) {
        ReversiApplication battleshipApplication = new ReversiApplication();
        battleshipApplication.mainMenu();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("***** ***** ********** ***** *****");
        System.out.println("***** *** PIRATE REVERSI *** *****");
        System.out.println("***** ***** ********** ***** *****");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("b")) {
            if (hasRunningGame()) {
                System.out.println("Spiel <F>ortsetzen");
            }
            System.out.println("Neues Spiel gegen <S>pieler");
            System.out.println("Neues Spiel gegen <C>omputer");
            System.out.println("<A>I-Match Computer gegen Computer");
            System.out.println("<B>eenden");
            System.out.print("Geben Sie einen Wert ein: ");
            input = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (input) {
                case "f":
                    if (hasRunningGame()) {
                        continueGame();
                    } else {
                        input = "";
                        System.out.println("Ungültiger Wert. Wiederholen Sie die Eingabe.\n");
                    }
                    break;
                case "s":
                    startNewGameVersusPlayer();
                    break;
                case "c":
                    startNewGameVersusComputer();
                    break;
                case "a":
                    startNewGameAI();
                    break;
                case "b":
                    break;
                default:
                    System.out.println("Ungültiger Wert. Wiederholen Sie die Eingabe.\n");
                    break;
            }
        }
    }

    private void mainMenu() {
        printMainMenu();
    }


    private boolean hasRunningGame() {
        return !(game == null || game.isFinished());
    }

    private void continueGame() {
        this.game.run();
    }

    private void startNewGameVersusComputer() {
        this.game = new ReversiGame(new UserInputStrategy(), new MaxTokenStrategy());
        continueGame();
    }

    private void startNewGameVersusPlayer() {
        this.game = new ReversiGame(new UserInputStrategy(), new UserInputStrategy());
        continueGame();
    }

    private void startNewGameAI() {
        this.game = new ReversiGame(new MaxTokenStrategy(), new MaxTokenStrategy());
        continueGame();
    }

}
