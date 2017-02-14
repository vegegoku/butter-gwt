package com.progressoft.security.app.layout.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.repository.FakePrincipal;

public class FakeAuthenticationCompletedContext implements AuthenticationCompletedContext {
    @Override
    public Principal getPrincipal() {
        return new FakePrincipal("USER", "TENANT");
    }
}
