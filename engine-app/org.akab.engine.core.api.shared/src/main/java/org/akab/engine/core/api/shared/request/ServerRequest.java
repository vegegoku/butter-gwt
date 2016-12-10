package org.akab.engine.core.api.shared.request;

import java.io.Serializable;

public class ServerRequest implements Serializable{

    private static final long serialVersionUID = -8670728823544377945L;

    private String requestKey;

    public ServerRequest() {
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }
}
