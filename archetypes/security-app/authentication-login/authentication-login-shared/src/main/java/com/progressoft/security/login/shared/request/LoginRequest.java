package com.progressoft.security.login.shared.request;

import com.progressoft.security.login.shared.extension.LoginCredentials;
import org.akab.engine.core.api.shared.request.ServerRequest;

public class LoginRequest extends ServerRequest {

    private LoginCredentials loginCredentials;

    public LoginRequest() {
    }

    public LoginRequest(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }
}
