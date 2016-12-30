package org.akab.engine.app.test;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.events.ClientRequestEventFactory;
import org.akab.engine.core.api.client.events.Event;
import org.akab.engine.core.api.client.events.EventsBus;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.client.request.Request;
import org.akab.engine.core.api.client.request.RequestRouter;


public class TestClientRouter implements RequestRouter<ClientRequest> {

    private final ClientRequestEventFactory eventFactory= request -> new TestClientEvent(request);

    @Override
    public void routeRequest(ClientRequest request) {
        eventFactory.make(request).fire();
    }

    public class TestClientEvent implements Event{
        protected final ClientRequest request;
        private final ClientApp clientApp=ClientApp.make();

        public TestClientEvent(ClientRequest request) {
            this.request = request;
        }

        @Override
        public void fire() {
            clientApp.getEventsBus().publishEvent(new TestRequestEvent(this));
        }

        @Override
        public void process(){
            request.applyState(new Request.DefaultRequestStateContext());
        }
    }

    public class TestRequestEvent implements EventsBus.RequestEvent<TestClientEvent>{

        private final TestClientEvent event;

        public TestRequestEvent(TestClientEvent event) {
            this.event = event;
        }

        @Override
        public TestClientEvent asEvent() {
            return event;
        }
    }


}
