package com.junit.object_mothers;

import com.excelia.spaceships.infrastructure.out.persistence.entities.SpaceshipPostgreEntity;
import org.instancio.Instancio;

public final class SpaceshipPostgreObjectMother {

    public static SpaceshipPostgreEntity aSpaceshipPostgreEntity() {
        return Instancio.of(SpaceshipPostgreEntity.class).create();
    }

}
