package org.akab.engine.core.api.client.events;

import org.akab.engine.core.api.client.request.ClientRequest;

@FunctionalInterface
public interface ClientRequestEventFactory {
    Event make(ClientRequest request);
}
