package org.akab.engine.core.api.client.events;

import com.google.gwt.event.shared.EventHandler;

@FunctionalInterface
public interface EventProcessor extends EventHandler {
    void process(Event event);
}
