package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.response.CompleteAuthenticationResponse;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.request.CompleteAuthenticationRequest;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientServerRequest;

@Request
public class CompleteAuthenticationOnServerRequest extends
        ClientServerRequest<AuthenticationPresenter, CompleteAuthenticationRequest, CompleteAuthenticationResponse> {

    private final Principal principal;

    public CompleteAuthenticationOnServerRequest(Principal principal) {
        this.principal = principal;
    }

    @Override
    protected void process(AuthenticationPresenter presenter, CompleteAuthenticationRequest serverArgs,
                           CompleteAuthenticationResponse response) {
        if (response.isAuthenticated())
            presenter.onAuthenticationCompleted(principal);
        else
            presenter.showErrorMessage();
    }

    @Override
    public CompleteAuthenticationRequest buildArguments() {
        return new CompleteAuthenticationRequest(principal);
    }


}
