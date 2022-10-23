package io.twotle.chess4j.players;

public class Player {
    private String name;
    private int color;
    public Player() {

    }

    public Player(String name,  int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public int getColor() {
        return this.color;
    }

    public String getColorToString() {
        return (this.color == 0) ? "Black" : "White";
    }
}
