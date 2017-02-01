package com.progressoft.security.authentication.server.handlers;

import com.progressoft.security.authentication.server.shared.UserSessionContext;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;

@Handler
public class UserLoggedInHandler implements RequestHandler<AuthenticationRequest, AuthenticationResponse> {

    @Override
    public AuthenticationResponse handleRequest(AuthenticationRequest request) {
        return new AuthenticationResponse(UserSessionContext.get().getPrincipal());
    }
}
