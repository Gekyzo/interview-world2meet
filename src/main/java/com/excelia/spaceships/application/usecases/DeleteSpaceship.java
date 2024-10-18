package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.domain.ports.in.DeleteSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteSpaceship implements DeleteSpaceshipPort {

    private final SpaceshipRepositoryPort repository;

    @Override
    public void delete(UUID spaceshipId) {
        repository.delete(spaceshipId);
    }
}
