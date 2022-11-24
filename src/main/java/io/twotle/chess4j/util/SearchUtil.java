package io.twotle.chess4j.util;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.obj.Obj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class SearchUtil {
    public static int findObj(String name, ArrayList<Obj> objArrayList) {
        int index = -1;
        for(int x = 0; x < objArrayList.size(); x++) {
            if(Objects.equals(objArrayList.get(x).getName(), name)) {
                index = x;
                break;
            }
        }
        return index;
    }

    public static int findObjByLocation(int x, int y, ArrayList<Obj> objArrayList) {
        int index = -1;
        for(int i = 0; i < objArrayList.size(); i++) {
            if(objArrayList.get(i).getX() == x && objArrayList.get(i).getY() == y) index = i;
        }
        return index;
    }

    public static int findObjByPos(int x, int y, ArrayList<Position> p) {
        int index = -1;
        for(int i = 0; i < p.size(); i++ ) {
            if(p.get(i).getX() == x && p.get(i).getY() == y) index = i;
        }
        return index;
    }

    public static ArrayList<Position> removeJungbok(ArrayList<Position> objArrayList) {
        HashSet<Position> p = new HashSet<>(objArrayList);
        return new ArrayList<>(p);
    }


}
