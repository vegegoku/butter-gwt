package org.akab.engine.core.annotation.processor.server.multiInterceptors;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.FirstRequest;

@Interceptor
public class FirstInterceptor implements RequestInterceptor<FirstRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(FirstRequest request, TestServerEntryPointContext context) {

    }
}

