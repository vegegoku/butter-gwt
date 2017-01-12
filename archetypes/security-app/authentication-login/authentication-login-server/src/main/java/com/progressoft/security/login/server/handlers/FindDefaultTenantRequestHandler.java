package com.progressoft.security.login.server.handlers;

import com.progressoft.security.authentication.shared.ServerAuthenticationContext;
import com.progressoft.security.login.shared.request.DefaultTenantRequest;
import com.progressoft.security.login.shared.response.DefaultTenantResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.login.shared.request.LoginRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@Handler
public class FindDefaultTenantRequestHandler implements RequestHandler<DefaultTenantRequest, DefaultTenantResponse> {
    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(FindDefaultTenantRequestHandler.class.getName());
    @Override
    public DefaultTenantResponse handleRequest(DefaultTenantRequest request) {

        return new DefaultTenantResponse(ServerAuthenticationContext.configuration().defaultTenant());
    }

}
