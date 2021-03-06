package com.progressoft.security.uimessages.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class UiMessagesRequest extends ServerRequest {

    private String message;

    public UiMessagesRequest() {
    }

    public UiMessagesRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
