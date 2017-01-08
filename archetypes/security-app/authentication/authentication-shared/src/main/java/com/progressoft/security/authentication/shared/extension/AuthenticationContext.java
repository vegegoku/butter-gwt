package com.progressoft.security.authentication.shared.extension;

import org.akab.engine.core.api.shared.extension.Context;

public interface AuthenticationContext extends Context {
    void registerProvider(String name, ClientAuthenticationProvider authenticationProvider);

    void onChainCompleted(CompletedChainContext context);

    void onChainFailed(FailedChainContext failedChainContext);
}
