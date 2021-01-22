package com.eone.demo.service;

import com.eone.demo.model.DieRoll;
import com.eone.demo.model.GameSession;
import com.eone.demo.model.GameSessionState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SnakeAndLadderGameLogicTest {

    @Test
    void canChangeGameSessionState_tokenPositionIsChanging() {
        //Given: the token is on square 1
        GameSession gameSession = new GameSession();
        gameSession.setTokenPosition(1);
        //When: the token is moved 3 spaces
        DieRoll dieRoll = new DieRoll();
        dieRoll.setDiceValue(3);
        SnakeAndLadderGameLogic.changeGameSessionState(gameSession, dieRoll);
        //Then: the token is on square 4
        assertThat(gameSession.getTokenPosition()).isEqualTo(4);
        assertThat(gameSession.getStatus()).isEqualTo(GameSessionState.IN_PROGRESS);
    }

    @Test
    void canWinTheGame() {
        //Given: the token is on square 1
        GameSession gameSession = new GameSession();
        gameSession.setTokenPosition(95);
        //When: the token is moved 3 spaces
        DieRoll dieRoll = new DieRoll();
        dieRoll.setDiceValue(5);
        SnakeAndLadderGameLogic.changeGameSessionState(gameSession, dieRoll);
        //Then: the token is on square 4
        assertThat(gameSession.getTokenPosition()).isEqualTo(100);
        assertThat(gameSession.getStatus()).isEqualTo(GameSessionState.WINNER);
    }

}