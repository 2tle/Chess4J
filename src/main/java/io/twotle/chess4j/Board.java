package io.twotle.chess4j;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.error.NotFoundException;
import io.twotle.chess4j.obj.*;
import io.twotle.chess4j.players.Player;
import io.twotle.chess4j.util.SearchUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    public static Obj[][] boardObj = new Obj[8][8]; // 보드판 데이터 최종 관리


    public static ArrayList<Obj>[] moveableObj = new ArrayList[2];
    public static ArrayList<Obj>[] deadObj = new ArrayList[2];

    private Scanner scanner = new Scanner(System.in);



    public Board() {
        initialize();
    }

    public void initialize() {

        moveableObj[0] = new ArrayList<>();
        moveableObj[1] = new ArrayList<>();
        deadObj[0] = new ArrayList<>();
        deadObj[1] = new ArrayList<>();
        // 블랙말 및 화이트말 삽입

        //Pawn
        for(int i = 0; i<8;i++) {
            moveableObj[0].add(new Pawn(1,i,"Pawn"+i,0,5, true));
            moveableObj[1].add(new Pawn(6,i,"Pawn"+i, 1, 5, true));
        }

        //King
        moveableObj[0].add(new King(0,3,"King",0,0, true));
        moveableObj[1].add(new King(7,3,"King",1,0, true));
        //Queen
        moveableObj[0].add(new Queen(0,4,"Queen",0,1, true));
        moveableObj[1].add(new Queen(7,4,"Queen",1,1, true));
        //Bishop
        moveableObj[0].add(new Bishop(0,2,"Bishop1",0,3, true));
        moveableObj[0].add(new Bishop(0,5,"Bishop2",0,3, true));

        moveableObj[1].add(new Bishop(7,2,"Bishop1",1,3, true));
        moveableObj[1].add(new Bishop(7,5,"Bishop2",1,3, true));
        //Knight
        moveableObj[0].add(new Knight(0,1,"Knight1",0,4, true));
        moveableObj[0].add(new Knight(0,6,"Knight2",0,4, true));

        moveableObj[1].add(new Knight(7,1,"Knight1",1,4, true));
        moveableObj[1].add(new Knight(7,6,"Knight2",1,4, true));
        //Rook
        moveableObj[0].add(new Rook(0,0,"Rook1",0,3, true));
        moveableObj[0].add(new Rook(0,7,"Rook2",0,3, true));

        moveableObj[1].add(new Rook(7,0,"Rook1",1,3, true));
        moveableObj[1].add(new Rook(7,7,"Rook2",1,3, true));

    }

    public void render() {
        int linenumber = 8;
        System.out.println("   \ta\tb\tc\td\te\tf\tg\th");
        System.out.println("\t---------------------------------");
        for(int i = 0 ; i< 8; i++) {
            System.out.print("   "+linenumber+"|");
            for(int j = 0; j<8;j++) {
                if (boardObj[i][j] == null) {
                    System.out.print("\t|");
                } else {
                    System.out.print(" "+boardObj[i][j].toString()+" |");
                }
            }
            System.out.println(linenumber--);
        }
        System.out.println("\t---------------------------------");
        System.out.println("   \ta\tb\tc\td\te\tf\tg\th");
    }

    public static boolean isPositionAvailable(int x, int y) { //true -> null, false -> full
        return boardObj[x][y] == null;
    }

    public void move(Player player) {
        printMoveableList(player.getColor());
        String moveObj = inputMoveObj();
        int idx = SearchUtil.findObj(moveObj, moveableObj[player.getColor()]);
        if(idx == -1) {
            throw new NotFoundException(moveObj+" is not found");
        }
        ArrayList<Position> p = moveableObj[player.getColor()].get(idx).getMoveablePositionList();
        for( int i = 0; i< p.size() ; i++) {
            System.out.println((i+1)+". ("+p.get(i).getPosX()+","+p.get(i).getPosY()+")");
        }
        int moveIdx = inputLine("Choose Number >") - 1;
        if(boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()] != null) {
            deadObj[player.getColor()].add(boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()]);
            boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()] = null;
        }
        moveableObj[player.getColor()].get(idx).clear();
        moveableObj[player.getColor()].get(idx).setX(p.get(moveIdx).getX());
        moveableObj[player.getColor()].get(idx).setY(p.get(moveIdx).getY());
        moveableObj[player.getColor()].get(idx).commit();
    }

    private String inputMoveObj() {
        System.out.print("Choose Piece >");
        return scanner.next();
    }

    private int inputLine(String s) {
        System.out.print(s);
        return scanner.nextInt();

    }

    private void printMoveableList(int color) {
        moveableObj[color].forEach(item -> System.out.println(item.getName()+"("+item.getPosX()+","+item.getPosY()+")"));    }
}
