package com.progressoft.security.authentication.server.interceptors;

import com.progressoft.security.authentication.server.registry.WebAuthenticationHolder;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;
import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.server.RpcEntryPointContext;

@Interceptor
public class UserSessionInterceptor implements RequestInterceptor<AuthenticationRequest, RpcEntryPointContext> {
    @Override
    public void intercept(AuthenticationRequest request, RpcEntryPointContext context) {
        request.setSessionPrincipal(new WebAuthenticationHolder(context.getHttpRequest().getSession()).getPrincipal());
    }
}
