package me.devik.battleships.model;

import lombok.Getter;
import lombok.Setter;
import me.devik.battleships.model.ships.Ships;


import java.util.List;

@Getter
@Setter
public class Player {

    private String name;
    private long id;
    private List<Ships> ships;
    private int hitCount;
    private int missCount;
    private int score;

    public Player(String name, long id, List<Ships> ships, int hitCount, int missCount, int score) {
        this.name = name;
        this.id = id;
        this.ships = ships;
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.score = score;
    }



}
