package com.eone.demo.model;

import io.crnk.core.resource.annotations.*;
import lombok.Data;


@JsonApiResource(type = "dierolls")
@Data
public class DieRoll {

    @JsonApiId
    private String id;

//    Long order;

    Integer diceValue;

    @JsonApiRelation(opposite = "dieRolls", lookUp = LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,
            repositoryBehavior = RelationshipRepositoryBehavior.FORWARD_OWNER,
            serialize = SerializeType.ONLY_ID)
    private GameSession session;

//    @JsonApiRelationId
//    private String sessionId;

}
