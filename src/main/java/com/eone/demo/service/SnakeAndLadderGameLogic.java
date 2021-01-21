package com.eone.demo.service;

import com.eone.demo.model.DieRoll;
import com.eone.demo.model.GameSession;
import com.eone.demo.model.GameSessionState;

public class SnakeAndLadderGameLogic {

    private static final Integer FINISH_POSITION = 100;

    public static GameSession changeGameSessionState(GameSession gs, DieRoll newDieRoll) {
        gs.getDieRolls().add(newDieRoll);
        Integer prev = gs.getTokenPosition();
        Integer currentPosition = calculateNewTokenPosition(prev, newDieRoll.getDiceValue());
        gs.setTokenPosition(currentPosition);
        if (currentPosition.equals(FINISH_POSITION)) {
            gs.setStatus(GameSessionState.WINNER);
        }
        return gs;
    }

    private static int calculateNewTokenPosition(int oldPosition, int diceValue) {
        int intermediaryTokenPosition = oldPosition + diceValue;
        //todo: implement Feature 2 - Snakes and Ladders logic
        int finalValue = intermediaryTokenPosition > FINISH_POSITION ? oldPosition : intermediaryTokenPosition;
        return finalValue;
    }
}
