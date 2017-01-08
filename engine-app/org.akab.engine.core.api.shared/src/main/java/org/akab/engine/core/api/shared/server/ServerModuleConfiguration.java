package org.akab.engine.core.api.shared.server;

public interface ServerModuleConfiguration {
    default void registerHandlers(HandlerRegistry registry){}
    default void registerInterceptors(InterceptorsRegistry registry){}
    default void registerGlobalInterceptors(InterceptorsRegistry registry){}
}