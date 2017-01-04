package com.progressoft.security.authentication.server;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

public class ServerAuthenticationContext {

    public static AuthenticationHolder authenticationHolder = new NullHolder();

    private static final class NullHolder implements AuthenticationHolder {

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Principal getPrincipal() {
            return null;
        }
    }
}
