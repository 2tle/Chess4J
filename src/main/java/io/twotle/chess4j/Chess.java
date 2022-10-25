package io.twotle.chess4j;

import io.twotle.chess4j.players.AiPlayer;
import io.twotle.chess4j.players.Player;
import io.twotle.chess4j.players.UserPlayer;

public class Chess {
    //FLAG
    private int GAME_MODE; // 0 is AI, 1 is PvP
    private Player[] userList = new Player[2];
    private int currentPlayer;

    private Board board;

    public Chess() {
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
            this.board.render();
            System.out.println("Current Turn: "+ userList[currentPlayer].getName() +" "+ userList[currentPlayer].getColorToString());
            while(true) {
                try {
                    board.move(userList[currentPlayer]);
                    break;
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }


            /* 움직임 끝낸 후에 검사 */

            // 프로모션 체크

            // 킹 / 퀸 자리 replace


            if(currentPlayer == 1) currentPlayer = 0;
            else currentPlayer = 1;
        }
    }
}
