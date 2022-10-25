package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;

public class Knight extends Obj{
    public Knight(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♘" : "♞";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();
        if(Board.isPositionAvailable(getX() - 1, getY() - 2, getColor()))
            p.add(new Position(getX() - 1, getY() - 2));
        if(Board.isPositionAvailable(getX() -2, getY() - 1, getColor()))
            p.add(new Position(getX() -2, getY() -1));
        if(Board.isPositionAvailable(getX() -2, getY() + 1, getColor()))
            p.add(new Position(getX() - 2, getY() + 1));
        if(Board.isPositionAvailable(getX() - 1, getY() + 2, getColor()))
            p.add(new Position(getX() -1, getY() + 2));

        if(Board.isPositionAvailable(getX() + 1, getY() - 2, getColor()))
            p.add(new Position(getX() + 1, getY() - 2));
        if(Board.isPositionAvailable(getX() + 2, getY() - 1, getColor()))
            p.add(new Position(getX() + 2, getY() -1));
        if(Board.isPositionAvailable(getX() +2, getY() + 1, getColor()))
            p.add(new Position(getX() + 2, getY() + 1));
        if(Board.isPositionAvailable(getX() + 1, getY() + 2, getColor()))
            p.add(new Position(getX() +1, getY() + 2));


        return p;
    }

}
