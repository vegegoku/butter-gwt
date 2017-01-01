package com.progressoft.security.sample.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class SampleRequest extends ServerRequest {

    private String message;

    public SampleRequest() {
    }

    public SampleRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
