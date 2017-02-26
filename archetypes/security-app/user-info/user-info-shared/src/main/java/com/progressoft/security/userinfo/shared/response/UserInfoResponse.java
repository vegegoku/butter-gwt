package com.progressoft.security.userinfo.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class UserInfoResponse extends ServerResponse {

    private String serverMessage;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
