package com.eone.demo.dao;

import com.eone.demo.model.GameSession;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveGameSessionRepository extends ReactiveMongoRepository<GameSession, String> {
}
