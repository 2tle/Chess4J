package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Obj {
    private int x,y; // x is a,b,c... | y is 1,2,3...
    private int color;
    private String name;
    private int kind;
    private int weight;

    public Obj() {

    }

    public Obj(int x,int y,String name, int color, int kind, boolean isLive, int weight) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.kind = kind;
        if(isLive) Board.boardObj[x][y] = this;
        this.weight = weight;
    }

    public String toString() {
       return color+" "+kind;
    }

    public String getName() {
        return this.name;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getColor() {
        return color;
    }

    public int getKind() {
        return kind;
    }

    public int getWeight() { return weight; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void clear() {
        Board.boardObj[this.x][this.y] = null;
    }

    public void commit() {
        Board.boardObj[this.x][this.y] = this;
    }

    public String getPosX() {
        return String.valueOf((char)(65+this.y));
    }

    public String getPosY() {
        return String.valueOf(8-this.x);
    }

    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();

        return p;
    }

    public ArrayList<Position> getCheckedObjRoute(King checked) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Obj) {
            Obj o = (Obj) obj;
            return getName().equals(o.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
