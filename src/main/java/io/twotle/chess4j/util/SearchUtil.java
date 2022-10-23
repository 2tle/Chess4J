package io.twotle.chess4j.util;

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

}
