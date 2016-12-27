package org.akab.engine.core.annotation.processor.server.singleInterceptor;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.annotation.processor.server.singleInterceptor.FirstInterceptor;
import org.akab.engine.core.annotation.processor.server.singleInterceptor.FirstRequest;
import org.akab.engine.core.annotation.processor.server.singleInterceptor.TestServerEntryPointContext;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerInterceptors(InterceptorsRegistry registry) {
        registry.registerInterceptor(FirstRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new FirstInterceptor());
    }
}
