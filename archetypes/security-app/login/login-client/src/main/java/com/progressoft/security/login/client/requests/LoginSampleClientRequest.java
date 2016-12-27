package com.progressoft.security.login.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.login.client.presenters.LoginPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import org.akab.engine.core.api.client.annotations.Request;
import com.progressoft.security.login.client.presenters.LoginPresenter;

@Request
public class LoginSampleClientRequest extends ClientRequest<LoginPresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public LoginSampleClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(LoginPresenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint, "Hello world! from login contribution fgfhfghgh");
    }
}