package com.progressoft.security.authentication.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class CompleteAuthenticationResponse extends ServerResponse{

    private boolean authenticated;

    public CompleteAuthenticationResponse() {
        //required by GWT RPC serializer
    }

    public CompleteAuthenticationResponse(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
