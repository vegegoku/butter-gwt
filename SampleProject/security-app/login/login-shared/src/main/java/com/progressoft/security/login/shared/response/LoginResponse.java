package com.progressoft.security.login.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class LoginResponse extends ServerResponse {

    private String serverMessage;

    public LoginResponse() {
    }

    public LoginResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
