package com.game.resource;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record GameInitialiseRequest(@NotNull @NotEmpty List<String> players,  @Positive int rounds) {
}
