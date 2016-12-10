package org.akab.engine.core.api.client.request;


import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.api.shared.request.ServerResponse;

public interface Request<P extends Presentable> {

    class DefaultRequestStateContext implements RequestStateContext{

    }

    class ServerResponseRecievedStateContext implements  RequestStateContext{
        final RequestStateContext nextContext;

        public ServerResponseRecievedStateContext(RequestStateContext nextContext) {
            this.nextContext = nextContext;
        }
    }

    class ServerSuccessRequestStateContext implements RequestStateContext{

        final ServerResponse serverResponse;

        public ServerSuccessRequestStateContext(ServerResponse serverResponse) {
            this.serverResponse = serverResponse;
        }
    }

    class ServerFailedRequestStateContext implements RequestStateContext{

        final FailedServerResponse response;

        public ServerFailedRequestStateContext(FailedServerResponse response) {
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
