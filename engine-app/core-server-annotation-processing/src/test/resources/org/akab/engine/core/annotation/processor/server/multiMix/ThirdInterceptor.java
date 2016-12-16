package org.akab.engine.core.annotation.processor.server.multiMix;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiMix.ThirdRequest;

@Interceptor
public class ThirdInterceptor implements RequestInterceptor<ThirdRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(ThirdRequest request, TestServerEntryPointContext context) {

    }
}

