package org.akab.engine.core.client.request;

import org.akab.engine.core.api.client.events.ServerRequestEventFactory;
import org.akab.engine.core.api.client.request.RequestRouter;
import org.akab.engine.core.api.client.request.ClientServerRequest;

public class ServerRouter implements RequestRouter<ClientServerRequest> {

    private final AsyncRunner asyncRunner;

    public ServerRouter(ServerRequestEventFactory requestEventFactory) {
       asyncRunner=new AsyncRunner(requestEventFactory);
    }

    @Override
    public void routeRequest(final ClientServerRequest request) {
        asyncRunner.run(request);
    }
}
