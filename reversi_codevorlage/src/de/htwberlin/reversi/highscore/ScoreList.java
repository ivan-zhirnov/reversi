package de.htwberlin.reversi.highscore;

import java.util.ArrayList;
import java.util.List;

public class ScoreList {

    private final List<Player> scoreList = new ArrayList<>();


    /**
     * Adds player to list
     */
    public void addPlayer(Player player) {
        scoreList.add(player);
        scoreList.sort((p1, p2) ->
                p1.getMoves() - p2.getMoves() != 0 ? p1.getMoves() - p2.getMoves() : p1.getTokens() - p2.getTokens());
    }

    /**
     * Prints high-score
     */
    public void printList() {
        if (scoreList.isEmpty()) {
            System.out.println("Diese Liste ist leer");
        } else {
            System.out.printf("%-2s %-20s %-7s %-7s%n", "#", "Name", "Moves", "Tokens");
            for (int i = 0; i < scoreList.size(); i++) {
                System.out.printf("%-2d %-20s %-7d %-7d%n",
                        i + 1, scoreList.get(i).getName(), scoreList.get(i).getMoves(), scoreList.get(i).getTokens());
            }
        }
        System.out.println();
    }
}
