package com.progressoft.security.authentication.shared.extension;

import org.akab.engine.core.api.shared.extension.Context;

public interface AuthenticationContext extends Context {
    void registerProvider(ClientAuthenticationProvider authenticationProvider);
}
