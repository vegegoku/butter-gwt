package org.akab.engine.core.annotation.processor.server.multiMix;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.HandlerRegistry;
import org.akab.engine.core.annotation.processor.server.multiMix.FirstHandler;
import org.akab.engine.core.annotation.processor.server.multiMix.FirstRequest;
import org.akab.engine.core.annotation.processor.server.multiMix.SecondHandler;
import org.akab.engine.core.annotation.processor.server.multiMix.SecondRequest;
import org.akab.engine.core.annotation.processor.server.multiMix.ThirdHandler;
import org.akab.engine.core.annotation.processor.server.multiMix.ThirdRequest;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.annotation.processor.server.multiMix.FirstInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiMix.SecondInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.ThirdInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.FirstGlobalInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.SecondGlobalInterceptor;
import org.akab.engine.core.annotation.processor.server.multiMix.ThirdGlobalInterceptor;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(FirstRequest.class.getCanonicalName(), new FirstHandler());
        registry.registerHandler(SecondRequest.class.getCanonicalName(), new SecondHandler());
        registry.registerHandler(ThirdRequest.class.getCanonicalName(), new ThirdHandler());
    }

    @Override
    public void registerInterceptors(InterceptorsRegistry registry) {
        registry.registerInterceptor(FirstRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new FirstInterceptor());
        registry.registerInterceptor(SecondRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new SecondInterceptor());
        registry.registerInterceptor(ThirdRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new ThirdInterceptor());
    }

    @Override
    public void registerGlobalInterceptors(InterceptorsRegistry registry) {
        registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new FirstGlobalInterceptor());
        registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new SecondGlobalInterceptor());
        registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new ThirdGlobalInterceptor());
    }
}
