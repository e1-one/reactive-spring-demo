package com.eone.demo.resource;

import com.eone.demo.dao.ReactiveGameSessionRepository;
import com.eone.demo.model.GameSession;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameSessionJsonResourceRepository extends ResourceRepositoryBase<GameSession, String> {

    @Autowired
    private ReactiveGameSessionRepository gameSessionRepository;

    public GameSessionJsonResourceRepository() {
        super(GameSession.class);
    }

    @Override
    public ResourceList<GameSession> findAll(QuerySpec querySpec) {
        return querySpec.apply(gameSessionRepository.findAll().toIterable());
    }

    @Override
    public Class<GameSession> getResourceClass() {
        return GameSession.class;
    }
}
