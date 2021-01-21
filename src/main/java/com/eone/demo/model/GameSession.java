package com.eone.demo.model;

import io.crnk.core.resource.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonApiResource(type = "sessions")
public class GameSession {

    @JsonApiId
    private String id;

    public GameSession(GameSession gameSession) {
        this.id = gameSession.getId();
        this.dieRolls = gameSession.getDieRolls();
        this.tokenPosition = gameSession.getTokenPosition();
        this.status = gameSession.getStatus();
    }

    @JsonApiRelation(opposite = "session", lookUp = LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,
            repositoryBehavior = RelationshipRepositoryBehavior.FORWARD_OPPOSITE)
    private List<DieRoll> dieRolls = new LinkedList<>();

    private Integer tokenPosition = 1;

    private GameSessionState status = GameSessionState.IN_PROGRESS;
}
