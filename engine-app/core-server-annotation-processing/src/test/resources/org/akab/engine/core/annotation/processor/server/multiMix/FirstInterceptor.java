package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiMix.FirstRequest;

@Interceptor
public class FirstInterceptor implements RequestInterceptor<FirstRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(FirstRequest request, TestServerEntryPointContext context) {

    }
}

