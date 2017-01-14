package com.progressoft.security.login.server.handlers;

import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.login.shared.request.DefaultTenantRequest;
import com.progressoft.security.login.shared.response.DefaultTenantResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class FindDefaultTenantRequestHandler implements RequestHandler<DefaultTenantRequest, DefaultTenantResponse> {
    @Override
    public DefaultTenantResponse handleRequest(DefaultTenantRequest request) {
        return new DefaultTenantResponse(ServerAuthenticationContext.configuration().defaultTenant());
    }

}
