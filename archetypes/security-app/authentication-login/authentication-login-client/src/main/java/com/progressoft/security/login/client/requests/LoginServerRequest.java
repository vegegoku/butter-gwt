package com.progressoft.security.login.client.requests;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import com.progressoft.security.login.shared.request.LoginRequest;
import com.progressoft.security.login.shared.response.LoginResponse;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientServerRequest;

import static java.util.Objects.isNull;

@Request
public class LoginServerRequest extends ClientServerRequest<LoginPresenter, LoginRequest, LoginResponse> {

    private final LoginCredentials loginCredentials;
    private final static String LOGIN_FAILED="Login failed";
    private final static String BAD_CREDENTIALS="Bad credentials";

    public LoginServerRequest(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    @Override
    protected void process(LoginPresenter presenter, LoginRequest serverRequest, LoginResponse response) {
        if(isNull(response.getPrincipal()))
            presenter.showError(LOGIN_FAILED, BAD_CREDENTIALS);
        else
            presenter.onLoginSuccess(response.getPrincipal());
    }



    @Override
    public LoginRequest buildArguments() {
        return new LoginRequest(loginCredentials);
    }
}
