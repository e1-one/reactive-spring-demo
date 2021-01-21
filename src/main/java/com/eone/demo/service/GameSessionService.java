package com.eone.demo.service;

import com.eone.demo.dao.ReactiveDieRollRepository;
import com.eone.demo.model.DieRoll;
import com.eone.demo.model.GameSession;
import com.eone.demo.dao.ReactiveGameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameSessionService {

    @Autowired
    private ReactiveGameSessionRepository gameSessionRepository;

    @Autowired
    private ReactiveDieRollRepository dieRollRepository;

    @Autowired
    private RandomService randomService;

    public Mono<GameSession> createNewGameSession(){
        GameSession gs = new GameSession();
        return gameSessionRepository.save(gs);
    }

    public Mono<DieRoll> makeMove(String gameSessionId) {
        Mono<GameSession> byId = gameSessionRepository.findById(gameSessionId);
        Mono<DieRoll> dieRoll = randomService.getRandomDice()
                .flatMap(diceNumber -> {
                    DieRoll dr = new DieRoll();
                    dr.setDiceValue(diceNumber);
                    return dieRollRepository.save(dr);
                });

        Mono.zip(byId, dieRoll).map(tuple -> {
            GameSession gs = tuple.getT1();
            DieRoll newDieRoll = tuple.getT2();
            SnakeAndLadderGameLogic.changeGameSessionState(gs, newDieRoll);
            return gs;
        }).subscribe(gs -> {
            gameSessionRepository.save(gs).subscribe();
        });
        return dieRoll;
    }
}
