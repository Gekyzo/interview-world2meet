package com.junit.factories;

import com.excelia.spaceships.domain.entities.Spaceship;
import org.instancio.Instancio;

public final class SpaceshipFactory {

    public static Spaceship aSpaceship() {
        return Instancio.of(Spaceship.class).create();
    }

}
