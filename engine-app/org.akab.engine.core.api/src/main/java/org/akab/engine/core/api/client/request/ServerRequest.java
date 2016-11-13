package org.akab.engine.core.api.client.request;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;
import org.akab.engine.core.api.shared.request.FailedResponse;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public abstract class ServerRequest<P extends ClientPresenter, R extends ServerArgs, S extends Response>
        extends BaseRequest<P> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ServerRequest.class);

    private R serverArgs;

    private final RequestState<ServerResponseRecievedStateContext> sent =
            new RequestState<ServerResponseRecievedStateContext>() {
                @Override
                public void execute(ServerResponseRecievedStateContext context) {
                    if (context.nextContext instanceof ServerSuccessRequestStateContext) {
                        state = executedOnServer;
                        applyState(context.nextContext);
                    } else if (context.nextContext instanceof ServerFailedRequestStateContext) {
                        state = failedOnServer;
                        applyState(context.nextContext);
                    } else {
                        throw new InvalidRequestState(
                                "Request cannot be processed until a response is recieved from the server");
                    }
                }
            };

    private final RequestState<ServerSuccessRequestStateContext> executedOnServer =
            new RequestState<ServerSuccessRequestStateContext>() {
                @Override
                public void execute(ServerSuccessRequestStateContext context) {
                    process((P) getRequestPresenter(), serverArgs, (S) context.response);
                    applyHistory();
                    state = completed;

                }
            };
    private final RequestState<ServerFailedRequestStateContext> failedOnServer =
            new RequestState<ServerFailedRequestStateContext>() {
                @Override
                public void execute(ServerFailedRequestStateContext context) {
                    onFailedServerCall((P) getRequestPresenter(), serverArgs, context.response);
                    state = completed;
                }
            };

    @Override
    public void startRouting() {
        state = sent;
        clientApp.getServerRouter().routeRequest(this);
    }

    protected abstract void process(P presenter, R serverArgs, S response);


    protected void onFailedServerCall(P presenter, R serverArgs, FailedResponse failedResponse) {
        LOGGER.error("Could not execute request on server!.", failedResponse.getError());
    }

    public abstract R buildArguments();

    public R arguments() {
        this.serverArgs = buildArguments();
        return this.serverArgs;
    }
}
