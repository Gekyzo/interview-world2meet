package com.excelia.spaceships.infrastructure.out.persistence.adapters;

import com.excelia.spaceships.application.exceptions.SpaceshipNotFoundException;
import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import com.excelia.spaceships.infrastructure.out.persistence.mappers.SpaceshipPostgreMapper;
import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import com.excelia.spaceships.infrastructure.out.persistence.repositories.SpaceshipPostgreRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceshipRepositoryAdapter implements SpaceshipRepositoryPort {

    private final SpaceshipPostgreRepository postgreRepository;
    private final SpaceshipPostgreMapper mapper;

    @Override
    public void create(Spaceship entity) {
        SpaceshipPostgreModel model = mapper.toPostgreModel(entity);
        postgreRepository.save(model);
    }

    @Override
    public void delete(UUID spaceshipId) {
        postgreRepository.findById(spaceshipId).orElseThrow(() -> new SpaceshipNotFoundException(spaceshipId));
        postgreRepository.deleteById(spaceshipId);
    }

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return postgreRepository.findById(spaceshipId).map(mapper::toDomainEntity);
    }

    @Override
    public Optional<Spaceship> update(Spaceship entity) {
        SpaceshipPostgreModel model = mapper.toPostgreModel(entity);
        return postgreRepository
            .findById(entity.getId())
            .map(_ -> mapper.toDomainEntity(postgreRepository.save(model)));
    }

    @Override
    public Page<Spaceship> find(SearchSpaceshipQuery query, Pageable pageable) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("captainName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("appearsIn", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<SpaceshipPostgreModel> example = Example.of(mapper.queryToModel(query), exampleMatcher);
        return postgreRepository.findAll(example, pageable).map(mapper::toDomainEntity);
    }

}
