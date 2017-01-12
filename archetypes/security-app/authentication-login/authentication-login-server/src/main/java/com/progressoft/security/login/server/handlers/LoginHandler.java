package com.progressoft.security.login.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.login.shared.request.LoginRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@Handler
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {
    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(LoginHandler.class.getName());
    @Override
    public LoginResponse handleRequest(LoginRequest request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new LoginResponse("Server message");
    }

}
