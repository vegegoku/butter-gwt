package com.progressoft.security.app.layout.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class LayoutResponse extends ServerResponse {

    private String serverMessage;

    public LayoutResponse() {
    }

    public LayoutResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
