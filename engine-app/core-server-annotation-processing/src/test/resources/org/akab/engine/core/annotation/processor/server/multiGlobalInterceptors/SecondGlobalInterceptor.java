package org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.GlobalInterceptor;
import org.akab.engine.core.api.shared.server.GlobalRequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors.TestServerEntryPointContext;

@GlobalInterceptor
public class SecondGlobalInterceptor implements GlobalRequestInterceptor<TestServerEntryPointContext>{

    @Override
    public void intercept(ServerRequest request, TestServerEntryPointContext context) {

    }
}
