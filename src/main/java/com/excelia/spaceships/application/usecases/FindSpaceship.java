package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.domain.entities.Spaceship;
import com.excelia.spaceships.domain.ports.in.FindSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindSpaceship implements FindSpaceshipPort {

    private final SpaceshipRepositoryPort repository;

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return repository.findById(spaceshipId);
    }

    @Override
    public Page<Spaceship> find(Pageable pageable) {
        return repository.find(pageable);
    }
}
