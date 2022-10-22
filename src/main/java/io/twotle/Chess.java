package io.twotle;

public class Chess {
    //FLAG
    private int GAME_MODE; // 0 is PvP, 1 is Player vs AI

    private Board board;

    public Chess() {
        System.out.println("WELCOME TO CHESS4J");
    }

    public void setGameMode(int mode) {
        this.GAME_MODE = mode;
    }

    public void startChess(int turn) {
        this.board = new Board();
        this.board.render();

        this.board.startGame(GAME_MODE, turn);
    }
}
