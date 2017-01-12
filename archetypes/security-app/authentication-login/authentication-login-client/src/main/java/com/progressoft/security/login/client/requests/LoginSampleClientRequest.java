package com.progressoft.security.login.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.login.client.presenters.LoginPresenter;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class LoginSampleClientRequest extends ClientRequest<LoginPresenter> {

    @Override
    protected void process(LoginPresenter presenter) {

    }
}