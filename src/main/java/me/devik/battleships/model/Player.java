package me.devik.battleships.model;

import lombok.Getter;
import lombok.Setter;
import me.devik.battleships.model.ships.Ships;


import java.util.List;

@Getter
@Setter
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }



}
