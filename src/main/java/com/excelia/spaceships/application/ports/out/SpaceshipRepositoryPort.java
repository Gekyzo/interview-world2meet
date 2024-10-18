package com.excelia.spaceships.application.ports.out;

import com.excelia.spaceships.domain.entities.Spaceship;

public interface SpaceshipRepositoryPort {

    Spaceship create(Spaceship entity);

}
