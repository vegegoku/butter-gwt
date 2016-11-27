package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.ClientApp;

public class ClientRequestEvent extends ClientRequestGwtEvent implements Event {

    protected final ClientRequest request;
    private final ClientApp clientApp=ClientApp.make();

    public ClientRequestEvent(ClientRequest request) {
        this.request = request;
    }

    @Override
    public void fire() {
        clientApp.getEventsBus().publishEvent(this);
    }

    @Override
    public void process(){
        request.applyState(new Request.DefaultRequestStateContext());
    }

    @Override
    protected void dispatch(EventProcessor eventProcessor) {
        eventProcessor.process(this);
    }
}
