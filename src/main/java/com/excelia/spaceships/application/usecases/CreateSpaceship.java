package com.excelia.spaceships.application.usecases;

import com.excelia.spaceships.application.ports.CreateSpaceshipPort;
import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;
import com.excelia.spaceships.domain.entities.Spaceship;
import org.springframework.stereotype.Service;

@Service
public class CreateSpaceship implements CreateSpaceshipPort {

    @Override
    public Spaceship create(CreateSpaceshipCommand command) {
        return null;
    }
}
