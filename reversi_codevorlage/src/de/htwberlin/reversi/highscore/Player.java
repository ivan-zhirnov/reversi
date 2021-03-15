package de.htwberlin.reversi.highscore;

public class Player {
    private final String name;
    private final int moves;
    private final int tokens;

    public Player(String name, int moves, int tokens) {
        this.name = name;
        this.moves = moves;
        this.tokens = tokens;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public int getTokens() {
        return tokens;
    }

}
