package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import com.excelia.spaceships.domain.queries.SearchSpaceshipQuery;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindSpaceship implements FindSpaceshipPort {

    private final SpaceshipRepositoryPort repository;

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return repository.findById(spaceshipId);
    }

    @Override
    public Page<Spaceship> find(SearchSpaceshipQuery query, Pageable pageable) {
        return repository.find(query, pageable);
    }
}
