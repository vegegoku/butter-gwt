package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.GlobalInterceptor;
import org.akab.engine.core.api.shared.server.GlobalRequestInterceptor;

@GlobalInterceptor
public class ThirdGlobalInterceptor implements GlobalRequestInterceptor<TestServerEntryPointContext>{

    @Override
    public void intercept(ServerRequest request, TestServerEntryPointContext context) {

    }
}

