package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiMix.SecondRequest;

@Interceptor
public class SecondInterceptor implements RequestInterceptor<SecondRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(SecondRequest request, TestServerEntryPointContext context) {

    }
}

