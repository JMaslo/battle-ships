package me.devik.battleships.dto.selectGame;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SelectGameResponse {
    String status;
    String gameId;
    String message;
}
