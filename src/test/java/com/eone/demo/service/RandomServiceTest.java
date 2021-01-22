package com.eone.demo.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class RandomServiceTest {

    @Test
    void getRandomNumber() {
        RandomService randomService = new RandomService();
        Mono<Integer> randomNumber = randomService.getRandomNumber();
        StepVerifier.create(randomNumber).expectNextMatches(integer -> integer >= 1 && integer <= 6).verifyComplete();
    }
}