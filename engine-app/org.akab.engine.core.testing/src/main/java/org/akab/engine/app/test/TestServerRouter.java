package org.akab.engine.app.test;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.api.client.events.ServerRequestEventFactory;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.request.RequestRouter;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.ServerApp;
import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

public class TestServerRouter implements RequestRouter<ClientServerRequest> {

    private final ServerRequestEventFactory eventFactory=new ServerRequestEventFactory() {
        @Override
        public Event makeSuccess(ClientServerRequest request, ServerResponse serverResponse) {
            return new TestServerSuccessEvent(request,serverResponse);
        }

        @Override
        public Event makeFailed(ClientServerRequest request, Throwable error) {
            return new TestServerFailedEvent(request, error);
        }
    };

    private final TestServerService service=new TestServerService(){

        @Override
        public ServerResponse executeRequest(ServerRequest request) {
            return ServerApp.make().executeRequest(request, entryPointContext);
        }
    };

    private final ServerEntryPointContext entryPointContext;

    public TestServerRouter(ServerEntryPointContext entryPointContext) {
        this.entryPointContext = entryPointContext;
    }

    @Override
    public void routeRequest(ClientServerRequest request) {
        try{
            ServerResponse response=service.executeRequest(request.arguments());
            eventFactory.makeSuccess(request, response).fire();
        }catch (Exception ex){
            eventFactory.makeFailed(request, ex);
        }
    }

    public class TestServerSuccessEvent implements Event{
        protected final ClientServerRequest request;
        protected final ServerResponse serverResponse;
        private final ClientApp clientApp=ClientApp.make();

        public TestServerSuccessEvent(ClientServerRequest request, ServerResponse serverResponse) {
            this.request = request;
            this.serverResponse=serverResponse;
        }

        @Override
        public void fire() {
            clientApp.getEventsBus().publishEvent(new TestSuccessRequestEvent(this));
        }

        @Override
        public void process(){
            request.applyState(new Request.ServerResponseRecievedStateContext(makeSuccessContext()));
        }

        private Request.ServerSuccessRequestStateContext makeSuccessContext() {
            return new Request.ServerSuccessRequestStateContext(serverResponse);
        }
    }

    public class TestSuccessRequestEvent implements EventsBus.RequestEvent<TestServerSuccessEvent>{

        private final TestServerSuccessEvent event;

        public TestSuccessRequestEvent(TestServerSuccessEvent event) {
            this.event = event;
        }

        @Override
        public TestServerSuccessEvent asEvent() {
            return event;
        }
    }

    public class TestServerFailedEvent implements Event{
        protected final ClientServerRequest request;
        protected final Throwable error;
        private final ClientApp clientApp=ClientApp.make();

        public TestServerFailedEvent(ClientServerRequest request, Throwable error) {
            this.request = request;
            this.error = error;
        }

        @Override
        public void fire() {
            clientApp.getEventsBus().publishEvent(new TestFailedRequestEvent(this));
        }

        @Override
        public void process(){
            request.applyState(new Request.ServerResponseRecievedStateContext(makeFailedContext()));
        }

        private Request.ServerFailedRequestStateContext makeFailedContext() {
            return new Request.ServerFailedRequestStateContext( new FailedServerResponse(error));
        }
    }

    public class TestFailedRequestEvent implements EventsBus.RequestEvent<TestServerFailedEvent>{

        private final TestServerFailedEvent event;

        public TestFailedRequestEvent(TestServerFailedEvent event) {
            this.event = event;
        }

        @Override
        public TestServerFailedEvent asEvent() {
            return event;
        }
    }
}
