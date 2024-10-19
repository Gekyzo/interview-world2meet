package com.excelia.spaceships.application.exceptions;

import java.util.UUID;

import static com.excelia.spaceships.shared.MessageUtils.getMessageSource;

public class SpaceshipNotFoundException extends RuntimeException {

    private static final String MESSAGE_KEY = "errors.spaceship.notfound";

    public SpaceshipNotFoundException(UUID spaceshipId) {
        super(getMessageSource(MESSAGE_KEY, new Object[]{spaceshipId}));  // Use instance method
    }
}