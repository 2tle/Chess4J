package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;

public class King extends Obj{

    public King(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♔" : "♚";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() { // 보류
        ArrayList<Position> p = new ArrayList<>();
        if(getX() == 0) {
            if(getY() == 0) {
                //if(Board.isPositionAvailable())
            } else if(getY() == 7) {

            } else {

            }
        } else if(getX() == 7) {
            if(getY() == 0) {

            } else if(getY() == 7) {

            } else {

            }
        } else {
            if(Board.isPositionAvailable(getX() + 1, getY() + 1, getColor()))
                p.add(new Position(getX() + 1 , getY() + 1));

            if(Board.isPositionAvailable(getX() + 1, getY() - 1, getColor()))
                p.add(new Position(getX() + 1 , getY() - 1));

            if(Board.isPositionAvailable(getX() + 1, getY(), getColor()))
                p.add(new Position(getX() + 1 , getY()));

            if(Board.isPositionAvailable(getX() - 1, getY() + 1, getColor()))
                p.add(new Position(getX() - 1 , getY() + 1));

            if(Board.isPositionAvailable(getX() - 1, getY() - 1, getColor()))
                p.add(new Position(getX() - 1 , getY() - 1));

            if(Board.isPositionAvailable(getX() - 1, getY() , getColor()))
                p.add(new Position(getX() - 1 , getY()));

            if(Board.isPositionAvailable(getX() , getY() + 1, getColor()))
                p.add(new Position(getX() , getY() + 1));

            if(Board.isPositionAvailable(getX() , getY() - 1, getColor()))
                p.add(new Position(getX() , getY() - 1));
        }
        return p;
    }


}
