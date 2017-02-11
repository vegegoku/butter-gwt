package com.progressoft.security.uimessages.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class UiMessagesResponse extends ServerResponse {

    private String serverMessage;

    public UiMessagesResponse() {
    }

    public UiMessagesResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
