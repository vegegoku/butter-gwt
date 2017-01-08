package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.Principal;

public class DefaultAuthenticationCompletedContext implements AuthenticationCompletedContext {

    private final Principal principal;

    public DefaultAuthenticationCompletedContext(
            Principal principal) {
        this.principal = principal;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }
}
