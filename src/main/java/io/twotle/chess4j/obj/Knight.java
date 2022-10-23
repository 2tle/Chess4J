package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;

public class Knight extends Obj{
    public Knight(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♘" : "♞";
    }
}
