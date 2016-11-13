package org.akab.engine.core.api.client.events;

public interface EventsBus{
    void publishEvent(com.google.web.bindery.event.shared.Event<EventProcessor> event);
}
