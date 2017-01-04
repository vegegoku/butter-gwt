package com.progressoft.security.sample.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class SampleResponse extends ServerResponse {

    private String serverMessage;

    public SampleResponse() {
    }

    public SampleResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
