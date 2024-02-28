package me.devik.battleships.model.game;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
final public class Game {

    final String id;
    final String name;

    public Game(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
