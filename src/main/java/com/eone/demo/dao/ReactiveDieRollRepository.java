package com.eone.demo.dao;

import com.eone.demo.model.DieRoll;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveDieRollRepository extends ReactiveMongoRepository<DieRoll, String> {
}
