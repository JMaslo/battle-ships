package me.devik.battleships.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ships {

    private String name;
    private int size;
    private int hits;
    private int[] coordinates;

    public Ships(String name, int size, int hits, int[] coordinates) {
        this.name = name;
        this.size = size;
        this.hits = hits;
        this.coordinates = coordinates;
    }

}
