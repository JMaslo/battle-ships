package me.devik.battleships.dto.joinGame;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class joinGameResponse {
    private String status;
    private String message;
}
