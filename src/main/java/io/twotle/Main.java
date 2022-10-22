package io.twotle;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Chess game = new Chess(1);


        Chess game = new Chess();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);


        /* SET GAME MODE */

        System.out.println(" < 게임모드 선택 > ");
        System.out.println("1. AI와 대전\n2. 2인 플레이");
        int mode;
        while(true) {
            mode = scanner.nextInt();
            if( mode != 0 && mode != 1) {
                System.out.println("다시 입력해주세요");
            }
            else break;
        }
        game.setGameMode(mode);

        /* SET TURN */

        int firstTurn = random.nextInt(2);
        game.startChess(firstTurn);


    }
}