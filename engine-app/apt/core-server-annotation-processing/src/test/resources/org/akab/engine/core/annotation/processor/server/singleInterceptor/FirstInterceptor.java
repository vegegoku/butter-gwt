package org.akab.engine.core.annotation.processor.server.singleInterceptor;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;

@Interceptor
public class FirstInterceptor implements RequestInterceptor<FirstRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(FirstRequest request, TestServerEntryPointContext context) {

    }
}

