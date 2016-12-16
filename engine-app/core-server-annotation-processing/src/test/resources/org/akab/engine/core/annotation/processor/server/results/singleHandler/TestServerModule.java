package org.akab.engine.core.annotation.processor.server.singleHandler;

import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import com.google.auto.service.AutoService;
import org.akab.engine.core.annotation.processor.server.singleHandler.HandlerImplementingRequestHandlerInterface;


import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.server.HandlerRegistry;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(ServerRequest.class.getCanonicalName(), new HandlerImplementingRequestHandlerInterface());
    }
}