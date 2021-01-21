package com.eone.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RandomService {

    private WebClient client = WebClient.create("https://www.random.org/");
    /**
     * Get "genuine" random value from random.org service
     * @return value in [1, 6] range
     */
    public Mono<Integer> getRandomDice(){
        return client.get()
                .uri("/integers/?num=1&min=1&max=6&col=1&base=10&format=plain&rnd=new")
                .accept(MediaType.TEXT_PLAIN)
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class)
                            .map(s -> Integer.parseInt(s.substring(0, 1)));

                });
    }

}
