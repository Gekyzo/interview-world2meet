package com.excelia.spaceships.application.ports;

import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;

public interface CreateSpaceship {

    Spaceship create(CreateSpaceshipCommand command);

}
