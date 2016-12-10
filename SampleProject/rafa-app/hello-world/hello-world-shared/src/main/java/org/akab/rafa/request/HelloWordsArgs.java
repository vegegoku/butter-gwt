package org.akab.rafa.request;

import org.akab.engine.core.api.shared.request.ServerRequest;


public class HelloWordsArgs extends ServerRequest {

    private String message;

    public HelloWordsArgs() {
    }

    public HelloWordsArgs(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
