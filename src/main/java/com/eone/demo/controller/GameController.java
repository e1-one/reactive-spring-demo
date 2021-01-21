package com.eone.demo.controller;

import com.eone.demo.model.DieRoll;
import com.eone.demo.model.GameSession;
import com.eone.demo.dao.ReactiveGameSessionRepository;
import com.eone.demo.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GameController {

    @Autowired
    private ReactiveGameSessionRepository reactiveGameSessionRepository;

    @Autowired
    private GameSessionService gameSessionService;

    @GetMapping("/sessions")
    public Flux<GameSession> getAllGameSessions(){
        return reactiveGameSessionRepository.findAll();
    }

    @PostMapping("/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GameSession> createGameSessions(@RequestBody GameSession gameSession){
        return gameSessionService.createNewGameSession();
    }

    @GetMapping("/sessions/{id}")
    public Mono<ResponseEntity<GameSession>> postDieRoll(@PathVariable String id){
        return reactiveGameSessionRepository.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/sessions/{id}/dierolls")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<DieRoll> postDieRoll(@PathVariable String id, @RequestBody DieRoll dieRoll){
        return gameSessionService.makeMove(id);
    }

}
