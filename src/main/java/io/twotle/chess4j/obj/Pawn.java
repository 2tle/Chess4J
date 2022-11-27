package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;

public class Pawn extends Obj{

    public Pawn(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive, 1);

    }
    @Override
    public String toString() {
        return (getColor() == 1) ? "♙" : "♟";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();
        //2box
        if(getColor() == 0 && getX() == 1) {
            if(Board.isPositionAvailable(getX() + 2, getY(), getColor())&& !Board.isKillAvailableForPawn(getX(), getY(),getColor()))
                p.add(new Position(getX() + 2, getY()));
            //p.add(new Position(getX() + 1, getY()));
        } else if(getColor() == 1 && getX() == 6) {
            if(Board.isPositionAvailable(getX() - 2, getY(), getColor())&& !Board.isKillAvailableForPawn(getX(), getY(),getColor()))
                p.add(new Position(getX() - 2, getY()));
            //p.add(new Position(getX() - 1, getY()));
        }

        //1box
        if(getColor() == 0) {
            if(Board.isPositionAvailable(getX() + 1, getY(), getColor()) && !Board.isKillAvailableForPawn(getX(), getY(),getColor()))
                p.add(new Position(getX() + 1, getY()));
        } else if(getColor() == 1) {
            if(Board.isPositionAvailable(getX() - 1, getY(), getColor()) && !Board.isKillAvailableForPawn(getX(), getY(),getColor()))
                p.add(new Position(getX() - 1, getY()));
        }

        //killOnly
        if(getColor() == 0) {
            if(getY() == 0) {
                if(Board.isKillAvailableForPawn(getX() + 1, getY() + 1 , getColor())) {
                    p.add(new Position(getX() + 1, getY() + 1));
                }

            } else if(getY() == 7) {
                if(Board.isKillAvailableForPawn(getX() + 1, getY() - 1  , getColor())) {
                    p.add(new Position(getX() + 1, getY() - 1));
                }

            } else {
                if(Board.isKillAvailableForPawn(getX() + 1, getY() - 1 , getColor())) {
                    p.add(new Position(getX() + 1, getY() - 1));
                }
                if(Board.isKillAvailableForPawn(getX() + 1 , getY() + 1 , getColor())) {
                    p.add(new Position(getX() + 1, getY() + 1));
                }

            }

        } else if(getColor() == 1) {
            if(getY() == 0) {
                if(Board.isKillAvailableForPawn(getX() - 1, getY() + 1, getColor())) {
                    p.add(new Position(getX() - 1, getY() + 1));
                }

            } else if(getY() == 7) {
                if(Board.isKillAvailableForPawn(getX() - 1, getY() - 1, getColor())) {
                    p.add(new Position(getX() - 1, getY() - 1));
                }

            } else {
                if(Board.isKillAvailableForPawn(getX() - 1, getY() - 1, getColor())) {
                    p.add(new Position(getX() - 1, getY() - 1));
                }
                if(Board.isKillAvailableForPawn(getX() - 1 , getY() + 1,getColor())) {
                    p.add(new Position(getX() - 1, getY() + 1));
                }

            }

        }

        return p;
    }
}
