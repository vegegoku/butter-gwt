package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

import java.util.Objects;

public class InMemoryAuthenticationHolder implements AuthenticationHolder {

    private Principal principal;

    public InMemoryAuthenticationHolder(Principal principal) {
        this.principal = principal;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(principal);
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }
}
