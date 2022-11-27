package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.util.SearchUtil;

import java.util.ArrayList;

public class Rook extends Obj{

    public Rook(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive, 5);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♖" : "♜";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();

        // 일반 룩 움직임
        //left

        int c = 1;

        while(Board.isInBoard(getX(), getY() - c)) {
            if(Board.isPositionAvailable(getX(), getY() - c,getColor())) {
                p.add(new Position(getX(), getY() - c));
                if (Board.isOpponent(getX(), getY() - c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }


        //up

        c = 1;

        while(Board.isInBoard(getX() - c, getY())) {
            if(Board.isPositionAvailable(getX() -c , getY() ,getColor())) {
                p.add(new Position(getX() -c, getY() ));
                if (Board.isOpponent(getX() -c , getY(), getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }



        //right

        c = 1;
        while(Board.isInBoard(getX(), getY() + c)) {
            if(Board.isPositionAvailable(getX(), getY() + c,getColor())) {
                p.add(new Position(getX(), getY() + c));
                if (Board.isOpponent(getX(), getY() + c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }


        //down

        c =1;
        while(Board.isInBoard(getX() + c, getY())) {
            if(Board.isPositionAvailable(getX() +c , getY() ,getColor())) {
                p.add(new Position(getX() +c, getY() ));
                if (Board.isOpponent(getX() +c , getY(), getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        // 추가로 캐슬링 가능한지?
        //if(Board.castlingAvailable)

        // 중복제거
        SearchUtil.removeJungbok(p);

        return p;
    }

    @Override
    public ArrayList<Position> getCheckedObjRoute(King checked) {
        ArrayList<Position> p = new ArrayList<>();
        p.add(new Position(getX(), getY()));
        int c = 1;
        if(checked.getX() < getX() && checked.getY() == getY()) {
            while(Board.isInBoard(getX(), getY() - c)) {
                if(Board.isPositionAvailable(getX(), getY() - c,getColor())) {
                    p.add(new Position(getX(), getY() - c));
                    if (Board.isOpponent(getX(), getY() - c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX()==getX() && checked.getY() > getY()) {
            while(Board.isInBoard(getX() - c, getY())) {
                if(Board.isPositionAvailable(getX() -c , getY() ,getColor())) {
                    p.add(new Position(getX() -c, getY() ));
                    if (Board.isOpponent(getX() -c , getY(), getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX() > getX() && checked.getY() == getY()) {
            while(Board.isInBoard(getX(), getY() + c)) {
                if(Board.isPositionAvailable(getX(), getY() + c,getColor())) {
                    p.add(new Position(getX(), getY() + c));
                    if (Board.isOpponent(getX(), getY() + c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else {
            while(Board.isInBoard(getX() + c, getY())) {
                if(Board.isPositionAvailable(getX() +c , getY() ,getColor())) {
                    p.add(new Position(getX() +c, getY() ));
                    if (Board.isOpponent(getX() +c , getY(), getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        }



        return p;
    }
}
