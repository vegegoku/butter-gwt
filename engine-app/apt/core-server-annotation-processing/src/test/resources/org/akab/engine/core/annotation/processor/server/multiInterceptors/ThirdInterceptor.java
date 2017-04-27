package org.akab.engine.core.annotation.processor.server.multiInterceptors;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;

@Interceptor
public class ThirdInterceptor implements RequestInterceptor<ThirdRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(ThirdRequest request, TestServerEntryPointContext context) {

    }
}

