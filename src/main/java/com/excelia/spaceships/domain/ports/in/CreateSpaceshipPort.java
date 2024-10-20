package com.excelia.spaceships.domain.ports.in;

import com.excelia.spaceships.domain.commands.CreateSpaceshipCommand;

public interface CreateSpaceshipPort {

    void create(CreateSpaceshipCommand command);

}
