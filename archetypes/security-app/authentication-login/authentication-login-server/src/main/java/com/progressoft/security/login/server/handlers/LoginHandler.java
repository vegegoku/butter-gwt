package com.progressoft.security.login.server.handlers;

import com.progressoft.security.authentication.server.shared.UserSessionContext;
import com.progressoft.security.login.server.usecase.LoginUserUseCase;
import com.progressoft.security.login.shared.request.LoginRequest;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.repository.RepositoryContext;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import static com.progressoft.security.login.server.usecase.LoginUserUseCase.BadCredentialsException;
import static com.progressoft.security.login.server.usecase.LoginUserUseCase.UserNotFoundException;

@Handler
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(LoginHandler.class);

    @Override
    public LoginResponse handleRequest(LoginRequest request) {
        try {
            LoginResponse response = new LoginUserUseCase(RepositoryContext.userRepository()).login(request.getLoginCredentials());
            UserSessionContext.get().setProperty("principal", response.getPrincipal());
            return response;
        } catch (BadCredentialsException | UserNotFoundException e) {
            LOGGER.debug("Bad credentials : ", e);
            return new LoginResponse(null);
        }
    }
}

