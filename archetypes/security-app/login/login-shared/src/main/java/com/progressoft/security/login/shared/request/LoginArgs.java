package com.progressoft.security.login.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class LoginArgs extends ServerRequest {

    private String message;

    public LoginArgs() {
    }

    public LoginArgs(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
