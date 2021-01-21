package com.eone.demo.resource;

import com.eone.demo.dao.ReactiveDieRollRepository;
import com.eone.demo.dao.ReactiveGameSessionRepository;
import com.eone.demo.model.DieRoll;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DieRollJsonResourceRepository extends ResourceRepositoryBase<DieRoll, String> implements ResourceRepository<DieRoll, String> {

    @Autowired
    private ReactiveDieRollRepository dieRollRepository;

    public DieRollJsonResourceRepository() {
        super(DieRoll.class);
    }

    @Override
    public ResourceList<DieRoll> findAll(QuerySpec querySpec) {
        return querySpec.apply(dieRollRepository.findAll().toIterable());
    }

    @Override
    public <S extends DieRoll> S create(S resource) {
        return dieRollRepository.save(resource).block();
    }

    @Override
    public <S extends DieRoll> S save(S resource) {
        return super.save(resource);
    }

    @Override
    public Class<DieRoll> getResourceClass() {
        return DieRoll.class;
    }
}
