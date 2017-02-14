package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainAuthenticationCompletedContextClientRequest extends ClientRequest<AppLayoutPresenter> {

    private final AuthenticationCompletedContext context;

    public ObtainAuthenticationCompletedContextClientRequest(AuthenticationCompletedContext context) {
        this.context=context;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onAuthenticationCompletedContextRecieved(context);
    }
}