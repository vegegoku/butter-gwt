package com.progressoft.security.layout.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.layout.client.presenters.AuthenticationLayoutPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import org.akab.engine.core.api.client.annotations.Request;
import com.progressoft.security.layout.client.presenters.AuthenticationLayoutPresenter;

@Request
public class AuthenticationLayoutClientRequest extends ClientRequest<AuthenticationLayoutPresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public AuthenticationLayoutClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(AuthenticationLayoutPresenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint);
    }
}