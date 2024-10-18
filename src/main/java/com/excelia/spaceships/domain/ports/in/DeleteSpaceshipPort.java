package com.excelia.spaceships.domain.ports.in;

import java.util.UUID;

public interface DeleteSpaceshipPort {

    void delete(UUID spaceshipId);

}
