package io.twotle.obj;

import io.twotle.Board;

public class Obj {
    private int x,y; // x is a,b,c... | y is 1,2,3...
    private int color;
    private String name;
    private int kind;

    public Obj() {

    }

    public Obj(int x,int y,String name, int color, int kind, boolean isLive) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.kind = kind;
        if(isLive) Board.boardObj[x][y] = this;
    }

    public String toString() {
       return color+" "+kind;
    }


}
