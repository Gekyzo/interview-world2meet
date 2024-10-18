package com.junit.object_mothers;

import com.excelia.spaceships.domain.entities.Spaceship;
import org.instancio.Instancio;

public final class SpaceshipObjectMother {

    public static Spaceship aSpaceship() {
        return Instancio.of(Spaceship.class).create();
    }

}
