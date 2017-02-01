package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.ChainCompletedSuccessfullyRequest;
import com.progressoft.security.authentication.client.requests.ChainFailedRequest;
import com.progressoft.security.authentication.shared.extension.*;

public class DefaultAuthenticationContext implements AuthenticationContext {

    @Override
    public void registerProvider(String name, ClientAuthenticationProvider authenticationProvider) {
        AuthenticationProviderRegistry.registerProvider(name, authenticationProvider);
    }

    @Override
    public void onChainCompleted(CompletedChainContext context) {
        new ChainCompletedSuccessfullyRequest(context).send();
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        new ChainFailedRequest(failedChainContext).send();
    }

}
