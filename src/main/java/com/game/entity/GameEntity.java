package com.game.entity;

import com.game.model.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "game")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private int rounds;
    @ElementCollection
    private List<String> players;
    private int roundsPlayed;
    private boolean completed;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "game_rounds")
    private List<GameRoundEntity> gameRounds = new ArrayList<>();

    public Game toGame(){
        return Game.builder()
                .id(this.id)
                .players(this.players)
                .rounds(this.rounds)
                .roundsCompleted(this.roundsPlayed)
                .completed(this.completed)
                .gameRounds(this.gameRounds.stream().map(GameRoundEntity::toGameRound).toList())
                .build();
    }
    }
