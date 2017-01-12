package com.progressoft.security.authentication.server.interceptors;

import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.authentication.server.registry.WebAuthenticationHolder;
import com.progressoft.security.authentication.shared.request.CompleteAuthenticationRequest;
import org.akab.engine.core.api.shared.server.Interceptor;
import org.akab.engine.core.api.shared.server.RequestInterceptor;
import org.akab.engine.core.server.RpcEntryPointContext;

@Interceptor
public class CompleteAuthenticationInterceptor implements RequestInterceptor<CompleteAuthenticationRequest, RpcEntryPointContext> {
    @Override
    public void intercept(CompleteAuthenticationRequest request, RpcEntryPointContext context) {
        ServerAuthenticationContext.hold(new WebAuthenticationHolder(context.getHttpRequest().getSession(), request.getPrincipal()));
    }
}
