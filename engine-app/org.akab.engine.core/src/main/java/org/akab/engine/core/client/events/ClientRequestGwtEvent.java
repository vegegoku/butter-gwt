package org.akab.engine.core.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.akab.engine.core.api.client.events.EventProcessor;
import com.google.web.bindery.event.shared.Event;

public abstract class ClientRequestGwtEvent extends Event<EventProcessor>{

    final static GwtEvent.Type<EventProcessor> CLIENT_REQUEST_EVENT_TYPE = new GwtEvent.Type<>();

    @Override
    public Type<EventProcessor> getAssociatedType() {
        return CLIENT_REQUEST_EVENT_TYPE;
    }

}