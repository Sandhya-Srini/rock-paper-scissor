package com.game.model;

import com.game.entity.GameRoundEntity;
import com.game.enums.Move;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class GameRound {
    private Long id;
    private int roundNumber;
    private Map<String, Move> playerMoves;
    private Game game;
    private String winner;
    public GameRoundEntity toGameRoundEntity(){
        GameRoundEntity gameRoundEntity = new GameRoundEntity();
        gameRoundEntity.setRoundNumber(this.getRoundNumber());
        gameRoundEntity.setPlayerMoves(this.getPlayerMoves());
        gameRoundEntity.setGame(this.getGame().toGameEntity());
        gameRoundEntity.setWinner(this.getWinner());
        return gameRoundEntity;
    }
}
