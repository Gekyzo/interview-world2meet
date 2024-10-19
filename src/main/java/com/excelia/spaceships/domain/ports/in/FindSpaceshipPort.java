package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.entities.Spaceship;
import java.util.Optional;
import java.util.UUID;

public interface FindSpaceshipPort {

    Optional<Spaceship> findById(UUID spaceshipId);

}
