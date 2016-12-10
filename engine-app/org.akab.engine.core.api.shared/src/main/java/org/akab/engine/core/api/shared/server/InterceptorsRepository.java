package org.akab.engine.core.api.shared.server;

import java.util.Collection;

public interface InterceptorsRepository {
    void addInterceptor(String requestName, String entryPointName, RequestInterceptor interceptor);
    Collection<RequestInterceptor> findInterceptors(String requestName, String entryPointName);
    void addGlobalInterceptor(String entryPointName, GlobalInterceptor interceptor);
    Collection<GlobalInterceptor> findGlobalInterceptors(String entryPointName);
}
