package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.GlobalInterceptor;


public class TestGlobalInterceptor implements GlobalInterceptor<TestServerEntryPointContext> {
    @Override
    public void intercept(ServerRequest request, TestServerEntryPointContext context) {
        ((TestRequest)request).appendTestWord("-globally-intercepted");
        ((TestRequest)request).appendTestWord(context.getEntryContextParameter());
    }
}
