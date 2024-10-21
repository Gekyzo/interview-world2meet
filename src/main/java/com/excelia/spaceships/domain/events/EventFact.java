package com.excelia.spaceships.domain.events;

import lombok.EqualsAndHashCode;

/**
 * Event published after an action has already occurred
 */
@EqualsAndHashCode(callSuper = true)
public abstract non-sealed class EventFact extends Event {

}
