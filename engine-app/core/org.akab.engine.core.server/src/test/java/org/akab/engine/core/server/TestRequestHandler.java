package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.RequestHandler;

public class TestRequestHandler implements RequestHandler<TestRequest, TestResponse> {
    @Override
    public TestResponse handleRequest(TestRequest request) {
        request.appendTestWord("-handled");

        return new TestResponse();
    }
}
