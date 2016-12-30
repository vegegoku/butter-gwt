package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;

public class ServerSuccessRequestEvent extends ServerSuccessRequestGwtEvent implements Event {

    protected final ClientServerRequest request;
    private final ServerResponse serverResponse;
    private final ClientApp clientApp=ClientApp.make();

    public ServerSuccessRequestEvent(ClientServerRequest request, ServerResponse serverResponse) {
        this.request = request;
        this.serverResponse = serverResponse;
    }

    @Override
    public void fire() {
        clientApp.getEventsBus().publishEvent(new GWTRequestEvent(this));
    }

    @Override
    public void process(){
        request.applyState(new Request.ServerResponseRecievedStateContext(makeSuccessContext()));
    }

    private Request.ServerSuccessRequestStateContext makeSuccessContext() {
        return new Request.ServerSuccessRequestStateContext(serverResponse);
    }

    @Override
    protected void dispatch(EventProcessor eventProcessor) {
        eventProcessor.process(this);
    }

    private class GWTRequestEvent implements EventsBus.RequestEvent<ServerSuccessRequestGwtEvent> {

        private final ServerSuccessRequestGwtEvent event;
        public GWTRequestEvent(ServerSuccessRequestGwtEvent event) {
            this.event=event;
        }

        @Override
        public ServerSuccessRequestGwtEvent asEvent() {
            return event;
        }
    }
}
