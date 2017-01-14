package com.progressoft.security.login.client.requests;

import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.login.client.presenters.LoginPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainLayoutClientRequest extends ClientRequest<LoginPresenter> {

    private final AuthenticationLayoutContext context;

    public ObtainLayoutClientRequest(AuthenticationLayoutContext context) {
        this.context = context;
    }

    @Override
    protected void process(LoginPresenter presenter) {
        presenter.onLayoutContextReceived(context);
    }
}