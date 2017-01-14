package com.progressoft.security.login.shared.response;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerResponse;

public class LoginResponse extends ServerResponse {

    private Principal principal;

    public LoginResponse() {
    }

    public LoginResponse(Principal principal) {
        this.principal = principal;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
