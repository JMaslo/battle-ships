package me.devik.battleships.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ships {

    private String name;
    private int size;
    private int hits;
    private int[] coordinates;
    

    public Ships(String name, int size, int[] coordinates) {
        this.name = name;
        this.size = size;
        this.hits = 0;
        this.coordinates = coordinates;
    }

    public void placeShip(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isSunk() {
        return this.hits == this.size;
    }

    public boolean isHit() {
        return this.hits >= 1;
    }

}
