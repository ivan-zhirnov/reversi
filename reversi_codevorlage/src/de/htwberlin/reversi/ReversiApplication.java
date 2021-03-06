package de.htwberlin.reversi;


import de.htwberlin.reversi.strategies.MaxTokenStrategy;
import de.htwberlin.reversi.strategies.UserInputStrategy;

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
    }

    private void mainMenu() {
        printMainMenu();
        startNewGameVersusComputer();
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
