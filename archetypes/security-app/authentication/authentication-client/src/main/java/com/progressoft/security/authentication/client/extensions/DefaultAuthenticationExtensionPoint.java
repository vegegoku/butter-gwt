package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;

public class DefaultAuthenticationExtensionPoint implements AuthenticationExtensionPoint {
    private final AuthenticationContext context;

    public DefaultAuthenticationExtensionPoint(AuthenticationContext context) {
        this.context = context;
    }

    @Override
    public AuthenticationContext context() {
        return context;
    }
}
