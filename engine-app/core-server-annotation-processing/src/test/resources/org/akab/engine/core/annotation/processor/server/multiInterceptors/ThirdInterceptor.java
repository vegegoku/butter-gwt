package org.akab.engine.core.annotation.processor.server.multiInterceptors;

import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.ThirdRequest;

@Interceptor
public class ThirdInterceptor implements RequestInterceptor<ThirdRequest, TestServerEntryPointContext>{

    @Override
    public void intercept(ThirdRequest request, TestServerEntryPointContext context) {

    }
}

