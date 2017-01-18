package com.progressoft.security.authentication.shared.request;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerRequest;

public class AuthenticationRequest extends ServerRequest {

    private Principal sessionPrincipal;

    public AuthenticationRequest() {
        //required by GWT RPC serializer
    }

    public Principal getSessionPrincipal() {
        return sessionPrincipal;
    }

    public void setSessionPrincipal(Principal sessionPrincipal) {
        this.sessionPrincipal = sessionPrincipal;
    }
}
