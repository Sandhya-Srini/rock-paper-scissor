package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {GameController.class})
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;





}
