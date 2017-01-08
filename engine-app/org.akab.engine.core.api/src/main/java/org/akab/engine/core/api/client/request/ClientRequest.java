package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;

import java.util.Objects;

public abstract class ClientRequest<P extends Presentable> extends BaseRequest {

    private final RequestState<Request.DefaultRequestStateContext> sent =
            context -> {
                process((P) getRequestPresenter());
                applyHistory();
                state = completed;
                if(Objects.nonNull(chainedRequest))
                    chainedRequest.send();
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
