package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;

public class DefaultAuthenticationCompletedExtensionPoint implements AuthenticationCompletedExtensionPoint {

    private AuthenticationCompletedContext context;

    public DefaultAuthenticationCompletedExtensionPoint(
            AuthenticationCompletedContext context) {
        this.context = context;
    }


    @Override
    public AuthenticationCompletedContext context() {
        return context;
    }
}
