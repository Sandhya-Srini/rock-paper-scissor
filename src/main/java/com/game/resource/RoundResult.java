package com.game.resource;

import com.game.enums.Move;

import java.util.Map;

public record RoundResult(int roundNumber, Map<String, Move> playerMoves, String winner) {
}
