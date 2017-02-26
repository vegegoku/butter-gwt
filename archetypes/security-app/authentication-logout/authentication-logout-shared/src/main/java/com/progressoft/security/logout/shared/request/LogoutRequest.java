package com.progressoft.security.logout.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class LogoutRequest extends ServerRequest {

    private String message;

    public LogoutRequest() {
    }

    public LogoutRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
