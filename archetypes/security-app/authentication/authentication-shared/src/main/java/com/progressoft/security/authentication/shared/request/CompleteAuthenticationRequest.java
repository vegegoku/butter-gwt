package com.progressoft.security.authentication.shared.request;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerRequest;

public class CompleteAuthenticationRequest extends ServerRequest{

    private Principal principal;

    public CompleteAuthenticationRequest() {
    }

    public CompleteAuthenticationRequest(Principal principal) {
        this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
