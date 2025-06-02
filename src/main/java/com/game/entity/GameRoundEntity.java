package com.game.entity;

import com.game.enums.Move;
import com.game.model.GameRound;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "game_round")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameRoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roundNumber;
    @ElementCollection
    private Map<String, Move> playerMoves;
    private String winner;
    @ManyToOne
    @JoinColumn(name = "game")
    private GameEntity game;

    public GameRound toGameRound(){
        return GameRound.builder().id(this.id)
                .roundNumber(this.roundNumber)
                .game(this.game.toGame())
                .playerMoves(this.playerMoves)
                .build();
    }
}
