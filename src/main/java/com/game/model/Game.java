package com.game.model;

import com.game.entity.GameEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
public class Game {
    private Long id;
    private List<String> players;
    private int rounds;
    private boolean completed;
    private int roundsCompleted;
    private List<GameRound> gameRounds;

    public GameEntity toGameEntity(){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setRounds(this.rounds);
        gameEntity.setPlayers(this.players);
        gameEntity.setGameRounds(this.gameRounds.stream().map(GameRound::toGameRoundEntity).toList());
        gameEntity.setCompleted(this.completed);
        gameEntity.setRoundsPlayed(this.roundsCompleted);
        return gameEntity;
    }
}
