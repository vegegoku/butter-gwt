package org.akab.engine.core.api.client.events;

import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;

public interface ServerRequestEventFactory {
    Event makeSuccess(ClientServerRequest request, ServerResponse serverResponse);
    Event makeFailed(ClientServerRequest request, Throwable error);
}
