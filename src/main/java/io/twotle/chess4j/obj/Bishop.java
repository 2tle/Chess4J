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
        //left-up
        int c = 1;
        while(Board.isInBoard(getX() -c, getY() - c)) {
            if(Board.isPositionAvailable(getX() - c, getY() -c,getColor())) {
                p.add(new Position(getX() - c, getY() - c));
                if (Board.isOpponent(getX() - c, getY() - c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //right-up
        c = 1;
        while(Board.isInBoard(getX() -c, getY() + c)) {
            if(Board.isPositionAvailable(getX() - c, getY() + c,getColor())) {
                p.add(new Position(getX() - c, getY() + c));
                if (Board.isOpponent(getX() - c, getY() + c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //left-down
        c = 1;
        while(Board.isInBoard(getX() + c, getY() - c)) {
            if(Board.isPositionAvailable(getX() + c, getY() -c,getColor())) {
                p.add(new Position(getX() + c, getY() - c));
                if (Board.isOpponent(getX() + c, getY() - c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //right-down
        c = 1;
        while(Board.isInBoard(getX() + c, getY() + c)) {
            if(Board.isPositionAvailable(getX() + c, getY() + c,getColor())) {
                p.add(new Position(getX() + c, getY() + c));
                if (Board.isOpponent(getX() + c, getY() + c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }
        return p;
    }


}
