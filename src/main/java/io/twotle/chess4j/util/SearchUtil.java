package io.twotle.chess4j.util;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.obj.Obj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class SearchUtil {
    public static int findObj(String name, ArrayList<Obj> objArrayList) {
        int index = -1;
        for(int x = 0; x < objArrayList.size(); x++) {
            if(objArrayList.get(x).getName().contains(name)) {
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

    public static ArrayList<Position> hashRemoveAll(ArrayList<Position> p1, ArrayList<Position> p2 ){
        HashSet<Position> p1h = new HashSet<>(p1);
        HashSet<Position> p2h = new HashSet<>(p2);
        p1h.removeAll(p2h);
        return new ArrayList<Position>(p1h);
    }

    public static ArrayList<Position> hashRetainAll(ArrayList<Position> p1, ArrayList<Position> p2) {
        HashSet<Position> p1h = new HashSet<>(p1);
        HashSet<Position> p2h = new HashSet<>(p2);
        p1h.retainAll(p2h);
        return new ArrayList<>(p1h);
    }

    public static int getRandomPawn(ArrayList<Obj> p) {
        ArrayList<Integer> randomInt = new ArrayList<>();
        for(int i = 0 ; i < p.size(); i ++) {
            if(p.get(i).getName().contains("Pawn")) randomInt.add(i);
        }
        Random r = new Random();
        return (randomInt.size() > 0) ? r.nextInt(randomInt.size()) : 0;

    }





}
