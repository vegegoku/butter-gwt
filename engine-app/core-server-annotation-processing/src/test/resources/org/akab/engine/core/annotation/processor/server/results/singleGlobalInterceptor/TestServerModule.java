package org.akab.engine.core.annotation.processor.server.singleGlobalInterceptor;

import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import com.google.auto.service.AutoService;
import org.akab.engine.core.annotation.processor.server.singleGlobalInterceptor.FirstGlobalInterceptor;
import org.akab.engine.core.annotation.processor.server.singleGlobalInterceptor.TestServerEntryPointContext;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerGlobalInterceptors(InterceptorsRegistry registry) {
        registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new FirstGlobalInterceptor());
    }
}
