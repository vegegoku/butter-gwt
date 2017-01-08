package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;

public class RequestEventProcessor implements EventProcessor {

    @Override
    public void process(Event event) {
        event.process();
    }
}
