package com.excelia.spaceships.application.messaging;

import com.excelia.spaceships.domain.events.Event;

public interface EventPublisherPort {

    void publish(Event event);
}
