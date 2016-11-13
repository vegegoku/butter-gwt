package org.akab.engine.core.api.client.events;

import org.akab.engine.core.api.client.request.ClientRequest;

public interface ClientRequestEventFactory {
    Event make(ClientRequest request);
}
