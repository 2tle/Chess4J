package io.twotle.chess4j.obj;

import io.twotle.chess4j.Board;
import io.twotle.chess4j.data.Position;

import java.util.ArrayList;

public class Queen extends Obj{
    public Queen(int x,int y,String name, int color, int kind, boolean isLive) {
        super(x, y, name, color, kind, isLive, 9);
    }

    @Override
    public String toString() {
        return (getColor() == 1) ? "♕" : "♛";
    }

    @Override
    public ArrayList<Position> getMoveablePositionList() {
        ArrayList<Position> p = new ArrayList<>();

        //left-up
        int c = 1;
        while(Board.isInBoard(getX() -c, getY() - c)) {
            if(Board.isPositionAvailable(getX() - c, getY() -c,getColor())) {
                p.add(new Position(getX() - c, getY() - c));
                if (Board.isOpponent(getX() - c, getY() - c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //right-up
        c = 1;
        while(Board.isInBoard(getX() -c, getY() + c)) {
            if(Board.isPositionAvailable(getX() - c, getY() + c,getColor())) {
                p.add(new Position(getX() - c, getY() + c));
                if (Board.isOpponent(getX() - c, getY() + c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //left-down
        c = 1;
        while(Board.isInBoard(getX() + c, getY() - c)) {
            if(Board.isPositionAvailable(getX() + c, getY() -c,getColor())) {
                p.add(new Position(getX() + c, getY() - c));
                if (Board.isOpponent(getX() + c, getY() - c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //right-down
        c = 1;
        while(Board.isInBoard(getX() + c, getY() + c)) {
            if(Board.isPositionAvailable(getX() + c, getY() + c,getColor())) {
                p.add(new Position(getX() + c, getY() + c));
                if (Board.isOpponent(getX() + c, getY() + c, getColor())) {
                    break;
                }

            }
            else break;
            c++;
        }

        //left

        c = 1;

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



        return p;
    }

    @Override
    public ArrayList<Position> getCheckedObjRoute(King checked) {
        ArrayList<Position> p = new ArrayList<>();
        p.add(new Position(getX(), getY()));
        int c = 1;
        if(checked.getX() < getX() && checked.getY() < getY()) {
            while(Board.isInBoard(getX() -c, getY() - c)) {
                if(Board.isPositionAvailable(getX() - c, getY() -c,getColor())) {
                    p.add(new Position(getX() - c, getY() - c));
                    if (Board.isOpponent(getX() - c, getY() - c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX() > getX() && checked.getY() < getY()) {
            while(Board.isInBoard(getX() -c, getY() + c)) {
                if(Board.isPositionAvailable(getX() - c, getY() + c,getColor())) {
                    p.add(new Position(getX() - c, getY() + c));
                    if (Board.isOpponent(getX() - c, getY() + c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX() < getX() && checked.getY() > getY()) {
            while(Board.isInBoard(getX() + c, getY() - c)) {
                if(Board.isPositionAvailable(getX() + c, getY() -c,getColor())) {
                    p.add(new Position(getX() + c, getY() - c));
                    if (Board.isOpponent(getX() + c, getY() - c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX() > getX() && checked.getY() > getY()) {
            while(Board.isInBoard(getX() + c, getY() + c)) {
                if(Board.isPositionAvailable(getX() + c, getY() + c,getColor())) {
                    p.add(new Position(getX() + c, getY() + c));
                    if (Board.isOpponent(getX() + c, getY() + c, getColor())) {
                        break;
                    }

                }
                else break;
                c++;
            }
        } else if(checked.getX() < getX() && checked.getY() == getY()) {
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
