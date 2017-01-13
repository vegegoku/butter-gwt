package com.progressoft.security.login.client.requests;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import com.progressoft.security.login.shared.request.LoginRequest;
import com.progressoft.security.login.shared.response.LoginResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;

import org.akab.engine.core.api.client.annotations.Request;

import static java.util.Objects.*;

@Request
public class LoginServerRequest extends ClientServerRequest<LoginPresenter, LoginRequest, LoginResponse> {

    private final LoginCredentials loginCredentials;

    public LoginServerRequest(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    @Override
    protected void process(LoginPresenter presenter, LoginRequest serverRequest, LoginResponse response) {
        if(isNull(response.getPrincipal()))
            presenter.showError();
        else
            presenter.onLoginSuccess(response.getPrincipal());

    }

    @Override
    public LoginRequest buildArguments() {
        return new LoginRequest(loginCredentials);
    }
}
