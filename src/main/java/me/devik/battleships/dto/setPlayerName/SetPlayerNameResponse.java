package me.devik.battleships.dto.setPlayerName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class SetPlayerNameResponse {
    private String status;
    private String message;
}
