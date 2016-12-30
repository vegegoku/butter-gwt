package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.History.TokenConstruct;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

public abstract class BaseRequest<P extends Presentable> implements Request<P> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(BaseRequest.class);

    public static final String REQUEST_HAVE_ALREADY_BEEN_SENT = "Request have already been sent";

    protected final String key;
    protected RequestState state;
    protected final ClientApp clientApp=ClientApp.make();
    protected Request chainedRequest;

    protected final RequestState<DefaultRequestStateContext> ready=new RequestState<DefaultRequestStateContext>() {
        @Override
        public void execute(DefaultRequestStateContext context) {
            startRouting();
        }
    };

    protected final RequestState<DefaultRequestStateContext> completed=new RequestState<DefaultRequestStateContext>() {
        @Override
        public void execute(DefaultRequestStateContext context) {
            throw new InvalidRequestState("This request have already been completed!.");
        }
    };

    public BaseRequest() {
        this.key=this.getClass().getCanonicalName();
        this.state=ready;
    }

    @Override
    public void chainRequest(Request request) {
        this.chainedRequest=request;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void send() {
        if(state!=ready)
            throw new InvalidRequestState(REQUEST_HAVE_ALREADY_BEEN_SENT);
        this.state.execute(new DefaultRequestStateContext());
    }

    protected Presentable getRequestPresenter() {
        return clientApp.getPresentersRepository().getPresenter(getPresenterName());
    }

    private String getPresenterName() {
        return clientApp.getRequestRepository().findRequestPresenterWrapper(this.getKey()).getPresenterName();
    }

    protected void constructHistoryToken(TokenConstruct tokenConstruct){

    }

    protected void applyHistory(){
        constructHistoryToken(clientApp.getTokenConstruct());
        clientApp.applyUrlHistory();
    };

    @Override
    public void applyState(RequestStateContext context) {
        state.execute(context);
    }

}
