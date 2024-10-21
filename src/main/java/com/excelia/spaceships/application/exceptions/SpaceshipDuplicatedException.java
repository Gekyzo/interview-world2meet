package com.excelia.spaceships.application.exceptions;

import static com.excelia.spaceships.utils.messaging.MessageUtils.getMessageSource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SpaceshipDuplicatedException extends RuntimeException {

    private static final String MESSAGE_KEY = "errors.spaceship.duplicated";

    public SpaceshipDuplicatedException(String spaceshipName) {
        super(getMessageSource(MESSAGE_KEY, new Object[]{spaceshipName}));
    }
}