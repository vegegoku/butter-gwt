package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.RequestInterceptor;

public class TestInterceptor implements RequestInterceptor<TestRequest, TestServerEntryPointContext> {
    @Override
    public void intercept(TestRequest request, TestServerEntryPointContext context) {
        request.appendTestWord("-intercepted");
        request.appendTestWord(context.getEntryContextParameter());
    }
}
