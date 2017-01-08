package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.util.Objects;

public abstract class ClientServerRequest<P extends Presentable, R extends ServerRequest, S extends ServerResponse>
        extends BaseRequest {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ClientServerRequest.class);

    private R serverArgs;

    private final RequestState<ServerResponseRecievedStateContext> sent;

    private final RequestState<ServerSuccessRequestStateContext> executedOnServer =
            new RequestState<ServerSuccessRequestStateContext>() {
                @Override
                public void execute(ServerSuccessRequestStateContext context) {
                    process((P) getRequestPresenter(), serverArgs, (S) context.serverResponse);
                    applyHistory();
                    state = completed;
                    if(Objects.nonNull(chainedRequest))
                        chainedRequest.send();
                }
            };
    private final RequestState<ServerFailedRequestStateContext> failedOnServer =
            new RequestState<ServerFailedRequestStateContext>() {
                @Override
                public void execute(ServerFailedRequestStateContext context) {
                    onFailedServerCall((P) getRequestPresenter(), serverArgs, context.response);
                    state = completed;
                }

                private void onFailedServerCall(P presenter, R serverArgs, FailedServerResponse failedResponse) {
                    LOGGER.error("Could not execute request on server!.", failedResponse.getError());
                    processFailed(presenter, serverArgs, failedResponse);
                }
            };

    protected ClientServerRequest() {
        sent = context -> {
            if (context.nextContext instanceof ServerSuccessRequestStateContext) {
                state = executedOnServer;
                applyState(context.nextContext);
            } else if (context.nextContext instanceof ServerFailedRequestStateContext) {
                state = failedOnServer;
                applyState(context.nextContext);
            } else {
                throw new InvalidRequestState(
                        "Request cannot be processed until a serverResponse is recieved from the server");
            }
        };
    }

    @Override
    public void startRouting() {
        state = sent;
        clientApp.getServerRouter().routeRequest(this);
    }

    protected abstract void process(P presenter, R serverArgs, S response);

    protected abstract void processFailed(P presenter, R serverArgs, FailedServerResponse failedRespons);


    public abstract R buildArguments();

    public R arguments() {
        this.serverArgs = buildArguments();
        return this.serverArgs;
    }
}
