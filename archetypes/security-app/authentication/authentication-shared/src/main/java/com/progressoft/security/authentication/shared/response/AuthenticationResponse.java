package com.progressoft.security.authentication.shared.response;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerResponse;

import java.util.Objects;

public class AuthenticationResponse extends ServerResponse {

    private Principal principal;

    public AuthenticationResponse() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationResponse that = (AuthenticationResponse) o;

        return principal != null ? principal.equals(that.principal) : that.principal == null;
    }

    @Override
    public int hashCode() {
        return principal != null ? principal.hashCode() : 0;
    }
}
