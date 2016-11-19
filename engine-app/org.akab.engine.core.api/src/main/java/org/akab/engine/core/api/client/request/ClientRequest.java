package org.akab.engine.core.api.client.request;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.util.Objects;

public abstract class ClientRequest<P extends ClientPresenter> extends BaseRequest<P> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ClientRequest.class);

    private final RequestState<Request.DefaultRequestStateContext> sent =
            new RequestState<Request.DefaultRequestStateContext>() {
                @Override
                public void execute(DefaultRequestStateContext context) {
                    process((P) getRequestPresenter());
                    applyHistory();
                    state = completed;
                    if(Objects.nonNull(chainedRequest))
                        chainedRequest.send();
                }
            };


    @Override
    public void startRouting() {
        state = sent;
        clientApp.getClientRouter().routeRequest(this);

    }

    @Override
    public String getKey() {
        return this.key;
    }

    protected abstract void process(P presenter);

}
