package org.akab.engine.core.client.request;

import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.akab.engine.core.api.client.events.ServerRequestEventFactory;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import org.akab.engine.core.client.service.ServerService;
import org.akab.engine.core.client.service.ServerServiceAsync;

public class AsyncRunner {
    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AsyncRunner.class);
    private final ServerRequestEventFactory requestEventFactory;
    private final ServerServiceAsync serverService = GWT.create(ServerService.class);

    public AsyncRunner(ServerRequestEventFactory requestEventFactory){
        this.requestEventFactory = requestEventFactory;
    }

    public final void run(final ClientServerRequest request){
        com.google.gwt.core.client.GWT.runAsync(new RunAsyncCallback() {
            @Override
            public void onFailure(Throwable t) {
                LOGGER.error("Could not RunAsync request [" + request + "]", t);
            }

            @Override
            public void onSuccess() {
                serverService.executeRequest(request.arguments(), new AsyncCallback<ServerResponse>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        requestEventFactory.makeFailed(request, throwable).fire();
                    }

                    @Override
                    public void onSuccess(ServerResponse serverResponse) {
                        requestEventFactory.makeSuccess(request, serverResponse).fire();
                    }
                });
            }
        });
    }
}
