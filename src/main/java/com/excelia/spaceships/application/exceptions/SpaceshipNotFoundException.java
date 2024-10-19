package com.excelia.spaceships.application.exceptions;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.UUID;

public class SpaceshipNotFoundException extends RuntimeException {

    protected static final String MESSAGE = "errors.spaceship.notfound";
    protected static final ResourceBundleMessageSource MESSAGE_SOURCE = new ResourceBundleMessageSource();

    static {
        MESSAGE_SOURCE.setBasename("messages");
    }

    public SpaceshipNotFoundException(UUID spaceshipId) {
        super(MESSAGE_SOURCE.getMessage(MESSAGE, new Object[]{spaceshipId}, Locale.getDefault()));
    }
}
