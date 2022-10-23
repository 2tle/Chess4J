package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;

public class Bishop extends Obj{
    public Bishop(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♗" : "♝";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();

        return p;
    }


}
