package com.progressoft.security.userinfo.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class UserInfoRequest extends ServerRequest {

    private String message;

    public UserInfoRequest() {
    }

    public UserInfoRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
