package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.client.request.ServerRequest;

public class ServerSuccessRequestEvent extends ServerSuccessRequestGwtEvent implements Event {

    protected final ServerRequest request;
    private final ServerResponse serverResponse;
    private final ClientApp clientApp=ClientApp.make();

    public ServerSuccessRequestEvent(ServerRequest request, ServerResponse serverResponse) {
        this.request = request;
        this.serverResponse = serverResponse;
    }

    @Override
    public void fire() {
        clientApp.getEventsBus().publishEvent(this);
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
}
