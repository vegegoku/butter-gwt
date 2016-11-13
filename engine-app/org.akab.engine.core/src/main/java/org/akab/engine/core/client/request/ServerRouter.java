package org.akab.engine.core.client.request;

import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.akab.engine.core.api.client.events.ServerRequestEventFactory;
import org.akab.engine.core.api.client.request.RequestRouter;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.client.request.ServerRequest;
import org.akab.engine.core.api.shared.service.ServerService;
import org.akab.engine.core.api.shared.service.ServerServiceAsync;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public class ServerRouter implements RequestRouter<ServerRequest> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ServerRouter.class);

    private final ServerServiceAsync serverService = GWT.create(ServerService.class);

    private final ServerRequestEventFactory requestEventFactory;

    public ServerRouter(ServerRequestEventFactory requestEventFactory) {
        this.requestEventFactory = requestEventFactory;
    }

    @Override
    public void routeRequest(final ServerRequest request) {

        com.google.gwt.core.client.GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable t) {
                LOGGER.error("Could not RunAsync request [" + request + "]", t);
            }

            @Override
            public void onSuccess() {
                serverService.executeRequest(request.arguments(), new AsyncCallback<Response>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        requestEventFactory.makeFailed(request, throwable).fire();
                    }

                    @Override
                    public void onSuccess(Response response) {
                        requestEventFactory.makeSuccess(request, response).fire();
                    }
                });
            }
        });
    }
}