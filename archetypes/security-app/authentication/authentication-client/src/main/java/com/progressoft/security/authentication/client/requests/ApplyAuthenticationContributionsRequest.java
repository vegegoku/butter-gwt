package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;

@Request
public class ApplyAuthenticationContributionsRequest extends ClientRequest<AuthenticationPresenter> {

    @Override
    protected void process(AuthenticationPresenter presenter) {
        presenter.applyAuthenticationContributions();
    }
}
