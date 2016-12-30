package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventProcessor;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public class ServerFailedRequestEvent extends ServerFailedRequestGwtEvent implements Event {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(ServerFailedRequestEvent.class);

    protected final ClientServerRequest request;
    private final Throwable error;
    private final ClientApp clientApp=ClientApp.make();

    public ServerFailedRequestEvent(ClientServerRequest request, Throwable error) {
        this.request = request;
        this.error=error;
    }

    @Override
    public void fire() {
        clientApp.getEventsBus().publishEvent(new GWTRequestEvent(this));
    }

    @Override
    public void process(){
        request.applyState(new Request.ServerResponseRecievedStateContext(makeFailedContext()));
    }

    private Request.ServerFailedRequestStateContext makeFailedContext() {
        return new Request.ServerFailedRequestStateContext( new FailedServerResponse(error));
    }

    @Override
    protected void dispatch(EventProcessor eventProcessor) {
        eventProcessor.process(this);
    }

    private class GWTRequestEvent implements EventsBus.RequestEvent<ServerFailedRequestGwtEvent> {

        private final ServerFailedRequestGwtEvent event;
        public GWTRequestEvent(ServerFailedRequestGwtEvent event) {
            this.event=event;
        }

        @Override
        public ServerFailedRequestGwtEvent asEvent() {
            return event;
        }
    }
}
