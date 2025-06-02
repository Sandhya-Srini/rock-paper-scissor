package com.game.controller;

import com.game.api.GameService;
import com.game.resource.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("v1/rock-paper-scissor")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameInitialiseResponse> initialiseGame(@Valid @RequestBody GameInitialiseRequest initialiseRequest )
    {
        return ResponseEntity.ok(gameService.initialise(initialiseRequest));
    }

    @PostMapping("/two-player")
    public ResponseEntity<String> twoPlayerGame(@Valid @RequestBody TwoPlayerGameRequest gameRequest){
        try {
            return ResponseEntity.ok(gameService.playTwoPlayerGame(gameRequest).winner());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/multi-player")
    public RoundResult multiPlayerGame(@RequestBody MultiPlayerGameRequest gameRequest){
        return gameService.multiPlayerGame();
    }

    @PostMapping("/system-player")
    public RoundResult systemPlayerGame(@RequestBody SystemPlayerGameRequest gameRequest){
        return gameService.systemPlayerGame();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    // Exception handler for invalid enum values
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

}
