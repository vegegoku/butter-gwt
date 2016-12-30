package org.akab.engine.app.test;


import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.events.EventsBus;

public class TestEventBus  implements EventsBus<Event>{

    private final EventProcessor processor;

    public TestEventBus(EventProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void publishEvent(RequestEvent<Event> event) {
        processor.process(event.asEvent());
    }
}
