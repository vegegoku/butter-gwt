package org.akab.engine.core.annotation.processor.server.multiInterceptors;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.FirstInterceptor;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.FirstRequest;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.SecondInterceptor;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.SecondRequest;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.ThirdInterceptor;
import org.akab.engine.core.annotation.processor.server.multiInterceptors.ThirdRequest;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerInterceptors(InterceptorsRegistry registry) {
        registry.registerInterceptor(FirstRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new FirstInterceptor());
        registry.registerInterceptor(SecondRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new SecondInterceptor());
        registry.registerInterceptor(ThirdRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new ThirdInterceptor());
    }
}
