package io.twotle.chess4j;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.players.AiPlayer;
import io.twotle.chess4j.players.Player;
import io.twotle.chess4j.players.UserPlayer;

import java.util.Scanner;

public class Chess {
    //FLAG
    private int GAME_MODE; // 0 is AI, 1 is PvP, 2 is EvE
    private Player[] userList = new Player[2];
    private int currentPlayer;

    private Scanner scanner;

    private Board board;

    public Chess() {
        scanner = new Scanner(System.in);
        System.out.println("WELCOME TO CHESS4J");
    }

    public void setGameMode(int mode) {
        this.GAME_MODE = mode;

        switch (mode) {
            case 0 -> {
                userList[0] = new AiPlayer("AI", 0);
                userList[1] = new UserPlayer("USER1", 1);
            }
            case 1 -> {
                userList[0] = new UserPlayer("USER1", 0);
                userList[1] = new UserPlayer("USER2", 1);
            }
            case 2 -> {
                userList[0] = new AiPlayer("AI", 0);
                userList[1] = new AiPlayer("AI2", 1);
            }
        }


    }

    public void startChess( int turn   ) {
        this.board = new Board();

        this.currentPlayer = turn;

        //while(true) {
            this.board.resetFlag();


            while(true) {
                try {
                    System.out.println("Current Turn: "+ userList[currentPlayer].getName() +" "+ userList[currentPlayer].getColorToString());
                    this.board.render();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }



                    int idx = 0;

                    if(userList[currentPlayer] instanceof UserPlayer) {
                        this.board.printMoveableList(currentPlayer);
                        String moveObj = this.board.inputMoveObj();
                        idx = this.board.move(userList[currentPlayer], moveObj);
                        if(idx == -1) {
                            stopGame("GAME OVER!, Player"+currentPlayer+" LOSE!");
                        }
                    } else if (userList[currentPlayer] instanceof AiPlayer) {
                        idx = ((AiPlayer) userList[currentPlayer]).move(board);
                        if(idx == -1) {
                            stopGame("GAME OVER!, Player"+currentPlayer+" LOSE!");
                        }
                    }


                    /* 움직임 끝낸 후에 검사 */

                    // 프로모션 체크

                    try {
                        if(userList[currentPlayer] instanceof UserPlayer) {
                            Position promotionObj = this.board.isPromotion(this.currentPlayer);
                            int userChoice = promotionChoice();
                            this.board.doPromotion(promotionObj, userChoice, this.currentPlayer);
                        } else {
                            Position promotionObj = this.board.isPromotion(this.currentPlayer);
                            this.board.doPromotion(promotionObj, 4, this.currentPlayer);
                        }

                    } catch(Exception e) {
                        // No Promotion
                    }

                    // 체크 여부 & 체크메이트 검사
                    boolean isCheck = this.board.checkCheck(currentPlayer,idx);
                    if(isCheck) {
                        boolean isCM = this.board.checkCheckmate(currentPlayer);
                        if(isCM) {

                            stopGame("GAME OVER!");
                        }
                    }

                    // 캐슬링 체크
                    this.board.updateCastling();

                    // 무승부 여부
                    boolean isSamSamE = this.board.isSamSamE();
                    if(isSamSamE) {
                        stopGame("Draw!");
                    }






                    if(currentPlayer == 1) currentPlayer = 0;
                    else currentPlayer = 1;
                    System.out.println("==========================================");
                } catch(Exception e) {
                    stopGame("GAME OVER!");
                    break;
                }
            }

        }
    //}

    private int promotionChoice() {
        while(true) {
            System.out.println("What object Do you want?");
            System.out.println("1. Bishop\n2. Knight\n3. Rook\n4. Queen");
            int input = scanner.nextInt();
            if(input >= 1 && input <= 4) return input;
        }
    }

    private void stopGame(String errName) {
//        throw new Error(errName);
        System.out.println(errName);
        System.out.println("Final Board");
        this.board.render();
        System.exit(0);
    }


}
