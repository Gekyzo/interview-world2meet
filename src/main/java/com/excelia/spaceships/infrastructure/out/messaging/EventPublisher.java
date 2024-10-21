package com.excelia.spaceships.infrastructure.out.messaging;

import com.excelia.spaceships.domain.events.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(Event event) {
        publisher.publishEvent(event);
        log.info("Event published: {}", event);
    }
}
