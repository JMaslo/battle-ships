package me.devik.battleships.model;

import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class Player {
    private long id;
    private List<Ships> ships;
    private int hitCount;
    private int missCount;
    private int score;

    public Player(long id, List<Ships> ships, int hitCount, int missCount, int score) {
        this.id = id;
        this.ships = ships;
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.score = score;
    }



}
