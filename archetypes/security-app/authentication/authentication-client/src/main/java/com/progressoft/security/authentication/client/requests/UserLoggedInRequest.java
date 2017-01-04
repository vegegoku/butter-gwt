package com.progressoft.security.authentication.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import org.akab.engine.core.api.client.annotations.Request;

@Request
public class UserLoggedInRequest
        extends ClientServerRequest<AuthenticationPresenter, AuthenticationRequest, AuthenticationResponse> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(UserLoggedInRequest.class);

    @Override
    protected void process(AuthenticationPresenter presenter, AuthenticationRequest serverRequest,
                           AuthenticationResponse response) {
        if (response.isLoggedIn())
            presenter.onAuthenticationCompleted(response.getPrincipal());
    }

    @Override
    public AuthenticationRequest buildArguments() {
        return new AuthenticationRequest();
    }
}
