package io.twotle.chess4j.util;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.obj.Obj;

import java.util.ArrayList;
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

    public static int findDeadObjByLocation(int x, int y, ArrayList<Obj> objArrayList) {
        int index = -1;
        for(int i = 0; i < objArrayList.size(); i++) {
            if(objArrayList.get(i).getX() == x && objArrayList.get(i).getY() == y) index = i;
        }
        return index;
    }

    public static ArrayList<Position> removeJungbok(ArrayList<Position> objArrayList) {
        for (int i = 0; i < objArrayList.size(); i++) {
            for (int j = 0; j < objArrayList.size(); j++) {
                if (i != j && objArrayList.get(j).getX() == objArrayList.get(i).getX() && objArrayList.get(j).getY() == objArrayList.get(i).getY() ) {
                    objArrayList.remove(j);
                }
            }
        }
        return objArrayList;

    }


}
