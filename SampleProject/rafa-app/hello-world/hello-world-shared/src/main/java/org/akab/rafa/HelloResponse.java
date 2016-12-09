package org.akab.rafa;

import org.akab.engine.core.api.shared.request.ServerResponse;


public class HelloResponse extends ServerResponse {

    private String serverMessage;

    public HelloResponse() {
    }

    public HelloResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
