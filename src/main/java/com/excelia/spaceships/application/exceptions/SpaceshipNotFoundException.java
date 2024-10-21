package com.excelia.spaceships.application.exceptions;

import static com.excelia.spaceships.utils.messaging.MessageUtils.getMessageSource;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpaceshipNotFoundException extends RuntimeException {

    private static final String MESSAGE_KEY = "errors.spaceship.notfound";

    public SpaceshipNotFoundException(UUID spaceshipId) {
        super(getMessageSource(MESSAGE_KEY, new Object[]{spaceshipId}));
    }
}