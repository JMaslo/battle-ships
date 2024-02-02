package me.devik.battleships.model.ships;

public enum ShipsType {
    PATROL_BOAT(1),
    DESTROYER(2),
    SUBMARINE(3),
    BATTLESHIP(4),
    AIRCRAFT_CARRIER(5);

    public final Integer label;

    ShipsType(Integer label) {
        this.label = label;
    }

}
