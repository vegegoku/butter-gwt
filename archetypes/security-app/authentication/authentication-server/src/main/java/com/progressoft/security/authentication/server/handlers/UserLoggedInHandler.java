package com.progressoft.security.authentication.server.handlers;

import com.progressoft.security.authentication.server.ServerAuthenticationContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;

import java.util.logging.Logger;

@Handler
public class UserLoggedInHandler implements RequestHandler<AuthenticationRequest, AuthenticationResponse> {
    private static final Logger LOGGER = Logger.getLogger(UserLoggedInHandler.class.getName());

    @Override
    public AuthenticationResponse handleRequest(AuthenticationRequest request) {
        return new AuthenticationResponse(principal());
    }

    private Principal principal() {
        return ServerAuthenticationContext.authenticationHolder.getPrincipal();
    }

    private boolean isLoggedIn() {
        return !ServerAuthenticationContext.authenticationHolder.isEmpty();
    }
}
