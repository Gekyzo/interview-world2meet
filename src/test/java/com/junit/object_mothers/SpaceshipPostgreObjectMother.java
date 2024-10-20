package com.junit.object_mothers;

import com.excelia.spaceships.infrastructure.out.persistence.model.SpaceshipPostgreModel;
import org.instancio.Instancio;

public final class SpaceshipPostgreObjectMother {

    public static SpaceshipPostgreModel aSpaceshipPostgreModel() {
        return Instancio.of(SpaceshipPostgreModel.class).create();
    }

}
