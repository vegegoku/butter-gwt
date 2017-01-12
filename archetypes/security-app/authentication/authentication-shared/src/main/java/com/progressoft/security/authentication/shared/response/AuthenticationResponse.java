package com.progressoft.security.authentication.shared.response;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerResponse;

import java.util.Objects;

public class AuthenticationResponse extends ServerResponse {

    private Principal principal;

    public AuthenticationResponse() {
        //required by GWT RPC serializer
    }

    public AuthenticationResponse(Principal principal) {
        this.principal = principal;
    }

    public boolean isLoggedIn() {
        return Objects.nonNull(principal);
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }


}
