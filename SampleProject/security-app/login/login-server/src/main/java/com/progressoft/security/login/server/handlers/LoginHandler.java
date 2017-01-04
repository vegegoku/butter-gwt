package com.progressoft.security.login.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.login.shared.request.LoginRequest;

import java.util.logging.Logger;

@Handler
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {
    private static final Logger LOGGER= Logger.getLogger(LoginHandler.class.getName());
    @Override
    public LoginResponse handleRequest(LoginRequest request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new LoginResponse("Server message");
    }
}
