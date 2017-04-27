package org.akab.engine.core.annotation.processor.server.multiHandlers;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.HandlerRegistry;
import org.akab.engine.core.annotation.processor.server.multiHandlers.FirstHandler;
import org.akab.engine.core.annotation.processor.server.multiHandlers.FirstRequest;
import org.akab.engine.core.annotation.processor.server.multiHandlers.SecondHandler;
import org.akab.engine.core.annotation.processor.server.multiHandlers.SecondRequest;
import org.akab.engine.core.annotation.processor.server.multiHandlers.ThirdHandler;
import org.akab.engine.core.annotation.processor.server.multiHandlers.ThirdRequest;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{
    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(FirstRequest.class.getCanonicalName(), new FirstHandler());
        registry.registerHandler(SecondRequest.class.getCanonicalName(), new SecondHandler());
        registry.registerHandler(ThirdRequest.class.getCanonicalName(), new ThirdHandler());
    }
}
