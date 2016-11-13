package org.akab.engine.core.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.google.web.bindery.event.shared.Event;

public class SimpleEventsBus implements EventsBus{
    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(SimpleEventsBus.class);

    private final static EventBus simpleGwtEventsBus = new SimpleEventBus();

    public SimpleEventsBus(EventProcessor eventProcessor) {
        simpleGwtEventsBus.addHandler(ClientRequestGwtEvent.CLIENT_REQUEST_EVENT_TYPE, eventProcessor);
        simpleGwtEventsBus.addHandler(ServerSuccessRequestGwtEvent.SERVER_SUCCESS_REQUEST_EVENT_TYPE, eventProcessor);
        simpleGwtEventsBus.addHandler(ServerFailedRequestGwtEvent.SERVER_FAILED_REQUEST_EVENT_TYPE, eventProcessor);
    }

    @Override
    public void publishEvent(Event<EventProcessor> event) {
        try {
            simpleGwtEventsBus.fireEvent(event);
        }catch (Exception ex){
            LOGGER.error("could not publish event", ex);
        }
    }

}
