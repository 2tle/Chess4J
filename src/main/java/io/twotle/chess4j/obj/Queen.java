package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;

public class Queen extends Obj{
    public Queen(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♕" : "♛";
    }
}