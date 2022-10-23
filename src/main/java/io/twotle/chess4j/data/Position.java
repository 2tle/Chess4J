package io.twotle.chess4j.data;

public class Position {
    private int x;
    private int y;
    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPosX() {
        return String.valueOf((char)(65+this.y));
    }

    public String getPosY() {
        return String.valueOf(8-this.x);
    }
}
