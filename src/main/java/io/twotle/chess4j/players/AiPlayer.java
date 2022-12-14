package io.twotle.chess4j.players;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.obj.King;
import io.twotle.chess4j.obj.Knight;
import io.twotle.chess4j.obj.Obj;
import io.twotle.chess4j.obj.Pawn;
import io.twotle.chess4j.util.SearchUtil;

import java.util.ArrayList;
import java.util.Random;

public class AiPlayer extends Player{
    public AiPlayer(String name, int color) {
        super(name, color);
    }


    public ArrayList<Position> getKingMovePos(Board board) {
        ArrayList<Position> list = board.getAllMove((this.getColor() == 0) ? 1 : 0 );
        ArrayList<Position> kingCross = board.kingCross(this.getColor());
        return SearchUtil.hashRemoveAll(kingCross,list);
    }

    public ArrayList<Obj> getMyMovableObj(Board board) {
        return board.getAllMovable(getColor());
    }
    public ArrayList<Obj> getOpponentMovableObj(Board board) {
        return board.getAllMovable((getColor() == 0) ? 1 : 0);
    }

    public ArrayList<Position> getMyAllPos(Board board) {
        return board.getAllPos(getColor());
    }

    public ArrayList<Position> getFinalKingMovePosition(Board board) {
        return board.kingCross(this.getColor());
    }

    public Obj getMinObj(Board board) {
        return board.getMinObj(getColor());
    }

    public Obj getMaxObj(Board board) {
        return board.getMaxObj(getColor());
    }

    public Obj getMinObjByObj(ArrayList<Obj> o) {
        int min = 1000000;
        Obj obj = null;
        for (Obj value : o) {
            if (value.getWeight() < min) obj = value;
        }
        return obj;
    }

    public Obj getMaxObjByObj(ArrayList<Obj> o) {
        int max = 0;
        Obj obj = null;
        for (Obj value : o) {
            if (value.getWeight() > max) obj = value;
        }
        return obj;
    }

    public Obj getMaxObjByPos(Board board, ArrayList<Position> p) {
        int max = 0;
        Obj obj = null;
        for(int i = 1; i< p.size();i++) {
            try {
                Obj t = board.getObjByPos(p.get(i).getX(), p.get(i).getY(), (getColor() == 0) ? 1 :0 );
                if(t.getWeight() > max) obj = t;
            } catch (Exception e) {

            }

        }
        return obj;
    }

    public Position getOpponentCheckPos(Board board) {
//        int bck = board.checking();
//        if(getColor() == 0 && bck == 0) {
//            return board.getCheckPos();
//        } else if( getColor() == 1 && bck == 1) {
//            return board.getCheckPos();
//        }
//        return null;
        return board.getCheckPos();
    }

    public boolean isCheck(Board board) {
        int bck = board.checking();
        if(getColor() == 0 && bck == 0) {
            return true;
        } else if( getColor() == 1 && bck == 1) {
            return true;
        }
        return false;
    }

    public ArrayList<Obj> getAttackAbleObj(Board board) {
        ArrayList<Obj> attackAble = new ArrayList<>();
        ArrayList<Obj> mo = getMyMovableObj(board);
        Position p = getOpponentCheckPos(board);
        for(int i = 0; i < mo.size(); i++) {
            int idx = SearchUtil.findObjByPos(p.getX(),p.getY(),mo.get(i).getMoveablePositionList());
            if(idx != -1) {
                attackAble.add(mo.get(i));
            }

        }
        return attackAble;
    }

    public int move(Board board) {
        boolean check = this.isCheck(board);
        if(check) {
            ArrayList<Obj> newList = getAttackAbleObj(board);
            if(newList.size() > 0 ) {
                Obj selectedObject = getMinObjByObj(newList);
                Position p = getOpponentCheckPos(board);
                return moveObject(board,selectedObject,p.getX(), p.getY());
            } else {
                ArrayList<Position> finalKingMovePos = getFinalKingMovePosition(board);
                if(finalKingMovePos.size() != 0) {
                    Random r = new Random();
                    int mi = r.nextInt(finalKingMovePos.size());
                    return moveObject(board,board.getKing(getColor()), finalKingMovePos.get(mi).getX(), finalKingMovePos.get(mi).getY() );
                    // finalKingMovePos??? ?????? ?????? ????????? ??????
                } else {
                    ArrayList<Obj> checkCount = board.getCheckObjectCount(getColor());
                    if(checkCount.size() == 1) {
                        ArrayList<Obj> myMovableObj = getMyMovableObj(board);
                        //ArrayList<Obj> moveObjs = new ArrayList<>();
                        King king = board.getKing(getColor());
                        for(Obj my : myMovableObj) {
//                            moveObjs.addAll();
                            ArrayList<Position> checkingRoute = board.getCheckingRouteList(checkCount.get(0), king);
                            ArrayList<Position> tmp = SearchUtil.hashRetainAll(checkingRoute, my.getMoveablePositionList());
                            if(tmp.size() > 0) {
                                //moveObject();
                                //return true;
                                Random r = new Random();
                                int mi = r.nextInt(tmp.size());
                                return moveObject(board, my, tmp.get(mi).getX(), tmp.get(mi).getY());
                            }

                        }


                        // ????????? ?????? ?????? ????????? ?????? ????????? ???????????? ????????? ??????
                        // ??? ????????? ??????????????? ????????? ??????, HashSet?????? ????????? ?????? ??????, ???????????? ??????
                    } else {
                        return -1;
                    }
                }

            }
        } else {
            ArrayList<Position> maxList = new ArrayList<>();
            ArrayList<Obj> matchObj = new ArrayList<>();
            ArrayList<Obj> myMovableObj = getMyMovableObj(board);
            ArrayList<Obj> opponentMovableObj = getOpponentMovableObj(board);
            ArrayList<Position> opponentCurrent = board.getOpponentCurrentPos(getColor());
            for(Obj my : myMovableObj) {
                ArrayList<Position> movablePosition = my.getMoveablePositionList();
                ArrayList<Position> attack = SearchUtil.hashRetainAll(movablePosition, opponentCurrent);
                if(attack.size() > 0) {
                    Obj obj = getMaxObjByPos(board, attack);
                    if(obj != null)
                        maxList.add(new Position(obj.getX(),obj.getY()));
                    else maxList.add(null);
                } else {
                    maxList.add(null);
                }

            }
            //System.out.println(maxList);
            Obj o = getMaxObjByPos(board,maxList);
            if(o != null) {
                Obj finalO = myMovableObj.get(maxList.indexOf(new Position(o.getX(), o.getY())));
                // ????????? ?????? finalO??? o????????? ???????????????

                //return true;
                return moveObject(board, finalO, o.getX(), o.getY());
            } else {
                //?????? ?????? ??????

                Obj obj = myMovableObj.get(SearchUtil.getRandomPawn(myMovableObj));
                //System.out.println(myMovableObj);
                ArrayList<Position> pl = obj.getMoveablePositionList();
                Random r = new Random();
                if(pl.size() > 0) {
                    int idx = r.nextInt((pl.size()));
                    return moveObject(board, obj, pl.get(idx).getX(), pl.get(idx).getY());
                }

            }




        }
        return -1;

    }

    public int moveObject(Board board, Obj o, int x, int y ) {
        return board.aiMove(this,o,x,y);
    }
}
