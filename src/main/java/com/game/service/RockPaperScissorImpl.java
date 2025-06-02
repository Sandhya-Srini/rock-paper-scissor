package com.game.service;

import com.game.api.GameService;
import com.game.enums.Move;
import com.game.model.Game;
import com.game.model.GameRound;
import com.game.repository.GameRepository;
import com.game.repository.GameRoundRepository;
import com.game.resource.GameInitialiseRequest;
import com.game.resource.GameInitialiseResponse;
import com.game.resource.RoundResult;
import com.game.resource.TwoPlayerGameRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RockPaperScissorImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameRoundRepository gameRoundRepository;

    public RockPaperScissorImpl(GameRepository gameRepository, GameRoundRepository gameRoundRepository) {
        this.gameRepository = gameRepository;
        this.gameRoundRepository = gameRoundRepository;
    }

    @Override
    @Transactional
    public GameInitialiseResponse initialise(GameInitialiseRequest initialiseRequest) {
        Game newGame = Game.builder().rounds(initialiseRequest.rounds()).players(initialiseRequest.players()).build();
        return new GameInitialiseResponse(gameRepository.save(newGame.toGameEntity()).getId().toString());
    }

    @Override
    @Transactional
    public RoundResult playTwoPlayerGame(TwoPlayerGameRequest request) {
        Game game = gameRepository.findById(UUID.fromString(request.gameId())).orElseThrow().toGame();
        if(game.isCompleted()) {
            throw  new IllegalStateException("This game has already completed all rounds");
        }

        String winner = evaluateWinner(request.playerMoves());
        int roundNumber = game.getRoundsCompleted()+1;
        GameRound gameRound = GameRound.builder()
                .roundNumber(roundNumber)
                .playerMoves(request.playerMoves())
                .winner(winner)
                .build();
        gameRound.setGame(game);
        gameRoundRepository.save(gameRound.toGameRoundEntity());

        game.getGameRounds().add(gameRound);
        game.setRoundsCompleted(roundNumber);
        if(roundNumber >= game.getRounds())
            game.setCompleted(true);
        gameRepository.save(game.toGameEntity());

        return new RoundResult(roundNumber, request.playerMoves(), winner);
    }



    @Override
    public RoundResult multiPlayerGame() {
        return null;
    }

    @Override
    public RoundResult systemPlayerGame() {
        return null;
    }



    private String evaluateWinner(Map<String,Move> playerMoves) {
        List<Map.Entry<String, Move>> moves = new ArrayList<>(playerMoves.entrySet());
        String result = null;
        // Simple implementation for 2 players
            Move move1 = moves.get(0).getValue();
            Move move2 = moves.get(1).getValue();

        if (move1 == move2) {
            result = "Draw!";
        }

        switch (move1) {
            case ROCK -> result = move2 == Move.SCISSOR ? "Player 1 wins!" : "Player 2 wins!";
            case PAPER -> result = move2 == Move.ROCK ? "Player 1 wins!" : "Player 2 wins!";
            case SCISSOR -> result = move2 == Move.PAPER ? "Player 1 wins!" : "Player 2 wins!";
        }
        return result;
    }

}
