package org.akab.engine.core.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.akab.engine.core.api.client.events.EventProcessor;
import com.google.web.bindery.event.shared.Event;

public abstract class ServerSuccessRequestGwtEvent extends Event<EventProcessor>{

    final static GwtEvent.Type<EventProcessor> SERVER_SUCCESS_REQUEST_EVENT_TYPE = new GwtEvent.Type<>();

    @Override
    public Type<EventProcessor> getAssociatedType() {
        return SERVER_SUCCESS_REQUEST_EVENT_TYPE;
    }

}