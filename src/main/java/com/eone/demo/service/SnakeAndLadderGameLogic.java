package com.eone.demo.service;

import com.eone.demo.model.GameSession;
import com.eone.demo.model.GameSessionState;
import com.eone.demo.model.DieRoll;

public class SnakeAndLadderGameLogic {

    private static final Integer FINISH_POSITION = 100;

    public static GameSession changeGameSessionState(GameSession gs, DieRoll newDieRoll){

        return gs;
    }

    private static int calculateNewTokenPosition(int oldPosition, int diceValue){
        Integer intermediaryTokenPosition =  oldPosition + diceValue;
        //todo: implement Feature 2 - Snakes and Ladders logic
        Integer finalValue =  intermediaryTokenPosition > FINISH_POSITION ? oldPosition : intermediaryTokenPosition;
        return finalValue;
    }
}
