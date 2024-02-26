package me.devik.battleships.dto.selectGame;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class selectGameResponse {
    String status;
    String gameId;
    String message;
}
