package com.progressoft.security.userinfo.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.repository.FakePrincipal;

public class FakeAuthenticationCompletedContext implements AuthenticationCompletedContext {

    private final Principal principal;

    public FakeAuthenticationCompletedContext() {
        this.principal = new FakePrincipal("USER", "TENANT");
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }
}
