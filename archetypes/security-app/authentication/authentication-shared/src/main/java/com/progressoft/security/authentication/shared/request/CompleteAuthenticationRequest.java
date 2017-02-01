package com.progressoft.security.authentication.shared.request;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;
import org.akab.engine.core.api.shared.request.ServerRequest;

public class CompleteAuthenticationRequest extends ServerRequest{

    private Principal principal;
    private AuthenticationHolder holder;

    public CompleteAuthenticationRequest() {
        //required by GWT RPC serializer
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

    public AuthenticationHolder getHolder() {
        return holder;
    }

    public void setHolder(AuthenticationHolder holder) {
        this.holder = holder;
    }
}
