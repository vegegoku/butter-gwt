package org.akab.engine.core.api.client.events;

import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.client.request.ServerRequest;

public interface ServerRequestEventFactory {
    Event makeSuccess(ServerRequest request, ServerResponse serverResponse);
    Event makeFailed(ServerRequest request, Throwable error);
}
