package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.ServerRequestEventFactory;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.client.request.ServerRequest;

public class ServerEventFactory implements ServerRequestEventFactory
{
    @Override
    public Event makeSuccess(ServerRequest request, Response response) {
        return new ServerSuccessRequestEvent(request, response);
    }

    @Override
    public Event makeFailed(ServerRequest request, Throwable error) {
        return new ServerFailedRequestEvent(request, error);
    }
}
