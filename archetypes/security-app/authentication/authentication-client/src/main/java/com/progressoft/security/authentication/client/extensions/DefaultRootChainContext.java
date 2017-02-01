package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.extension.RootChainContext;

public class DefaultRootChainContext implements RootChainContext {

    private final Principal principal;

    public DefaultRootChainContext(Principal principal) {
        this.principal = principal;
    }

    @Override
    public Principal principal() {
        return this.principal;
    }
}
