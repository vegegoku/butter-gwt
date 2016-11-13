package org.akab.engine.core.api.client.events;

import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.client.request.ServerRequest;

public interface ServerRequestEventFactory {
    Event makeSuccess(ServerRequest request, Response response);
    Event makeFailed(ServerRequest request, Throwable error);
}
