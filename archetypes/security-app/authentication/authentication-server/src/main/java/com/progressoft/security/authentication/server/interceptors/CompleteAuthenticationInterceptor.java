package com.progressoft.security.authentication.server.interceptors;

import com.progressoft.security.authentication.server.shared.AuthenticationProcessContext;
import com.progressoft.security.authentication.server.shared.UserSessionContext;
import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;
import com.progressoft.security.authentication.shared.request.CompleteAuthenticationRequest;
import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.server.RpcEntryPointContext;

@Interceptor
public class CompleteAuthenticationInterceptor implements RequestInterceptor<CompleteAuthenticationRequest, RpcEntryPointContext> {
    @Override
    public void intercept(CompleteAuthenticationRequest request, RpcEntryPointContext context) {
        context.getHttpRequest().getSession().setAttribute(UserSessionContext.PRINCIPAL, request.getPrincipal());
        context.getHttpRequest().getSession().removeAttribute(AuthenticationProcessContext.AUTHENTICATION);
    }
}
