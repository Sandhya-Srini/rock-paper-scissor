package com.game.api;

import com.game.resource.GameInitialiseRequest;
import com.game.resource.GameInitialiseResponse;
import com.game.resource.RoundResult;
import com.game.resource.TwoPlayerGameRequest;

public interface GameService {
    RoundResult playTwoPlayerGame(TwoPlayerGameRequest gameRequest);

    RoundResult multiPlayerGame();

    RoundResult systemPlayerGame();

    GameInitialiseResponse initialise(GameInitialiseRequest initialiseRequest);
}
