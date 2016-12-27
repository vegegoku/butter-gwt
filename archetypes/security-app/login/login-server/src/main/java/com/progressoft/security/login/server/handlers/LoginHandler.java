package com.progressoft.security.login.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.login.shared.request.LoginArgs;

import java.util.logging.Logger;

@Handler
public class LoginHandler implements RequestHandler<LoginArgs, LoginResponse> {
    private static final Logger LOGGER= Logger.getLogger(LoginHandler.class.getName());
    @Override
    public LoginResponse handleRequest(LoginArgs request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new LoginResponse("Server message");
    }
}
