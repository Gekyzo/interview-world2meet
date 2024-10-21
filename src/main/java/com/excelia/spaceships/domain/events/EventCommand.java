package com.excelia.spaceships.domain.events;

import lombok.EqualsAndHashCode;

/**
 * Event published to request the execution of an action
 */
@EqualsAndHashCode(callSuper = true)
public abstract non-sealed class EventCommand extends Event {

}
