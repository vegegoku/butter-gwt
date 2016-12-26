package org.akab.engine.core.annotation.processor.server.singleHandler;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.HandlerRegistry;
import org.akab.engine.core.annotation.processor.server.singleHandler.HandlerImplementingRequestHandlerInterface;
import org.akab.engine.core.api.shared.request.ServerRequest;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(ServerRequest.class.getCanonicalName(), new HandlerImplementingRequestHandlerInterface());
    }
}