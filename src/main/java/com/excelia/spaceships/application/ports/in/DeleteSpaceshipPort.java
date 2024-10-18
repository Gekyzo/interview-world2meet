package com.excelia.spaceships.application.ports.in;

import java.util.UUID;

public interface DeleteSpaceshipPort {

    void delete(UUID spaceshipId);

}
