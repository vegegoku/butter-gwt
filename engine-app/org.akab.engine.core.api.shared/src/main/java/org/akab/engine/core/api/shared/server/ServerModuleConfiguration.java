package org.akab.engine.core.api.shared.server;

public interface ServerModuleConfiguration {
    void registerHandlers(HandlerRegistry registry);
    void registerInterceptors(InterceptorsRegistry registry);
    void registerGlobalInterceptors(InterceptorsRegistry registry);
}