package io.twotle.chess4j;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.error.NotFoundException;
import io.twotle.chess4j.obj.*;
import io.twotle.chess4j.players.Player;
import io.twotle.chess4j.util.SearchUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Board {
    public static Obj[][] boardObj = new Obj[8][8]; // 보드판 데이터 최종 관리

    public static ArrayList<Obj>[] checkingObj = new ArrayList[2];

    private ArrayList<Obj>[] moveableObj = new ArrayList[2];
    private ArrayList<Obj>[] deadObj = new ArrayList[2];

    private Scanner scanner = new Scanner(System.in);

    private int turn = 0;
    private int musungbu = 0;

    private int checkX = 0;
    private int checkY = 0;

    private boolean isPawnMove = false;
    private boolean isObjDead = false;


    public static int[][] castlingAvailable = new int[2][2];

    private boolean isPlayer1Checked = false;
    private boolean isPlayer2Checked = false;



    public Board() {
        initialize();
    }

    public void resetFlag() {
        isPawnMove = false;
        isObjDead = false;
    }



    public void initialize() {

        moveableObj[0] = new ArrayList<>();
        moveableObj[1] = new ArrayList<>();
        deadObj[0] = new ArrayList<>();
        deadObj[1] = new ArrayList<>();


        checkingObj[0] = new ArrayList<>();
        checkingObj[1] = new ArrayList<>();

        castlingAvailable[0][0] = 0; // black's left rook castling
        castlingAvailable[0][1] = 0; // black's right rook castling
        castlingAvailable[1][0] = 0; // white's left rook castling
        castlingAvailable[1][1] = 0; // white's right rook castling
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

    public static boolean isPositionAvailable(int x, int y, int color) { //true -> null, false -> full
        try {
            if(boardObj[x][y] == null) return true;
            else {
                 return boardObj[x][y].getColor() != color;
            }
        } catch ( Exception e) {
            return false;
        }
    }

    public Position isPromotion(int color) {
        if(color == 0) {
            //dest is 7?
            for(int i = 0; i < 8 ; i++) {
                if(boardObj[7][i] != null && boardObj[7][i] instanceof Pawn&& boardObj[7][i].getColor()==color) {
                    return new Position(7,i);
                }
            }
        } else if ( color == 1 ) {
            //dest is 0?
            for(int i = 0; i < 8 ; i++) {
                if(boardObj[0][i] != null && boardObj[0][i] instanceof Pawn&& boardObj[0][i].getColor()==color) {
                    return new Position(0,i);
                }
            }
        }
        throw new NotFoundException("No Promotion");
    }

    public void doPromotion(Position p, int userChoice, int color) {
        int idx = SearchUtil.findObjByLocation(p.getX(),p.getY(),this.moveableObj[color]);
        this.moveableObj[color].remove(idx);
        Obj obj = null;
        switch (userChoice) {
            case 1:
                //b
                obj = new Bishop(p.getX(), p.getY(),"Bishop"+turn,color,3,true);
                break;
            case 2:
                //k
                obj = new Knight(p.getX(), p.getY(),"Bishop"+turn,color,3,true);
                break;
            case 3:
                //r
                obj = new Rook(p.getX(), p.getY(),"Bishop"+turn,color,3,true);
                break;
            case 4:
                //q
                obj = new Queen(p.getX(), p.getY(),"Bishop"+turn,color,3,true);
                break;
        }
        this.moveableObj[color].add(obj);
    }

    public static boolean isPositionAvailableForPawn(int x, int y, int color) {
        try {
            return boardObj[x][y] == null;
        } catch ( Exception e) {
            return false;
        }
    }

    public Obj getObjByPos(int x, int y, int color) {
        int idx = SearchUtil.findObjByLocation(x,y,moveableObj[color]);
        return  moveableObj[color].get(idx);
    }

    public ArrayList<Position> kingCross(int color) {
        return moveableObj[color].get(SearchUtil.findObj("King", moveableObj[color])).getMoveablePositionList();
    }

    public ArrayList<Obj> getAllMovable(int color) {
        ArrayList<Obj> objs = new ArrayList<>();
        for(int i = 0; i < moveableObj[color].size(); i ++) {
            if(moveableObj[color].get(i).getMoveablePositionList().size() > 0)
                objs.add(moveableObj[color].get(i));
        }
        //return moveableObj[color];
        return objs;
    }

    public ArrayList<Position> getAllPos(int color ){
        ArrayList<Position> p =new ArrayList<>();
        for(int i =0;i<moveableObj[color].size(); i++) {
            p.add(new Position(moveableObj[color].get(i).getX(), moveableObj[color].get(i).getY()));
        }
        return p;
    }

    public ArrayList<Obj> getCheckObjectCount(int color) {
        ArrayList<Obj> o = new ArrayList<>();
        Obj king = moveableObj[color].get(SearchUtil.findObj("King", moveableObj[color]));
        ArrayList<Obj> opponentPos = getAllMovable((color == 0) ? 1 : 0);
        for(int i = 0; i < opponentPos.size(); i++) {
            int idx = SearchUtil.findObjByPos(king.getX(),king.getY(),opponentPos.get(i).getMoveablePositionList());
            if(idx != -1) {
                o.add(opponentPos.get(i));
            }
        }
        return o;

    }
    public King getKing(int color ){
        return (King) moveableObj[color].get(SearchUtil.findObj("King", moveableObj[color]));
    }

    public ArrayList<Position> getCheckingRouteList(Obj checkingObj, King checkedKing) {
        ArrayList<Position> p = checkingObj.getCheckedObjRoute(checkedKing);
        return p;
    }


    public Obj getMinObj(int color) {
        int max = 1000000;
        Obj obj = null;
        for(int i = 0; i < moveableObj[color].size(); i++) {
            if(moveableObj[color].get(i).getWeight() < max) {
                obj = moveableObj[color].get(i);
            }
        }
        return obj;
    }
    public Obj getMaxObj(int color) {
        int min = 0;
        Obj obj = null;
        for(int i = 0; i < moveableObj[color].size(); i++) {
            if(moveableObj[color].get(i).getWeight() > min) {
                obj = moveableObj[color].get(i);
            }
        }
        return obj;
    }

    public static boolean isKillAvailableForPawn(int x, int y, int color) {
        try {
            //if(boardObj[x][y].getColor() != color) return true;
            return boardObj[x][y].getColor() != color && boardObj[x][y] != null;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean isOpponent(int x, int y, int color) {
        try {
            return boardObj[x][y].getColor() != color;
        } catch(Exception e) {
            return false;
        }

    }

    public static boolean isInBoard(int x,int y) {
        if(x >=0 && x<= 7 && y >= 0 && y <= 7) return true;
        else return false;
    }

    public int move(Player player, String moveObj) {
        //printMoveableList(player.getColor());
        if(player.getColor() == 0) {
            if(isPlayer1Checked && moveObj.equals("King")) {
                return -1;
            }
        } else if(player.getColor() == 1) {
            if(isPlayer2Checked && moveObj.equals("King")) {
                return -1;
            }
        }



        //String moveObj = inputMoveObj();
        int idx = SearchUtil.findObj(moveObj, moveableObj[player.getColor()]);
        //if(moveableObj[player.getColor()].get(idx))
        //if(is)
        if(idx == -1) {
            throw new NotFoundException(moveObj+" is not found");
        }
        ArrayList<Position> p = moveableObj[player.getColor()].get(idx).getMoveablePositionList();
        if(p.size() == 0) {
            throw new NotFoundException("No movable location");
        }
        for( int i = 0; i< p.size() ; i++) {
            System.out.println((i+1)+". ("+p.get(i).getPosX()+","+p.get(i).getPosY()+")");
        }
        int moveIdx = inputLine("Choose Number >") - 1;
        if(moveIdx < 0 || moveIdx >= p.size()) throw new IndexOutOfBoundsException("No index");

        if(boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()] != null && boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()].getColor() != player.getColor()) {
            deadObj[player.getColor()].add(boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()]);
            int otherColor = (player.getColor() == 0) ? 1 : 0 ;
            int removeIdx = SearchUtil.findObjByLocation(p.get(moveIdx).getX(), p.get(moveIdx).getY(), moveableObj[otherColor]);
            boardObj[p.get(moveIdx).getX()][p.get(moveIdx).getY()] = null;
            moveableObj[otherColor].remove(removeIdx);
            musungbu =0;

        } else if(moveableObj[player.getColor()].get(idx) instanceof King) {
            if(player.getColor() == 0) {
                if(p.get(moveIdx).getX() == 0 && p.get(moveIdx).getY() == 1) {
                    int rook = SearchUtil.findObjByLocation(0,0,moveableObj[0]);
                    moveableObj[0].get(rook).clear();
                    moveableObj[0].get(rook).setX(0);
                    moveableObj[0].get(rook).setY(2);
                    moveableObj[0].get(rook).commit();
                } else if(p.get(moveIdx).getX() == 0 && p.get(moveIdx).getY() == 5) {
                    int rook = SearchUtil.findObjByLocation(0,7,moveableObj[0]);
                    moveableObj[0].get(rook).clear();
                    moveableObj[0].get(rook).setX(0);
                    moveableObj[0].get(rook).setY(4);
                    moveableObj[0].get(rook).commit();
                }
            } else {
                if(p.get(moveIdx).getX() == 7 && p.get(moveIdx).getY() == 1) {
                    int rook = SearchUtil.findObjByLocation(7,0,moveableObj[1]);
                    moveableObj[1].get(rook).clear();
                    moveableObj[1].get(rook).setX(7);
                    moveableObj[1].get(rook).setY(2);
                    moveableObj[1].get(rook).commit();

                } else if(p.get(moveIdx).getX() == 7 && p.get(moveIdx).getY() == 5) {
                    int rook = SearchUtil.findObjByLocation(0,7,moveableObj[1]);
                    moveableObj[1].get(rook).clear();
                    moveableObj[1].get(rook).setX(7);
                    moveableObj[1].get(rook).setY(4);
                    moveableObj[1].get(rook).commit();
                }

            }
        }
        moveableObj[player.getColor()].get(idx).clear();
        moveableObj[player.getColor()].get(idx).setX(p.get(moveIdx).getX());
        moveableObj[player.getColor()].get(idx).setY(p.get(moveIdx).getY());
        moveableObj[player.getColor()].get(idx).commit();

        if(moveableObj[player.getColor()].get(idx) instanceof Pawn) musungbu = 0;

        turn++;
        musungbu++;

        //checkCheck(player.getColor(), idx);
        return idx;

    }

    public int aiMove(Player player, Obj o, int x, int y) {
        int idx = SearchUtil.findObjByLocation(o.getX(),o.getY(),moveableObj[player.getColor()]);


        if(boardObj[x][y] != null && boardObj[x][y].getColor() != player.getColor()) {
            deadObj[player.getColor()].add(boardObj[x][y]);
            int otherColor = (player.getColor() == 0) ? 1 : 0 ;
            int removeIdx = SearchUtil.findObjByLocation(x, y, moveableObj[otherColor]);
            boardObj[x][y] = null;
            moveableObj[otherColor].remove(removeIdx);
            musungbu =0;

        } else if(moveableObj[player.getColor()].get(idx) instanceof King) {
            if(player.getColor() == 0) {
                if(x == 0 && y == 1) {
                    int rook = SearchUtil.findObjByLocation(0,0,moveableObj[0]);
                    moveableObj[0].get(rook).clear();
                    moveableObj[0].get(rook).setX(0);
                    moveableObj[0].get(rook).setY(2);
                    moveableObj[0].get(rook).commit();
                } else if(x == 0 && y == 5) {
                    int rook = SearchUtil.findObjByLocation(0,7,moveableObj[0]);
                    moveableObj[0].get(rook).clear();
                    moveableObj[0].get(rook).setX(0);
                    moveableObj[0].get(rook).setY(4);
                    moveableObj[0].get(rook).commit();
                }
            } else {
                if(x == 7 && y == 1) {
                    int rook = SearchUtil.findObjByLocation(7,0,moveableObj[1]);
                    moveableObj[1].get(rook).clear();
                    moveableObj[1].get(rook).setX(7);
                    moveableObj[1].get(rook).setY(2);
                    moveableObj[1].get(rook).commit();

                } else if(x == 7 && y == 5) {
                    int rook = SearchUtil.findObjByLocation(0,7,moveableObj[1]);
                    moveableObj[1].get(rook).clear();
                    moveableObj[1].get(rook).setX(7);
                    moveableObj[1].get(rook).setY(4);
                    moveableObj[1].get(rook).commit();
                }

            }
        }
        moveableObj[player.getColor()].get(idx).clear();
        moveableObj[player.getColor()].get(idx).setX(x);
        moveableObj[player.getColor()].get(idx).setY(y);
        moveableObj[player.getColor()].get(idx).commit();

        if(moveableObj[player.getColor()].get(idx) instanceof Pawn) musungbu = 0;

        turn++;
        musungbu++;




        turn++;
        musungbu++;

        //checkCheck(player.getColor(), idx);
        return idx;
    }

    public ArrayList<Position> getOpponentCurrentPos(int color) {
        color = (color == 0) ? 1: 0;
        ArrayList<Position> p = new ArrayList<>();
        for(int i= 0; i< moveableObj[color].size(); i++) {
            p.add(new Position(moveableObj[color].get(i).getX(), moveableObj[color].get(i).getY()));
        }
        return p;
    }
    public boolean checkCheck(int color, int idx){
        ArrayList<Position> p = moveableObj[color].get(idx).getMoveablePositionList();
        Position opponentKing = getOpponentKingPos(color);
        int pos = SearchUtil.findObjByPos(opponentKing.getX(),opponentKing.getY(), p);
        if(pos != -1) {
            if(color == 0) {
                isPlayer2Checked = true;
                checkingObj[color].add(boardObj[p.get(pos).getX()][p.get(pos).getY()]);
            }
            else {
                isPlayer1Checked = true;
                checkingObj[color].add(boardObj[p.get(pos).getX()][p.get(pos).getY()]);
            }
            System.out.println("Check! (Detail: Color "+((color == 0) ? "White" : "Black")+" Checked)");
            //checkCheckmate(color);
            checkX = p.get(pos).getX();
            checkY = p.get(pos).getY();
            return true;
        } else {
            if(color == 0) {
                isPlayer2Checked = false;
                checkingObj[color] = new ArrayList<>();
            }
            else {
                isPlayer1Checked = false;
                checkingObj[color] = new ArrayList<>();
            }
            return false;
        }
    }
    public int checking() {
        if(isPlayer1Checked) return 0;
        if(isPlayer2Checked) return 1;
        return -1;
    }

    public Position getCheckPos() {
        return new Position(checkX, checkY);
    }

    public boolean checkCheckmate(int color) {
        HashSet<Position> myMovable = new HashSet<>(getAllMove(color));
        HashSet<Position> opponentKingMovable = new HashSet<>(getOpponentKingMovable(color));
        opponentKingMovable.removeAll(myMovable);
        return opponentKingMovable.size() == 0;
    }

    public ArrayList<Position> getAllMove(int color ) {
        ArrayList<Position> p = new ArrayList<>();
        for(int i = 0; i < moveableObj[color].size() ; i++) {
            p.addAll(moveableObj[color].get(i).getMoveablePositionList());
        }
        return SearchUtil.removeJungbok(p);
    }

    private Position getOpponentKingPos(int color) {
        color = ( color == 0) ? 1: 0;
        int idx = SearchUtil.findObj("King",moveableObj[color]);
        Obj o =  moveableObj[color].get(idx);
        return new Position(o.getX(), o.getY());

    }

    private ArrayList<Position> getOpponentKingMovable(int color ){
        color = ( color == 0) ? 1: 0;
        Obj o =  moveableObj[color].get(SearchUtil.findObj("King",moveableObj[color]));
        return o.getMoveablePositionList();
    }

//    public static boolean isCastlingAvailable(Obj obj) {
//
//    }

    boolean isSamSamE() {
        if(moveableObj[0].size() == 1 && moveableObj[1].size() == 1 && moveableObj[0].get(0) instanceof King && moveableObj[1].get(0) instanceof  King) {
            return true;
        }
        if(musungbu >= 50) return true;

        if(moveableObj[0].size() == 1 && moveableObj[0].get(0) instanceof King && (moveableObj[1].size() == 2 && ((moveableObj[1].get(0) instanceof King && moveableObj[1].get(1) instanceof Bishop) || (moveableObj[1].get(0) instanceof Bishop && moveableObj[1].get(1) instanceof King) ))) {
            return true;
        }

        if(moveableObj[1].size() == 1 && moveableObj[1].get(0) instanceof King && (moveableObj[0].size() == 2 && ((moveableObj[0].get(0) instanceof King && moveableObj[0].get(1) instanceof Bishop) || (moveableObj[0].get(0) instanceof Bishop && moveableObj[0].get(1) instanceof King) ))) {
            return true;
        }


        if(moveableObj[0].size() == 1 && moveableObj[0].get(0) instanceof King && (moveableObj[1].size() == 2 && ((moveableObj[1].get(0) instanceof King && moveableObj[1].get(1) instanceof Knight) || (moveableObj[1].get(0) instanceof Knight && moveableObj[1].get(1) instanceof King) ))) {
            return true;
        }

        if(moveableObj[1].size() == 1 && moveableObj[1].get(0) instanceof King && (moveableObj[0].size() == 2 && ((moveableObj[0].get(0) instanceof King && moveableObj[0].get(1) instanceof Knight) || (moveableObj[0].get(0) instanceof Knight && moveableObj[0].get(1) instanceof King) ))) {
            return true;
        }

        if(moveableObj[0].size() == 2 && moveableObj[1].size() == 2) {
            if((moveableObj[0].get(0) instanceof King && moveableObj[0].get(1) instanceof Bishop) ) {
                if(moveableObj[1].get(0) instanceof King && moveableObj[1].get(1) instanceof Bishop) {
                    return isSameBoardColor(moveableObj[0].get(1).getX(), moveableObj[0].get(1).getY(), moveableObj[1].get(1).getX(), moveableObj[1].get(1).getY());
                } else if(moveableObj[1].get(0) instanceof Bishop && moveableObj[1].get(1) instanceof King) {
                    return isSameBoardColor(moveableObj[0].get(1).getX(), moveableObj[0].get(1).getY(), moveableObj[1].get(0).getX(), moveableObj[1].get(0).getY());
                }
            } else if((moveableObj[0].get(0) instanceof Bishop && moveableObj[0].get(1) instanceof King) ) {
                if(moveableObj[1].get(0) instanceof King && moveableObj[1].get(1) instanceof Bishop) {
                    return isSameBoardColor(moveableObj[0].get(0).getX(), moveableObj[0].get(0).getY(), moveableObj[1].get(1).getX(), moveableObj[1].get(1).getY());
                } else if(moveableObj[1].get(0) instanceof Bishop && moveableObj[1].get(1) instanceof King) {
                    return isSameBoardColor(moveableObj[0].get(0).getX(), moveableObj[0].get(0).getY(), moveableObj[1].get(0).getX(), moveableObj[1].get(0).getY());
                }
            }
        }


        return false;
    }

    private int abs(int x) {
        return (x < 0) ? -1 * x : x;
    }

    private boolean isSameHolZzak(int x, int y) {
        return (x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1);
    }

    private boolean isSameBoardColor(int x1, int y1, int x2, int y2) {
        if(abs(x2-x1) % 2 ==0 ) {
            return isSameHolZzak(y1,y2);
        } else {
            return !isSameHolZzak(y1,y2);
        }
    }


    public static boolean isSameClass(Obj a, int x, int y) {
        return a.getClass().isInstance(boardObj[x][y]);
    }



    public String inputMoveObj() {
        System.out.print("Choose Piece >");
        return scanner.next();
    }

    public int inputLine(String s) {
        System.out.print(s);
        return scanner.nextInt();

    }

    public void updateCastling() { //up is 0(b), down is 1(w)
        if(castlingAvailable[0][0] != -1) {
            try {
                if(!(boardObj[0][0] instanceof Rook) || !(boardObj[0][3] instanceof King)) {
                    castlingAvailable[0][0] = -1;
                }
                else if(boardObj[0][1] == null && boardObj[0][2] == null && !isPlayer1Checked) castlingAvailable[0][0] = 1;
                else castlingAvailable[0][0] = 0;
            } catch(Exception e) {
                //NPE ERR
            }
        }
        if(castlingAvailable[0][1] != -1) {
            try {
                if(!(boardObj[0][7] instanceof Rook) || !(boardObj[0][3] instanceof King)) {
                    castlingAvailable[0][1] = -1;
                }
                else if(boardObj[0][4] == null && boardObj[0][5] == null && boardObj[0][6] == null && !isPlayer1Checked) castlingAvailable[0][1] = 1;
                else castlingAvailable[0][1] = 0;
            } catch(Exception e) {
                //NPE ERR
            }
        }
        if(castlingAvailable[1][0] != -1) {
            try {
                if(!(boardObj[7][0] instanceof Rook) || !(boardObj[7][3] instanceof King)) {
                    castlingAvailable[1][0] = -1;
                }
                else if(boardObj[7][1] == null && boardObj[7][2] == null && !isPlayer2Checked) castlingAvailable[1][0] = 1;
                else castlingAvailable[1][0] = 0;
            } catch(Exception e) {
                //NPE ERR
            }
        }
        if(castlingAvailable[1][1] != -1) {
            try {
                if(!(boardObj[7][7] instanceof Rook) || !(boardObj[7][3] instanceof King)) {
                    castlingAvailable[1][1] = -1;
                }
                else if(boardObj[7][4] == null && boardObj[7][5] == null && boardObj[7][6] == null && !isPlayer2Checked) castlingAvailable[1][1] = 1;
                else castlingAvailable[1][1] = 0;
            } catch(Exception e) {
                //NPE ERR
            }
        }

    }

    public int getObjWeightByPos(int x, int y) {
        return getObjWeight(boardObj[x][y]);
    }


    public int getObjWeight(Obj obj) {
        if(obj instanceof King) return 1000000;
        else if (obj instanceof Queen) return 9;
        else if (obj instanceof Rook) return 5;
        else if (obj instanceof Bishop) return 3;
        else if (obj instanceof Knight) return 3;
        else if(obj instanceof Pawn) return 1;
        else return 0;
    }



    public void printMoveableList(int color) {
        getAllMovable(color).forEach(item -> System.out.println(item.getName()+"("+item.getPosX()+","+item.getPosY()+")"));
        //moveableObj[color].forEach(item -> System.out.println(item.getName()+"("+item.getPosX()+","+item.getPosY()+")"));
    }
}
