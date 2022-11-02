package io.twotle.chess4j;

import io.twotle.chess4j.data.Position;
import io.twotle.chess4j.players.AiPlayer;
import io.twotle.chess4j.players.Player;
import io.twotle.chess4j.players.UserPlayer;

import java.util.Scanner;

public class Chess {
    //FLAG
    private int GAME_MODE; // 0 is AI, 1 is PvP
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
        }


    }

    public void startChess(int turn) {
        this.board = new Board();

        this.currentPlayer = turn;

        while(true) {
            this.board.resetFlag();
            this.board.render();
            System.out.println("Current Turn: "+ userList[currentPlayer].getName() +" "+ userList[currentPlayer].getColorToString());
            while(true) {
                try {
                    this.board.move(userList[currentPlayer]);
                    break;
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }


            /* 움직임 끝낸 후에 검사 */

            // 프로모션 체크
            try {
                Position promotionObj = this.board.isPromotion(this.currentPlayer);
                int userChoice = promotionChoice();
                this.board.doPromotion(promotionObj, userChoice, this.currentPlayer);
            } catch(Exception e) {
                // No Promotion
            }

            // 캐슬링 체크

            // 무승부 여부

            // 체크메이트 여부

            // 체크 여부




            if(currentPlayer == 1) currentPlayer = 0;
            else currentPlayer = 1;
        }
    }

    private int promotionChoice() {
        while(true) {
            System.out.println("What object Do you want?");
            System.out.println("1. Bishop\n2. Knight\n3. Rook\n4. Queen");
            int input = scanner.nextInt();
            if(input >= 1 && input <= 4) return input;
        }


    }
}
