package com.excelia.spaceships.infrastructure.in.rest.listeners;

import com.excelia.spaceships.domain.events.SpaceshipCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateSpaceshipListener {

    @ApplicationModuleListener
    public void on(SpaceshipCreatedEvent event) {
        //
    }

}
