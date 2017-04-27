package org.akab.engine.core.annotation.processor.server.multiInterceptors;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;

@Interceptor
public class SecondInterceptor implements RequestInterceptor<SecondRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(SecondRequest request, TestServerEntryPointContext context) {

    }
}

