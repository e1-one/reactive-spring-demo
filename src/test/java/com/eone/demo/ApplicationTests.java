package com.eone.demo;

import com.eone.demo.model.DieRoll;
import com.eone.demo.model.GameSession;
import com.eone.demo.model.GameSessionState;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Value("${local.server.port}")
    protected int port;

    @BeforeEach
    public  void init(){
        RestAssured.port = port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void startTheGame() {
        //Given the game is started
        GameSession gameSession = RestAssured.given().contentType("application/vnd.api+json").body("{}").when().post
                ("/sessions").as(GameSession.class);
        //When the token is placed on the board
        //Then the token is on square 1
        assertThat(gameSession.getTokenPosition()).isEqualTo(1);

        assertThat(gameSession.getStatus()).isEqualTo(GameSessionState.IN_PROGRESS);
    }

    @Test
    void movesAreDeterminedByDiceRolls() {
        //Given the game is started
        GameSession gameSession = RestAssured.given().contentType("application/vnd.api+json").body("{}").when().post
                ("/sessions").as(GameSession.class);
        //When the player rolls a die
        DieRoll dieRoll = RestAssured.given().contentType("application/vnd.api+json").body("{}").when().post
                ("/sessions/" + gameSession.getId() + "/dierolls").as(DieRoll.class);
        //Then the result should be between 1-6 inclusive
        assertThat(dieRoll.getDiceValue()).isIn(1, 2, 3, 4, 5, 6);
    }

    @Test
    void movesTokenBasedOnARollOfADie() {
        //Given the game is started
        GameSession gameSession = RestAssured.given().contentType("application/vnd.api+json").body("{}").when()
                .post("/sessions").as(GameSession.class);
        //When the player rolls a die
        DieRoll dieRoll = RestAssured.given().contentType("application/vnd.api+json").body("{}").when()
                .post("/sessions/" + gameSession.getId() + "/dierolls").as(DieRoll.class);

        GameSession gameSessionRefreshed = RestAssured.given().contentType("application/vnd.api+json").when()
                .get("/sessions/" + gameSession.getId()).thenReturn().as(GameSession.class);
        //Then token must be moved
        assertThat(gameSessionRefreshed.getTokenPosition()).isNotEqualTo(gameSession.getTokenPosition());
    }

}
