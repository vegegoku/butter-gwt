package org.akab.engine.core.api.client.request;


import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;
import org.akab.engine.core.api.shared.request.FailedResponse;
import org.akab.engine.core.api.shared.request.Response;

public interface Request<P extends ClientPresenter> {

    class DefaultRequestStateContext implements RequestStateContext{

    }

    class ServerResponseRecievedStateContext implements  RequestStateContext{
        final RequestStateContext nextContext;

        public ServerResponseRecievedStateContext(RequestStateContext nextContext) {
            this.nextContext = nextContext;
        }
    }

    class ServerSuccessRequestStateContext implements RequestStateContext{

        final Response response;

        public ServerSuccessRequestStateContext(Response response) {
            this.response=response;
        }
    }

    class ServerFailedRequestStateContext implements RequestStateContext{

        final FailedResponse response;

        public ServerFailedRequestStateContext(FailedResponse response) {
            this.response=response;
        }
    }

    void send();

    void startRouting();

    String getKey();

    void chainRequest(Request request);

    void applyState(RequestStateContext context);

    class InvalidRequestState extends RuntimeException{

        private static final long serialVersionUID = 1976356149064117774L;

        public InvalidRequestState() {
        }

        public InvalidRequestState(String message) {
            super(message);
        }
    }
}
