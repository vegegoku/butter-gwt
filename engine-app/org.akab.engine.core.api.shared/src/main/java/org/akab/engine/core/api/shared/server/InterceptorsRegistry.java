package org.akab.engine.core.api.shared.server;

public interface InterceptorsRegistry {
    void registerInterceptor(String requestName, String entryPointName, RequestInterceptor interceptor);
    void registerGlobalInterceptor(String entryPointName, GlobalInterceptor interceptor);
}
