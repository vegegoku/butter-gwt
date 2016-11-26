package org.akab.engine.core.api.client.events;

public interface EventsBus{

    // TODO: 11/25/16 replace event from gwt with event wrapper
    void publishEvent(com.google.web.bindery.event.shared.Event<EventProcessor> event);
}
