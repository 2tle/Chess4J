package io.twotle;

import io.twotle.obj.*;

import java.util.ArrayList;

public class Board {
    public static Obj[][] boardObj = new Obj[8][8]; // 보드판 데이터 최종 관리

    public static ArrayList<Obj> blackObj = new ArrayList<>(); // 블랙 유저 데이터 최종 관리

    public static ArrayList<Obj> deadBlack = new ArrayList<>(); // 뒈진거 관리

    public static ArrayList<Obj> whiteObj = new ArrayList<>(); // 화이트 유저 데이터 최종 관리

    public static ArrayList<Obj> deadWhite = new ArrayList<>();


    private int currentTurnColor;

    public Board() {
        initialize();
    }

    public void initialize() {
        // 블랙말 및 화이트말 삽입

        //Pawn
        for(int i = 0; i<8;i++) {
            blackObj.add(new Pawn(1,i,"Pawn"+i,0,5, true));
            whiteObj.add(new Pawn(6,i,"Pawn"+i, 1, 5, true));
        }

        //King
        blackObj.add(new King(0,3,"King",0,0, true));
        whiteObj.add(new King(7,3,"King",1,0, true));
        //Queen
        blackObj.add(new Queen(0,4,"Queen",0,1, true));
        whiteObj.add(new Queen(7,4,"Queen",1,1, true));
        //Bishop
        blackObj.add(new Bishop(0,2,"Bishop1",0,3, true));
        blackObj.add(new Bishop(0,5,"Bishop2",0,3, true));

        blackObj.add(new Bishop(7,2,"Bishop1",1,3, true));
        blackObj.add(new Bishop(7,5,"Bishop2",1,3, true));
        //Knight
        blackObj.add(new Knight(0,1,"Knight1",0,4, true));
        blackObj.add(new Knight(0,6,"Knight2",0,4, true));

        blackObj.add(new Knight(7,1,"Knight1",1,4, true));
        blackObj.add(new Knight(7,6,"Knight1",1,4, true));
        //Rook
        blackObj.add(new Rook(0,0,"Rook1",0,3, true));
        blackObj.add(new Rook(0,7,"Rook2",0,3, true));

        blackObj.add(new Rook(7,0,"Rook1",1,3, true));
        blackObj.add(new Rook(7,7,"Rook2",1,3, true));

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

    public void startGame(int GAME_MODE, int first) {
        this.currentTurnColor = first;
    }
}
