package me.devik.battleships.service;

import me.devik.battleships.model.Board;
import me.devik.battleships.model.Ships;

public class GameService {

    private Ships ship;
    private Board gameBoard;

    public void startNewGame() {

    }

    public void makeMove(int x, int y) {

    }

    public boolean isGameOver() {
        if (ship.isSunk()) {
            System.out.println("Game Over");
        }
        return true;
    }


}
