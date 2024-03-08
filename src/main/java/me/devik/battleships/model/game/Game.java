package me.devik.battleships.model.game;

import lombok.Getter;

@Getter
final public class Game {

    final String id;
    final String name;
    final boolean waitingForOpponent;

    public Game(String id, String name, boolean waitingForOpponent) {
        this.id = id;
        this.name = name;
        this.waitingForOpponent = waitingForOpponent;
    }

}
