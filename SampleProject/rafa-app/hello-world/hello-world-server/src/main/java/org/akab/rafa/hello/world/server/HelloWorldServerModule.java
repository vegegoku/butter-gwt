package org.akab.rafa.hello.world.server;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.HandlerRegistry;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.rafa.HelloWordsArgs;

@AutoService(ServerModuleConfiguration.class)
public class HelloWorldServerModule implements ServerModuleConfiguration{
    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(HelloWordsArgs.class.getCanonicalName(), new HelloWorldHandler());
    }

    @Override
    public void registerInterceptors(InterceptorsRegistry registry) {

    }

    @Override
    public void registerGlobalInterceptors(InterceptorsRegistry registry) {

    }
}
