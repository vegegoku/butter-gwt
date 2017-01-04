package com.progressoft.security.authentication.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import org.akab.engine.core.api.client.annotations.Request;
import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;

@Request
public class AuthenticationSampleClientRequest extends ClientRequest<AuthenticationPresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public AuthenticationSampleClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(AuthenticationPresenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint, "Hello world! from Authentication contribution request");
    }
}