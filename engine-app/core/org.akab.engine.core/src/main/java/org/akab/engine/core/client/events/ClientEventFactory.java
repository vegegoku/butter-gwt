package org.akab.engine.core.client.events;

import org.akab.engine.core.api.client.events.ClientRequestEventFactory;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.request.ClientRequest;

public class ClientEventFactory implements ClientRequestEventFactory {
    @Override
    public Event make(ClientRequest request) {
        return new ClientRequestEvent(request);
    }
}
