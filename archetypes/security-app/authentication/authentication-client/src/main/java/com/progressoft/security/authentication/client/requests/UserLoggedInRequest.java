package com.progressoft.security.authentication.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import com.progressoft.security.authentication.shared.response.AuthenticationResponse;
import com.progressoft.security.authentication.shared.request.AuthenticationRequest;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import org.akab.engine.core.api.client.annotations.Request;

@Request
public class UserLoggedInRequest
        extends ClientServerRequest<AuthenticationPresenter, AuthenticationRequest, AuthenticationResponse> {

    @Override
    protected void process(AuthenticationPresenter presenter, AuthenticationRequest serverRequest,
                           AuthenticationResponse response) {
        if (response.isLoggedIn())
            presenter.onAuthenticationCompleted(response.getPrincipal());
        else
            presenter.onNoAuthenticatedUserFound();
    }

    @Override
    public AuthenticationRequest buildArguments() {
        return new AuthenticationRequest();
    }
}
