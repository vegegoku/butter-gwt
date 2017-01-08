package org.akab.engine.core.client.request;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.client.request.RequestRouter;
import org.akab.engine.core.api.client.events.ClientRequestEventFactory;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public class ClientRouter implements RequestRouter<ClientRequest> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ClientRouter.class);

    private final ClientRequestEventFactory requestEventFactory;

    public ClientRouter(ClientRequestEventFactory requestEventFactory) {
        this.requestEventFactory = requestEventFactory;
    }

    @Override
    public void routeRequest(final ClientRequest request) {
        GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable t) {
                LOGGER.error("Could not RunAsync request [" + request + "]", t);
            }

            @Override
            public void onSuccess() {
                requestEventFactory.make(request).fire();
            }
        });
    }
}
