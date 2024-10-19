package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.command.CreateSpaceshipCommand;

public interface CreateSpaceshipPort {

    void create(CreateSpaceshipCommand command);

}
