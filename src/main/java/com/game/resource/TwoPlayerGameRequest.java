package com.game.resource;

import com.game.enums.Move;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record TwoPlayerGameRequest(
        @NotNull
        String gameId,

        @NotNull
        Map<String, Move>playerMoves) {
}
