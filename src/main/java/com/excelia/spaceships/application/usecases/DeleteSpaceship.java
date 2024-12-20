package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.domain.ports.in.DeleteSpaceshipPort;
import com.excelia.spaceships.domain.ports.out.SpaceshipPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSpaceship implements DeleteSpaceshipPort {

    private final SpaceshipPort repository;

    @Override
    public void delete(UUID spaceshipId) {
        repository.delete(spaceshipId);
    }
}
